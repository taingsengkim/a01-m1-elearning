package co.istad.sengkim.elearninga01m1.features.video;

import co.istad.sengkim.elearninga01m1.features.comment.Comment;
import co.istad.sengkim.elearninga01m1.features.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String slug;
    private String title;
    private String description;
    private String thumbnail;
    private String duration;
    private String youtube;

    @ManyToOne
    private Course course;


    @OneToMany(mappedBy = "video")
    private List<Comment> comments;

    private Boolean isDeleted;
    private Boolean isPublished;


}
