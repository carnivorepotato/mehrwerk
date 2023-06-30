package org.testtask.database;

import org.testtask.model.Store;

import java.util.List;

public interface StoreDao {
    /**
     * Saves mapped stores to the database.
     * @param stores Stores to persist to the database.
     */
    void saveStores(List<Store> stores);

    /**
     * Returns the list of previously saved stores.
     * @return List of previously saved stores.
     */
    List<Store> getSavedStores();
}
