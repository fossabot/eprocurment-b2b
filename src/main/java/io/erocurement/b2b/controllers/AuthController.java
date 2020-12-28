package io.erocurement.b2b.controllers;



import io.erocurement.b2b.errors.ValidationErrorBuilder;
import io.erocurement.b2b.models.entity.User;
import io.erocurement.b2b.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    private UserService service;


    @RequestMapping(value = "user-info/", method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }


    @PostMapping("/singin")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, Errors errors) {
        if (this.service.existsByUsername(user.getUsername())) {

            return ResponseEntity.badRequest().body("Error User Invalid");
        }

        if (this.service.existsByEmail(user.getEmail())) {

            return ResponseEntity.badRequest().body("Error email Invalid");
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }


        return new ResponseEntity<User>(service.save(user), HttpStatus.OK);

    }
}
