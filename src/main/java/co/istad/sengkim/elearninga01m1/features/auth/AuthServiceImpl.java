package co.istad.sengkim.elearninga01m1.features.auth;

import co.istad.sengkim.elearninga01m1.config.props.KeycloakAdminClientProps;
import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterRequest;
import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterResponse;
import co.istad.sengkim.elearninga01m1.features.student.StudentProfile;
import co.istad.sengkim.elearninga01m1.features.student.StudentRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final Keycloak keycloak;
    private final KeycloakAdminClientProps props;
    private final StudentRepository studentRepository;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(!registerRequest.password().equals(registerRequest.confirmedPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password don't match!");
        }

        UserRepresentation user= new UserRepresentation();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        //Set custom attributes
        //if the fields doesn't have default in keycloak :
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender",List.of(registerRequest.gender().getGender()));
        attributes.put("biography",List.of(registerRequest.biography()));
        user.setAttributes(attributes);

        //Keycloak System Data
        user.setEnabled(true);
        user.setEmailVerified(false);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType("password");
        credential.setValue(registerRequest.password());
        user.setCredentials(List.of(credential));

        //Call Keycloak API
        UsersResource usersResource =   keycloak.realm(props.getTargetRealm()).users();
        log.info("User Resource : {}", usersResource.userProfile());
       try (Response response = usersResource.create(user);){
           log.info("Status : {}",response.getStatus());
           log.info("Response : {}",response);

           if(response.getStatus()==HttpStatus.CREATED.value()){
              UserRepresentation createdUser = usersResource.search(user.getUsername()).getFirst();
               log.info("Created User : {}",createdUser.getId());

               UserResource userResource = keycloak.realm(props.getTargetRealm()).users().get(createdUser.getId());
               userResource.sendVerifyEmail();
               RolesResource roleResource = keycloak.realm(props.getTargetRealm()).roles();
               RoleRepresentation roleStudent = roleResource.get(RoleEnum.STUDENT.name()).toRepresentation();
//               RoleRepresentation roleUser = roleResource.get(RoleEnum.USER.name()).toRepresentation();
//               RoleRepresentation roleAdmin = roleResource.get(RoleEnum.ADMIN.name()).toRepresentation();
//               RoleRepresentation roleConstructor = roleResource.get(RoleEnum.INSTRUCTOR.name()).toRepresentation();
//               RoleRepresentation roleSuperAdmin = roleResource.get(RoleEnum.SUPER_ADMIN.name()).toRepresentation();
               log.info("Check role from keycloak {}",roleStudent);
               userResource.roles()
                       .realmLevel()
                       .add(List.of(roleStudent));
               StudentProfile studentProfile = new StudentProfile();
               studentProfile.setUserId(createdUser.getId());
               studentRepository.save(studentProfile);
               return RegisterResponse.builder()
                       .id(createdUser.getId())
                       .username(createdUser.getUsername())
                       .email(createdUser.getEmail())
                       .firstName(createdUser.getFirstName())
                       .lastName(createdUser.getLastName())
                       .gender(createdUser.getAttributes().get("gender").getFirst())
                       .biography(createdUser.getAttributes().get("biography").getFirst())
                       .build();
           }
       }
        //Resource : ជា HTTP Client ( Api របស់ Keycloak )
        //Representation : ប្រើសម្រាប់ Manage realm, DAO - Consume API , Client if i want to use that api i will have the data that i will send to the server
        return null;
    }
}
