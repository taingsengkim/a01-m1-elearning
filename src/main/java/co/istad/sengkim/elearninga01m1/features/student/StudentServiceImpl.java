package co.istad.sengkim.elearninga01m1.features.student;

import co.istad.sengkim.elearninga01m1.config.props.KeycloakAdminClientProps;
import co.istad.sengkim.elearninga01m1.features.student.dto.StudentProfileResponse;
import co.istad.sengkim.elearninga01m1.features.student.dto.UpdateStudentProfileRequest;
import co.istad.sengkim.elearninga01m1.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.registry.HttpServiceProxyRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final Keycloak keycloak;
    private final KeycloakAdminClientProps keycloakAdminClientProps;
    private final StudentProfileMapper studentProfileMapper;
    private final StudentRepository studentRepository;

    @Override
    public StudentProfileResponse me() {
        //Extract userId from JWT Access Token.
        String userId = AuthUtils.extractUserId();
        //retrieve profile from keycloak
        UserRepresentation userResource = keycloak.realm(keycloakAdminClientProps.getTargetRealm())
                .users()
                .get(userId).toRepresentation();
        log.info("User {} logged in ",userResource);
        StudentProfile studentProfile = studentRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student profile not found."));
        // retrieve profile from database table
        return studentProfileMapper.toStudentProfileResponse(studentProfile,userResource);
    }

    @Override
    public StudentProfileResponse updateProfile(UpdateStudentProfileRequest updateStudentProfileRequest) {
        String userId = AuthUtils.extractUserId();
        UserResource userResource = keycloak.realm(keycloakAdminClientProps.getTargetRealm())
                .users()
                .get(userId);
        UserRepresentation keycloakUser = userResource.toRepresentation();

        if(updateStudentProfileRequest.firstName() != null){
            keycloakUser.setFirstName(updateStudentProfileRequest.firstName());
        }
        if(updateStudentProfileRequest.lastName() != null){
            keycloakUser.setLastName(updateStudentProfileRequest.lastName());
        }

        Map<String, List<String>> attributes = new HashMap<>();
        if(updateStudentProfileRequest.gender() != null){
            attributes.put("gender",List.of(updateStudentProfileRequest.gender().getGender()));
        }
        if (updateStudentProfileRequest.bio()!=null){
            attributes.put("biography",List.of(updateStudentProfileRequest.bio()));
        }
        keycloakUser.setAttributes(attributes);
        userResource.update(keycloakUser);
        StudentProfile studentProfile = studentRepository.findById(keycloakUser.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student Profile has not been found."));
        studentProfileMapper.mapUpateStudentProfileRequestToStudentProfile(updateStudentProfileRequest,studentProfile);
        studentRepository.save(studentProfile);
        return studentProfileMapper.toStudentProfileResponse(studentProfile,keycloakUser);
    }
}
