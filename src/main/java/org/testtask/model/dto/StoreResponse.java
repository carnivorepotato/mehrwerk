package org.testtask.model.dto;

import java.util.List;

public record StoreResponse(
        int currentPage,
        int numberOfPages,
        int numberOfResults,
        List<StoreDto> items) {
}
