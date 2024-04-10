package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

public class City {
    private Integer id;
    private Integer country_id;

    @Column(nullable = false, length = 60)
    private String name;
}
