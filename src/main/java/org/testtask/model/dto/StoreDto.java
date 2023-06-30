package org.testtask.model.dto;

import java.util.Date;
import java.util.List;

public record StoreDto(
        String id,
        int active,
        int top,
        String name,
        Date createdAt,
        long createdAtTimestamp,
        Date updatedAt,
        long updatedAtTimestamp,
        boolean complainable,
        int popularity,
        String description,
        List<CategoryDto> categories,
        List<StoreDto> similarShops,
        List<String> tags,
        List<Voucher> vouchers,
        boolean isFavorite,
        List<CashbackRate> cashbackRates,
        boolean isExtensionVisible,
        String link,
        String logo,
        List<String> urlMatches) {
}
