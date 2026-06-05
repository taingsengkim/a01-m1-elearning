package co.istad.sengkim.elearninga01m1.features.course.dto;

import co.istad.sengkim.elearninga01m1.features.category.Category;
import co.istad.sengkim.elearninga01m1.features.category.dto.CategoryResponse;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CourseResponse(
        String slug,
        String keyword,
        String title,
        String description,
        String thumbnail,
        Integer countRating,
        Float totalHours,
        String level,
        BigDecimal price,
        Float discountPercent,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        CategoryResponse category,
        String instructorName,
        Boolean isPublished
) {
}
