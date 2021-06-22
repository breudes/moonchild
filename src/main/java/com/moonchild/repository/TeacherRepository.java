package com.moonchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moonchild.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
