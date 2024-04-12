package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime certified_at;
    @Column(nullable = false)
    private String code;


    //relationship
    private long course_id;
    private LocalDateTime enrolled_at;
    private Boolean is_deleted;
    private Boolean is_certified;
    private Integer progress;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;
}
