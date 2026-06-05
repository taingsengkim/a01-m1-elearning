package co.istad.sengkim.elearninga01m1.features.course;


import co.istad.sengkim.elearninga01m1.features.category.Category;
import co.istad.sengkim.elearninga01m1.features.enrollment.Enrollment;
import co.istad.sengkim.elearninga01m1.features.instructor.InstructorProfile;
import co.istad.sengkim.elearninga01m1.features.video.Video;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.GenericSignatureFormatError;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String slug;

    private String keyword;

    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    private String thumbnail;

    private Integer countRating;
    private Integer raitingStar;
    private Float totalHours;
    private String level;
    private BigDecimal price;
    private Float discountPercent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private Boolean isDeleted;
    private Boolean isPublished;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "course")
    private List<Video> videos;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorProfile instructorProfile;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}
