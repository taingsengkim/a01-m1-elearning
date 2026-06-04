package co.istad.sengkim.elearninga01m1.features.category;

import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import co.istad.sengkim.elearninga01m1.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<CategoryResponse> allCategories(int pageNumber,int pageSize);
    CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest);
    void deleteCategory(Integer id);
    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest createCategoryRequest);
}
