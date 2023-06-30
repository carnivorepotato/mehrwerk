package org.testtask.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.testtask.model.Category;
import org.testtask.model.Store;
import org.testtask.model.dto.CategoryDto;
import org.testtask.model.dto.StoreDto;
import org.testtask.util.TestUtils;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreMapperImplTest {
    private final TestUtils testUtils = new TestUtils();
    @Spy
    private StoreMapper mapper = Mappers.getMapper(StoreMapper.class);

    @Test
    void testMapStore() {
        StoreDto store = testUtils.generateTestStoreDto();
        Store result = mapper.mapStore(store);

        Iterator<CategoryDto> categoryDtoIterator = store.categories().iterator();
        Iterator<Category> categoryIterator = result.getCategories().iterator();

        assertEquals(store.id(), result.getId());
        assertEquals(store.name(), result.getName());
        assertEquals(store.description(), result.getDescription());
        assertEquals(store.categories().size(), result.getCategories().size());
        while (categoryDtoIterator.hasNext() && categoryIterator.hasNext()) {
            CategoryDto categoryDto = categoryDtoIterator.next();
            Category category = categoryIterator.next();
            assertEquals(categoryDto.id(), category.getId());
            assertEquals(categoryDto.name(), category.getName());
        }

    }

    @Test
    void testMapCategory() {
        CategoryDto category = testUtils.generateTestCategoryDto();
        Category result = mapper.mapCategory(category);

        assertEquals(category.id(), result.getId());
        assertEquals(category.name(), result.getName());
    }
}