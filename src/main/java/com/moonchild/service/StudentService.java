package com.moonchild.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonchild.domain.Student;
import com.moonchild.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public void saveStudent(Student newStudent) throws Exception {
		try {
			studentRepository.save(newStudent);
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void deleteStudent(Integer idStudent) throws Exception {
		try {
			studentRepository.deleteById(idStudent);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void updateStudent(Integer idStudent, Student newStudent) {
		Student student = studentRepository.getById(idStudent);
		
		if(	student != null ) {
			student.setName(newStudent.getName());
			student.setBirth_date(newStudent.getBirth_date());
			student.setResponsible(newStudent.getResponsible());
			student.setClass_id(newStudent.getClass_id());
			
			studentRepository.save(student);
		}else {
			throw new IllegalStateException("Student with id equals " + idStudent + " not found!");
		}
	}
	
	public Optional<Student> acessOneStudent(Integer idStudent) {
		Optional<Student> student = Optional.of(studentRepository.getById(idStudent));
		if(student.isPresent()) {
			return student;
		}else {
			throw new IllegalStateException("Student with id equals " + idStudent + " not found!");
		}
	}
	
	public List<Student> acessAllStudents(){
		return studentRepository.findAll();
	}
	
	/*public List<Student> acessStudentsByClass(SchoolClass classId){
		return studentRepository.findAllByClass(classId);
	}*/
	
}
