package org.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.testtask.model.Store;
import org.testtask.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService service;

    @PostMapping("/retrieve")
    public ResponseEntity<String> retrieveAndPersistStoreList() {
        try {
            service.retrieveAndPersistStoreList();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.PRECONDITION_FAILED);
        } catch (HttpClientErrorException.Unauthorized u) {
            return new ResponseEntity<>(u.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Store>> getStoreList() {
        try {
            return new ResponseEntity<>(service.getStoreList(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
