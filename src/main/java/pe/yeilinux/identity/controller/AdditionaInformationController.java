package pe.yeilinux.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.identity.controller.response.GeneralResponse;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.service.AdditionalInformationService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("additional-information")
public class AdditionaInformationController {
    @Autowired
    AdditionalInformationService additionalInformationService;

    @GetMapping("user")
    public ResponseEntity<?> getAdditionalInformationByUser(@RequestParam String username){
        return new ResponseEntity<>(this.additionalInformationService.getAdditionalInformationByUser(username), HttpStatus.OK);
    }

    @GetMapping("user-fields")
    public ResponseEntity<?> getAdditionalInformationFields(){
        return new ResponseEntity<>(this.additionalInformationService.getAdditionalInformationFields(), HttpStatus.OK);
    }

    @GetMapping("token/{tokenId}")
    public ResponseEntity<?> getAdditionalInformation(@PathVariable int tokenId) throws IOException {
        return new ResponseEntity<>(this.additionalInformationService.getAdditionalInformation(tokenId), HttpStatus.OK);
    }

    @PostMapping("token/{tokenId}")
    public ResponseEntity<?> postAdditionalInformation(@PathVariable int tokenId,@RequestBody String additionalInformation){
        this.additionalInformationService.createAdditionalInformation(additionalInformation,tokenId);
        return new ResponseEntity<>(new GeneralResponse("Created correctly"),HttpStatus.CREATED);
    }
}
