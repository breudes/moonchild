package com.moonchild.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonchild.domain.SchoolClass;
import com.moonchild.domain.Teacher;
import com.moonchild.repository.SchoolClassRepository;
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
				SchoolClass schoolClass = schoolClassService.accessOneClass(newTeacher.getClasses().get(i).getId());
				
				if(!schoolClass.equals(null)) {
					schoolClass.getTeachers().add(newTeacher);
					newTeacher.addSchoolClass(schoolClass);
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
				int classesSize = teacher.getClasses().size();
				for(int i=0; i<classesSize; i++) {
					SchoolClass schoolClass = schoolClassService.accessOneClass(teacher.getClasses().get(i).getId());
					
					if(!schoolClass.equals(null)) {
						schoolClass.getTeachers().remove(teacher);
					}
				}
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
				
				List<SchoolClass> newList = new ArrayList<SchoolClass>();
				teacher.setClasses(new ArrayList<SchoolClass>());
				
				int classesSize = newTeacher.getClasses().size();
				for(int i=0; i<classesSize; i++) {
					SchoolClass schoolClass = schoolClassService.accessOneClass(newTeacher.getClasses().get(i).getId());
					schoolClass.setTeachers(new ArrayList<Teacher>());
					
					if(!schoolClass.equals(null)) {
						schoolClass.getTeachers().add(teacher);
						teacher.addSchoolClass(schoolClass);
					}
				}
			}
			
			teacherRepository.save(teacher);

		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public Teacher acessOneTeacher(Integer idTeacher) {
		Optional<Teacher> teacher = Optional.of(teacherRepository.getById(idTeacher));
		
		if(teacher.isPresent()) {
			return teacher.get();
		}else {
			throw new IllegalStateException();
		}
	}
	
	public List<Teacher> accessAllTeachers(){
		return teacherRepository.findAll();
	}
	
	/*public List<Teacher> findTeachersByClasses(Integer classId){
		return teacherRepository.findAllByClasses(classId);
	}*/
		
}
