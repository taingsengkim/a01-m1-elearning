package co.istad.sengkim.elearninga01m1.features.course;

import co.istad.sengkim.elearninga01m1.features.course.dto.CourseResponse;
import co.istad.sengkim.elearninga01m1.features.course.dto.CreateCourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course mapCreateCourseToCourse(CreateCourseRequest createCourseRequest);
    CourseResponse mapCourseToCourseResponse(Course course);
}
