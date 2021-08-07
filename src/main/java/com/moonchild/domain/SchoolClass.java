package com.moonchild.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="school_class",schema="public")
public class SchoolClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private Integer id;
	
	@Column(name = "name", length=25, nullable = false)
	private String name;
	
	@OneToMany(
			mappedBy="class_id",
			fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL }
	)
	@JsonIgnoreProperties("class_id")
	private List<Student> children;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "class_teacher", 
		joinColumns = { @JoinColumn(name = "class_id", updatable=true) }, 
		inverseJoinColumns = { @JoinColumn(name = "teacher_id", updatable=true) })
	@JsonIgnoreProperties("classes")
	private List<Teacher> teachers = new ArrayList<Teacher>();

	public SchoolClass() {
		super();
	}
	
	public SchoolClass(Integer id, String name, List<Student> children, List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.children = children;
		this.teachers = teachers;
	}

	public SchoolClass(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SchoolClass(String name) {
		super();
		this.name = name;
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

	public List<Student> getChildren() {
		return children;
	}

	public void setChildren(List<Student> children) {
		this.children = children;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "SchoolClass [id=" + id + ", name=" + name + ", children=" + children + ", teachers=" + teachers
				+ "]";
	}
	
}
