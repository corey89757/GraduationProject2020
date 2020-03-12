package com.suda.scst.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Angrish
 */
@NodeEntity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String gender;
    private int birth;
    private int age;

    @JsonIgnoreProperties
    @Relationship(type = "STUDY_IN", direction = Relationship.OUTGOING)
    private List<Class> classList = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String gender, int birth, int age) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }


    public void addClass(Class clazz) {
        this.classList.add(clazz);
    }
}