package co.istad.sengkim.elearninga01m1.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
public class KeycloakAdminClientProps {
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String realm; // master
    private String targetRealm;
}
