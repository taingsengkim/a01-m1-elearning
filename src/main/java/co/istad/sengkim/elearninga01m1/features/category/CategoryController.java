package co.istad.sengkim.elearninga01m1.features.category;


import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import co.istad.sengkim.elearninga01m1.features.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService ){
        this.categoryService = categoryService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CategoryResponse> getAllCategories(
        @RequestParam(required = false,defaultValue ="0") int pageNumber,
        @RequestParam(required = false,defaultValue = "10") int pageSize
    ){
        return categoryService.allCategories(pageNumber,pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse addCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.addCategory(createCategoryRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id,@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return categoryService.updateCategoryById(id,updateCategoryRequest);
    }
}
