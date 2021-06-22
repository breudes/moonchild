package com.moonchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moonchild.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
