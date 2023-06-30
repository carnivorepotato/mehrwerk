package org.testtask.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import org.testtask.model.dto.CategoryDto;
import org.testtask.model.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public StoreDto generateTestStoreDto() {
        String id = RandomStringUtils.random(10, true, true);
        String name = RandomStringUtils.randomAlphabetic(10);
        String description = RandomStringUtils.randomAlphabetic(120);
        List<CategoryDto> categories = new ArrayList<>();
        categories.add(generateTestCategoryDto());
        categories.add(generateTestCategoryDto());
        categories.add(generateTestCategoryDto());
        return new StoreDto(id, 0, 0, name, null, 0l, null,
                0l, true, 0, description,  categories, null, null,
                null, false, null, false, null, null, null);
    }

    public CategoryDto generateTestCategoryDto() {
        String id = RandomStringUtils.random(10, true, true);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new CategoryDto(id, name);
    }
}
