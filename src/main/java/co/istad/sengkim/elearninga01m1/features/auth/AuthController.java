package co.istad.sengkim.elearninga01m1.features.auth;

import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterRequest;
import co.istad.sengkim.elearninga01m1.features.auth.dto.RegisterResponse;
import co.istad.sengkim.elearninga01m1.features.student.StudentService;
import co.istad.sengkim.elearninga01m1.features.student.dto.StudentProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final StudentService studentService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest){
        log.info(" SERVICE CALLED ===");

        return authService.register(registerRequest);
    }

    @GetMapping("/me")
    public StudentProfileResponse me(){
        return studentService.me();
    }

}
