package co.istad.sengkim.elearninga01m1.features.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @NotBlank(message = "Category name is required")
        @Size(max = 50)
        String name,
        @Size(max=150)
        String icon
) {
}
