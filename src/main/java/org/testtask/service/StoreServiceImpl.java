package org.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testtask.database.StoreDao;
import org.testtask.mapper.StoreMapper;
import org.testtask.model.Store;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final RestApiCaller restService;
    private final StoreDao storeDao;
    private final StoreMapper mapper;

    @Override
    public void retrieveAndPersistStoreList() {
        saveStoreListToDatabase(mapper.mapStores(restService.getStoresList()));
    }

    @Override
    public List<Store> getStoreList() {
        return storeDao.getSavedStores();
    }

    private void saveStoreListToDatabase(List<Store> stores) {
        storeDao.saveStores(stores);
    }
}
