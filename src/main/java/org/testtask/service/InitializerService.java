package org.testtask.service;

public interface InitializerService {
    /**
     * Runs initializtion logic.
     * In our case this method should call API to get a bearer token,
     * with that token get a list of stores from another API and persist it in the database.
     */
    void initialize();
}
