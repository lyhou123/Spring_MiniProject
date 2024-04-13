package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "countries")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //relationship city
    @ManyToMany(mappedBy = "countries", fetch = FetchType.LAZY)
    private Set<City> cities;
}
