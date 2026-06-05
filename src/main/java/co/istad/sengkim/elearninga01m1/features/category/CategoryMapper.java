package co.istad.sengkim.elearninga01m1.features.category;

import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CreateCategoryRequest request);
}
