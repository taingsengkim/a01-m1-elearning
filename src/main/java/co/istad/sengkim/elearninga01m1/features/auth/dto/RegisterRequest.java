package co.istad.sengkim.elearninga01m1.features.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Username is required")
        @Size(min = 3 , max = 255)
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 255)
        String password,
        @NotBlank(message = "Confirmed password is required")
        @Size(min = 8, max = 255)
        String confirmedPassword,
        @NotBlank(message = "Email is required")
        String email,
        String firstName,
        String lastName,
        GenderOption gender,
        String biography
) {
}
