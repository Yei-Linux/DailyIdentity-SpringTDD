package pe.yeilinux.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.identity.controller.request.UserRegisterRequest;
import pe.yeilinux.identity.controller.response.GeneralResponse;
import pe.yeilinux.identity.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/{companyId}")
    public ResponseEntity<?> createClient(@PathVariable int companyId){
        return new ResponseEntity<>(this.userService.findUserByCompanyId(companyId), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserRegisterRequest userRegisterRequest){
        this.userService.createUser(userRegisterRequest);
        return new ResponseEntity<>(new GeneralResponse("User created correctly"),HttpStatus.CREATED);
    }
}
