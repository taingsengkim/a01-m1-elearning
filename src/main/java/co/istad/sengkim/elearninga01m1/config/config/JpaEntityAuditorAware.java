package co.istad.sengkim.elearninga01m1.config.config;

import co.istad.sengkim.elearninga01m1.security.AuthUtils;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaEntityAuditorAware implements AuditorAware<String> {
    @NullMarked
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(AuthUtils.extractUserId());
    }
}
