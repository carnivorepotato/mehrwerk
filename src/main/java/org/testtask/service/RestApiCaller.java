package org.testtask.service;

import org.testtask.model.dto.StoreDto;

import java.util.List;

public interface RestApiCaller {
    /**
     * Retrieves bearer token from remote API.
     * @return Remotely generated bearer token.
     * @throws IllegalStateException Signifies that either response body is empty or received status code is wrong
     */
    String getBearerToken();

    /**
     * Retrieves store list from remote API.
     * @return Store data transfer object list.
     * @throws IllegalStateException Signifies that either response body is empty or received status code is wrong
     */
    List<StoreDto> getStoresList();
}
