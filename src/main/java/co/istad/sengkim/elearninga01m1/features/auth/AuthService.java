package co.istad.sengkim.elearninga01m1.features.auth;

import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterRequest;
import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
}
