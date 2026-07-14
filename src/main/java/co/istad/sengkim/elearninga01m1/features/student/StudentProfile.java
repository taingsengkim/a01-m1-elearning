package co.istad.sengkim.elearninga01m1.features.student;

import co.istad.sengkim.elearninga01m1.config.config.BasedEntity;
import co.istad.sengkim.elearninga01m1.features.enrollment.Enrollment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "student_profiles")
@Entity
@NoArgsConstructor
public class StudentProfile extends BasedEntity {

    @Id
    private String userId; // the id is generated from keycloak

    private String profilePicture;

    private String phoneNumber;

    private String githubLink;

    private String facebookLink;

    private String university;

    private String major;



    @OneToMany(mappedBy = "studentProfile")
    private List<Enrollment> enrollments;

}
