package com.suda.scst.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public class Class1 {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String teacher;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }
}
