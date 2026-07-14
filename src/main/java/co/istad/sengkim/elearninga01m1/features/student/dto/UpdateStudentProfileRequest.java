package co.istad.sengkim.elearninga01m1.features.student.dto;

import co.istad.sengkim.elearninga01m1.features.auth.dto.GenderOption;
import lombok.Builder;

@Builder
public record UpdateStudentProfileRequest(
        String email,
        String firstName,
        String lastName,
        GenderOption gender,
        String bio,
        String university,
        String major,
        String phoneNumber,
        String githubLink,
        String facebookLink
) {

}
