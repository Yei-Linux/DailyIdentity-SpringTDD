package pe.yeilinux.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.identity.controller.response.GeneralResponse;
import pe.yeilinux.identity.domain.Token;
import pe.yeilinux.identity.service.TokenService;

@RestController
@RequestMapping("signature")
public class SignatureController {
    @Autowired
    TokenService tokenService;

    @GetMapping
    public ResponseEntity<?> getSignatures(){
        return new ResponseEntity<>(this.tokenService.getSignKeyAndVerifierKey(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateSignature(@RequestBody Token token){
        this.tokenService.updateSignKeyAndVerifierKey(token);
        return new ResponseEntity<>(new GeneralResponse("Correctly Updated"), HttpStatus.OK);
    }
}
