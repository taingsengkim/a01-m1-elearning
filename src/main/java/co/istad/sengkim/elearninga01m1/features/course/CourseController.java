package co.istad.sengkim.elearninga01m1.features.course;

import co.istad.sengkim.elearninga01m1.features.course.dto.CourseResponse;
import co.istad.sengkim.elearninga01m1.features.course.dto.CreateCourseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse createCourse(
            @Valid @RequestBody CreateCourseRequest createCourseRequest,
            @AuthenticationPrincipal Jwt jwt)
    {
        log.info("CREATE COURSE : {} , JWT : {}",createCourseRequest,jwt.getSubject());
        return courseService.createCourse(jwt,createCourseRequest);
    }
}
