package org.testtask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AppInitializerService implements InitializerService {
    @Autowired
    private StoreService storeService;
    private Logger logger = LoggerFactory.getLogger(AppInitializerService.class);

    @Override
    public void initialize() {
        try {
            storeService.retrieveAndPersistStoreList();
        } catch (HttpClientErrorException.Unauthorized u) {
            logger.error(u.getLocalizedMessage());
        }
    }
}
