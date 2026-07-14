package co.istad.sengkim.elearninga01m1.features.student;

import co.istad.sengkim.elearninga01m1.features.student.dto.StudentProfileResponse;
import co.istad.sengkim.elearninga01m1.features.student.dto.UpdateStudentProfileRequest;

public interface StudentService {
    StudentProfileResponse me();
    StudentProfileResponse updateProfile(UpdateStudentProfileRequest updateStudentProfileRequest);
}
