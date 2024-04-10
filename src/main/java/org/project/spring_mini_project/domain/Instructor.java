package org.project.spring_mini_project.domain;

import jakarta.persistence.Column;

import java.math.BigInteger;

public class Instructor {
    private Integer id;
    private String text;
    private String family_name;
    private String github;
    private String given_name;
    private Boolean is_blocked;
    private Boolean is_deleted;
    private String job_title;
    private String linked_in;

    @Column(unique = true, nullable = false,length = 30)

    private String national_id_card;
    private String profile;

    //relationship user_id
    private BigInteger user_id;
    private String website;

}
