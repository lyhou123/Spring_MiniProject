package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String alias;

    private Integer cat_id;
    private String description;
    private Integer ins_id;
    private Boolean is_deleted;
    private Boolean is_free;
    private String thumbnail;
    private String title;

    //category relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", insertable = false, updatable = false)
    private Categories category;

    //instructor relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ins_id", insertable = false, updatable = false)
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Enrollment enrollment;

}
