package org.testtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.testtask.model.Category;
import org.testtask.model.dto.CategoryDto;
import org.testtask.model.Store;
import org.testtask.model.dto.StoreDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    /**
     * Maps StoreDto into Store object for further serialization.
     * @param source Store data transfer object.
     * @return Mapped store object.
     */
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "categories", source = "source.categories")
    Store mapStore(StoreDto source);

    /**
     * Maps CategoryDto into Category object. A helper mapper method for store mapping.
     * @param source Category data transfer object.
     * @return Mapped category object.
     */
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "name", source = "source.name")
    Category mapCategory(CategoryDto source);

    /**
     * Maps list of stores using the according mapping method
     * @param source List of store data transfer objects
     * @return List of mapped stores
     */
    List<Store> mapStores(List<StoreDto> source);

    /**
     * Maps list of store categories using the according mapping method
     * @param source List of store category data transfer objects
     * @return List of mapped store categories
     */
    List<Category> mapCategories(List<CategoryDto> source);
}
