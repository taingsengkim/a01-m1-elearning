package co.istad.sengkim.elearninga01m1.features.enrollment;

import co.istad.sengkim.elearninga01m1.config.config.BasedEntity;
import co.istad.sengkim.elearninga01m1.features.course.Course;
import co.istad.sengkim.elearninga01m1.features.student.StudentProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "enrollments")
@Entity
public class Enrollment extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile studentProfile;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private Boolean paymentStatus;
    private LocalDateTime enrolledAt;
    private LocalDateTime paymentAt;
}
