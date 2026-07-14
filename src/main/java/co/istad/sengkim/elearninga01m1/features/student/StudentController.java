package co.istad.sengkim.elearninga01m1.features.student;

import co.istad.sengkim.elearninga01m1.features.student.dto.StudentProfileResponse;
import co.istad.sengkim.elearninga01m1.features.student.dto.UpdateStudentProfileRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;
    @GetMapping("/me")
    public StudentProfileResponse me(){
        return service.me();
    }
    @PatchMapping
    public StudentProfileResponse update(@Valid @RequestBody UpdateStudentProfileRequest updateStudentProfileRequest){
        return service.updateProfile(updateStudentProfileRequest);
    }
}
