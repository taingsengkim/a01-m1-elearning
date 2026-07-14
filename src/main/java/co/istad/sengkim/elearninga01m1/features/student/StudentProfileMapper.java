package co.istad.sengkim.elearninga01m1.features.student;

import co.istad.sengkim.elearninga01m1.features.student.dto.StudentProfileResponse;
import co.istad.sengkim.elearninga01m1.features.student.dto.UpdateStudentProfileRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class StudentProfileMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract void mapUpateStudentProfileRequestToStudentProfile(UpdateStudentProfileRequest updateStudentProfileRequest,
                                                                          @MappingTarget StudentProfile studentProfile);

    public StudentProfileResponse toStudentProfileResponse(StudentProfile studentProfile, UserRepresentation userRepresentation){
        return StudentProfileResponse.builder()
                .userId(userRepresentation.getId())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .bio(userRepresentation.getAttributes().get("biography").getFirst())
                .gender(userRepresentation.getAttributes().get("gender").getFirst())
                .major(studentProfile.getMajor())
                .university(studentProfile.getUniversity())
                .facebookLink(studentProfile.getFacebookLink())
                .githubLink(studentProfile.getGithubLink())
                .phoneNumber(studentProfile.getPhoneNumber())
                .build();
    }
}
