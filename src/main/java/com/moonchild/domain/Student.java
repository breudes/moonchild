package com.moonchild.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private Date birth_date;
	
	@Column(name = "responsible", nullable = false)
	private String responsible;
	
	@ManyToOne
    @JoinColumn(name="class_id")
	private SchoolClass class_id;
	
	public Student() {
		super();
	}

	public Student(Integer id, String name, Date birth_date, String responsible, SchoolClass class_id) {
		super();
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.responsible = responsible;
		this.class_id = class_id;
	}

	public Student(Integer id, String name, Date birth_date, String responsible) {
		super();
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.responsible = responsible;
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

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public SchoolClass getClass_id() {
		return class_id;
	}

	public void setClass_id(SchoolClass class_id) {
		this.class_id = class_id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birth_date=" + birth_date + ", responsible=" + responsible
				+ ", class_id=" + class_id + "]";
	}
	
}
