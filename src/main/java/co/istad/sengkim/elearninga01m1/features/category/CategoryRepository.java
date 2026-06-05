package co.istad.sengkim.elearninga01m1.features.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
    Page<Category> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);
    Optional<Category> findByIdAndIsDeletedFalse(Integer id);
}
