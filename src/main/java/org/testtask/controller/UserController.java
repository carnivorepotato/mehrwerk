package org.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.testtask.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        try {
            service.login();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.PRECONDITION_FAILED);
        } catch (HttpClientErrorException.Unauthorized u) {
            return new ResponseEntity<>(u.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
