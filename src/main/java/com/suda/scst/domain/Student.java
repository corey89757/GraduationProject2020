package com.suda.scst.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mark Angrish
 */
@NodeEntity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int student_id;

	private String gender;
	private int birth;
	private int age;

	@JsonIgnoreProperties("student")
	@Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
	private List<Role> roles;

	public Student() {
	}

	public Student(String name, int student_id, String gender, int birth, int age) {
		this.name = name;
		this.student_id = student_id;
		this.gender = gender;
		this.birth = birth;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStudent_id() {
		return student_id;
	}

	public String getGender() {
		return gender;
	}

	public int getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}
}