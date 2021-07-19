package com.moonchild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonchild.domain.SchoolClass;
import com.moonchild.repository.SchoolClassRepository;

@Service
public class SchoolClassService {
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	
	public SchoolClassService(SchoolClassRepository schoolClassRepository) {
		this.schoolClassRepository = schoolClassRepository;
	}
	
	public void saveSchoolClass(SchoolClass schoolClass) throws Exception {
		try {
			schoolClassRepository.save(schoolClass);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void deleteSchoolClass(Integer classId) throws Exception {
		try {
			//remove class id for students
			//remove class id for teachers
			schoolClassRepository.deleteById(classId);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void updateSchoolClass(Integer classId, SchoolClass schoolClass) throws Exception {
		try {
			SchoolClass oldSchoolClass = this.accessOneClass(classId);
			if(oldSchoolClass!=null) {
				oldSchoolClass.setName(schoolClass.getName());
				oldSchoolClass.setChildren(schoolClass.getChildren());
				oldSchoolClass.setTeachers(schoolClass.getTeachers());
			}
			schoolClassRepository.save(schoolClass);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	/*public SchoolClass findByTeacher(Integer teacherId) {
		return schoolClassRepository.findByTeacher(teacherId);
	}*/
	
	public SchoolClass accessOneClass(Integer classId) {
		return schoolClassRepository.getById(classId);
	}
	public List<SchoolClass> listAllClasses(){
		return schoolClassRepository.findAll();
	}
}