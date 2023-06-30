package org.testtask.service;

public interface UserService {
    /**
     * Retrieves or refreshes bearer token from remote API
     */
    void login();
}
