package co.istad.sengkim.elearninga01m1.features.course;

import co.istad.sengkim.elearninga01m1.features.category.dto.CreateCategoryRequest;
import co.istad.sengkim.elearninga01m1.features.course.dto.CourseResponse;
import co.istad.sengkim.elearninga01m1.features.course.dto.CreateCourseRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface CourseService {
    CourseResponse createCourse(Jwt jwt, CreateCourseRequest createCourseRequest);


}
