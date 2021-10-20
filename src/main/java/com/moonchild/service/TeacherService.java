package com.moonchild.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonchild.domain.SchoolClass;
import com.moonchild.domain.Teacher;
import com.moonchild.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SchoolClassService schoolClassService;
	
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	public void saveTeacher(Teacher newTeacher) throws Exception {
		try {
			int classesSize = newTeacher.getClasses().size();
			for(int i=0; i<classesSize; i++) {
				Optional<SchoolClass> schoolClass = schoolClassService.accessOneClass(newTeacher.getClasses().get(i).getId());
				
				if(!schoolClass.equals(null)) {
					schoolClass.get().getTeachers().add(newTeacher);
					newTeacher.addSchoolClass(schoolClass.get());
				}
			}
			teacherRepository.save(newTeacher);
		}
		catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void deleteTeacher(Integer idTeacher) throws Exception {
		try {
			
			Teacher teacher = teacherRepository.getById(idTeacher);
			
			if( teacher != null ) {
				removeAllSchoolClassesFromTeacher(teacher);
			}
			
			teacherRepository.deleteById(idTeacher);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public void updateTeacher(Integer idTeacher, Teacher newTeacher) {
		try {
			Teacher teacher = teacherRepository.getById(idTeacher);
			
			if(	teacher != null ) {
				teacher.setName(newTeacher.getName());
				teacher.setBirth_date(newTeacher.getBirth_date());
				teacher.setTaught_subject(newTeacher.getTaught_subject());
			
				removeAllSchoolClassesFromTeacher(teacher);
				addAllSchoolClassesFromTeacher(newTeacher);
				
				teacher.setClasses(newTeacher.getClasses());
			}
			
			teacherRepository.save(teacher);

		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public Optional<Teacher> accessOneTeacher(Integer idTeacher) {		
		Optional<Teacher> teacher = teacherRepository.findById(idTeacher);
		if(teacher.isPresent()) {
			return teacher;
		}else {
			throw new IllegalStateException();
		}
	}
	
	public List<Teacher> accessAllTeachers(){
		return teacherRepository.findAll();
	}
	
	public void removeAllSchoolClassesFromTeacher(Teacher teacher) {
		teacher.getClasses().forEach(object -> {
			Optional<SchoolClass> schoolClass = schoolClassService.accessOneClass(object.getId());
			if(!schoolClass.equals(null)) {
				schoolClass.get().getTeachers().remove(teacher);
			}
		});
		
		teacher.setClasses(null);
	}
	
	public void addAllSchoolClassesFromTeacher(Teacher teacher) {
		teacher.getClasses().forEach(object -> {
			Optional<SchoolClass> schoolClass = schoolClassService.accessOneClass(object.getId());
			if(!schoolClass.equals(null)) {
				schoolClass.get().getTeachers().add(teacher);
			}
		});
	}
}
