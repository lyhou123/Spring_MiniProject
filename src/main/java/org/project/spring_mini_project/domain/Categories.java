package org.project.spring_mini_project.domain;

public class Categories {
    private Long id;
    private String alias;
    private String icon;
    private Boolean isdeleted;
    private String name;

    //relationship parent category
    private Integer parent_category_id;
}
