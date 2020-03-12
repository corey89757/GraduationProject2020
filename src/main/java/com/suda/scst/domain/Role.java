package com.suda.scst.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.*;

/**
 * @author Mark Angrish
 */
@RelationshipEntity(type = "ACTED_IN")
public class Role {

    @Id
    @GeneratedValue
	private Long id;
	private List<String> roles = new ArrayList<>();

	@StartNode
	private Person person;

	@EndNode
	private Student student;

	public Role() {
	}

	public Role(Student student, Person actor) {
		this.student = student;
		this.person = actor;
	}

	public Long getId() {
	    return id;
	}

	public List<String> getRoles() {
	    return roles;
	}

	public Person getPerson() {
	    return person;
	}

	public Student getMovie() {
	    return student;
	}

    public void addRoleName(String name) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(name);
    }
}