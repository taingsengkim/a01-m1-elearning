package co.istad.sengkim.elearninga01m1.features.instructor;

import co.istad.sengkim.elearninga01m1.config.config.BasedEntity;
import co.istad.sengkim.elearninga01m1.features.course.Course;
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
@Table(name = "instructor_profiles")
@Entity
@NoArgsConstructor
public class InstructorProfile extends BasedEntity {

    public InstructorProfile(String userId){
        this.userId = userId;
    }


    @Id
    private String userId; // the id is generated from keycloak

    private String biography;

    private String jobTitle;

    private String phoneNumber;

    private String githubLink;

    private String facebookLink;

    @OneToMany(mappedBy = "instructorProfile")
    private List<Course> courses;

}
