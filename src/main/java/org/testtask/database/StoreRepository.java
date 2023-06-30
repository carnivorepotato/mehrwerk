package org.testtask.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testtask.model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
