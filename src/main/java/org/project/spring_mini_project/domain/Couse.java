package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

public class Couse {
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


}
