package co.istad.sengkim.elearninga01m1.features.category;


import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import co.istad.sengkim.elearninga01m1.features.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService ){
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<CategoryResponse> getAllCategories(
        @RequestParam(required = false,defaultValue ="0") int pageNumber,
        @RequestParam(required = false,defaultValue = "10") int pageSize
    ){
        return categoryService.allCategories(pageNumber,pageSize);
    }

    @PostMapping
    public CategoryResponse addCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.addCategory(createCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id,@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.updateCategoryById(id,updateCategoryRequest);
    }
}
