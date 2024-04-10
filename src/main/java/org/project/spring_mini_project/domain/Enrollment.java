package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Enrollment {
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
    private BigInteger student_id;
}
