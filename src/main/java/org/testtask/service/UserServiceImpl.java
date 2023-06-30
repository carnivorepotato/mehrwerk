package org.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestApiCaller restService;

    @Override
    public void login() {
        restService.getBearerToken();
    }
}
