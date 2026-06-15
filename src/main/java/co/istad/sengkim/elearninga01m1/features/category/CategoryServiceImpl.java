package co.istad.sengkim.elearninga01m1.features.category;

import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import co.istad.sengkim.elearninga01m1.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org  .springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryResponse> allCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryRepository.findAllByIsDeleted(false,pageable);
        return categoryPage.map(d->new CategoryResponse(d.getId(),d.getName(),d.getIcon()));
    }

    @Override
    public CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest) {
        Category existCategory = categoryRepository.findByName(createCategoryRequest.name());
        if(existCategory!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Category with this name is already exist!");
        }

        Category category = new Category();
        category.setName(createCategoryRequest.name());
        category.setIcon(createCategoryRequest.icon());
        category.setIsDeleted(false);
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getIcon()
        );
    }

    @Override
    public void deleteCategory(Integer id) {
        Category existCategory = categoryRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));
        if(existCategory.getIsDeleted().equals(true)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This category already deleted");
        }
        existCategory.setIsDeleted(true);
        categoryRepository.save(existCategory);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category existCategory = categoryRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));
        if(updateCategoryRequest.name()!=null){
            existCategory.setName(updateCategoryRequest.name());
        }
        if(updateCategoryRequest.icon()!=null){
            existCategory.setIcon(updateCategoryRequest.icon());
        }

        categoryRepository.save(existCategory);
        return new CategoryResponse(existCategory.getId(),existCategory.getName(),existCategory.getIcon());
    }
}
