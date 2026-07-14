package co.istad.sengkim.elearninga01m1.features.student.dto;


import lombok.Builder;

@Builder
public record StudentProfileResponse (
        String userId,
        String email,
        String firstName,
        String lastName,
        String gender,
        String bio,
        String university,
        String major,
        String phoneNumber,
        String githubLink,
        String facebookLink
){
}
