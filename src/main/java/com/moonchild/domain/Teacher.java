package com.moonchild.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Integer id;
	
	@Column(name = "name", length=50, nullable = false)
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private Date birth_date;
	
	@Column(name = "taught_subject", length=25, nullable = false)
	private String taught_subject;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers")
	private List<SchoolClass> classes = new ArrayList<SchoolClass>();

	public Teacher() {
		super();
	}

	public Teacher(Integer id, String name, Date birth_date, String taught_subject, List<SchoolClass> classes) {
		super();
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.taught_subject = taught_subject;
		this.classes = classes;
	}

	public Teacher(Integer id, String name, Date birth_date, String taught_subject) {
		super();
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.taught_subject = taught_subject;
	}
	
	public Teacher(String name, Date birth_date, String taught_subject, List<SchoolClass> classes) {
		super();
		this.name = name;
		this.birth_date = birth_date;
		this.taught_subject = taught_subject;
		this.classes = classes;
	}
	
	public Teacher(String name, Date birth_date, String taught_subject) {
		super();
		this.name = name;
		this.birth_date = birth_date;
		this.taught_subject = taught_subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getTaught_subject() {
		return taught_subject;
	}

	public void setTaught_subject(String taught_subject) {
		this.taught_subject = taught_subject;
	}

	public List<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(List<SchoolClass> classes) {
		this.classes = classes;
	}
	
	public void addSchoolClass(SchoolClass schoolClass) {
		this.classes.add(schoolClass);
	}
	
	public void removeSchoolClass(SchoolClass schoolClass) {
		this.classes.remove(schoolClass);
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", birth_date=" + birth_date + ", taught_subject="
				+ taught_subject + ", classes=" + classes + "]";
	}
	
}
