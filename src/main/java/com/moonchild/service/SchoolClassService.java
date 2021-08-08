package com.moonchild.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonchild.domain.SchoolClass;
import com.moonchild.domain.Student;
import com.moonchild.domain.Teacher;
import com.moonchild.repository.SchoolClassRepository;

@Service
public class SchoolClassService {
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	
	public SchoolClassService(SchoolClassRepository schoolClassRepository) {
		this.schoolClassRepository = schoolClassRepository;
	}
	
	public void saveSchoolClass(SchoolClass schoolClass) throws Exception {
		try {
			// add students
			schoolClass.getChildren().forEach(child ->{
				Optional<Student> student = studentService.accessOneStudent(child.getId());
				if(!student.equals(null)) {
					student.get().setClass_id(schoolClass);
					schoolClass.getChildren().add(student.get());
				}
			});
			// add teachers
			schoolClass.getTeachers().forEach(teacher ->{
				Optional<Teacher> professor = teacherService.accessOneTeacher(teacher.getId());
				if(!professor.equals(null)) {
					professor.get().addSchoolClass(schoolClass);
				}
			});
			// save class
			schoolClassRepository.save(schoolClass);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void deleteSchoolClass(Integer classId) throws Exception {
		try {
			Optional<SchoolClass> schoolClass = this.accessOneClass(classId);
			if(!schoolClass.equals(null)){
				//remove class for all related students
				removeChildrenFromSchoolClass(schoolClass.get());
				//remove class for all related teachers
				schoolClass.get().setTeachers(null);
			}
			//delete class
			schoolClassRepository.deleteById(classId);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void updateSchoolClass(Integer classId, SchoolClass schoolClass) throws Exception {
		try {
			Optional<SchoolClass> oldSchoolClass = this.accessOneClass(classId);
			if(!oldSchoolClass.equals(null)) {
				oldSchoolClass.get().setName(schoolClass.getName());
				oldSchoolClass.get().setChildren(schoolClass.getChildren());
				oldSchoolClass.get().setTeachers(schoolClass.getTeachers());
				
			}
			schoolClassRepository.save(schoolClass);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public Optional<SchoolClass> accessOneClass(Integer classId) {
		return schoolClassRepository.findById(classId);
	}
	public List<SchoolClass> listAllClasses(){
		return schoolClassRepository.findAll();
	}
	
	public void removeChildrenFromSchoolClass(SchoolClass schoolClass) {
		schoolClass.getChildren().forEach(child -> {
			Optional<Student> student = studentService.accessOneStudent(child.getId());
			if(!student.equals(null)) {
				student.get().setClass_id(null);
			}
			
			schoolClass.setChildren(null);
		});
	}
}