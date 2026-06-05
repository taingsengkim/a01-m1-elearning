package co.istad.sengkim.elearninga01m1.features.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Boolean  existsBySlug(String slug);


}
