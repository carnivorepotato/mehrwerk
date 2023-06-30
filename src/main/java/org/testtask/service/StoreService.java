package org.testtask.service;

import org.testtask.model.Store;

import java.util.List;

public interface StoreService {
    /**
     * Retrieves the list of stores from remote API and saves it to the database.
     */
    void retrieveAndPersistStoreList();

    /**
     * Returns the list of stores from the database.
     * @return List of saved stores.
     */
    List<Store> getStoreList();
}
