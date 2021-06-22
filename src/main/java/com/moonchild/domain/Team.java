package com.moonchild.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="team",schema="public")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy="class_id",
			fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE})
	private List<Student> children;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE})
	@JoinTable(
	  name = "team_teacher", 
	  joinColumns = @JoinColumn(name = "team_id"), 
	  inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> professors;

	public Team() {
		super();
	}

	public Team(Integer id, String name, List<Student> children, List<Teacher> professors) {
		super();
		this.id = id;
		this.name = name;
		this.children = children;
		this.professors = professors;
	}

	public Team(Integer id, String name) {
		super();
		this.id = id;
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

	public List<Teacher> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Teacher> professors) {
		this.professors = professors;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", children=" + children + ", professors=" + professors + "]";
	}
	
}
