package org.testtask.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.testtask.model.Store;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreDaoImpl implements StoreDao {

    @NonNull
    private DaoFactory daoFactory;

    @Override
    public void saveStores(List<Store> stores) {
        daoFactory.getStoreRepository().saveAll(stores);
    }

    @Override
    public List<Store> getSavedStores() {
        return daoFactory.getStoreRepository().findAll();
    }
}
