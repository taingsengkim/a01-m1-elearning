package co.istad.sengkim.elearninga01m1.config.config;

import co.istad.sengkim.elearninga01m1.config.props.KeycloakAdminClientProps;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakAdminClientConfig {
    private final KeycloakAdminClientProps props;
    @Bean
    public Keycloak configKeycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(props.getBaseUrl())
                .realm(props.getTargetRealm())
                .clientId(props.getClientId())
                .clientSecret(props.getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
