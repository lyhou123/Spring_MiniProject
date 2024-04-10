package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

public class Country {
    private long id;
    private String flag;

    @Column(nullable = false,length = 10)
    private String iso;

    @Column(nullable = false,length = 60)
    private String name;

    private String nice_name;

    @Column(nullable = false)

    private Integer num_code;

    @Column(nullable = false)

    private Integer phone_code;
}
