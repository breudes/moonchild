package com.moonchild.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moonchild.controller.rest.StudentController;
import com.moonchild.domain.SchoolClass;
import com.moonchild.domain.Student;
import com.moonchild.domain.Teacher;
import com.moonchild.service.SchoolClassService;
import com.moonchild.service.StudentService;
import com.moonchild.service.TeacherService;

@Controller
@RequestMapping("moonchild")
public class MoonchildController {
	@Autowired
	private StudentController studentController;
	
	@GetMapping("/student/list")
	public ModelAndView listStudents() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("student/list");
		model.addObject("studentsList", studentController.getAllStudents());
		
		return model;
	}
	
	@GetMapping("/student/delete/{id}")
	public ModelAndView deleteStudent(@PathVariable Integer id) {
		
		try {
			studentController.deleteStudent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStudents();
	}
	
	@GetMapping("/student/update/{id}")
	public ModelAndView updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		
		try {
			studentController.updateStudent(id, student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStudents();
	}
	
	@GetMapping("/student/form")
	public ModelAndView formStudent(ModelAndView model) {
		
		Student student = new Student();
		
		model.setViewName("student/form");
		model.addObject("student", student);
		
		return model;
	}
	
	@PostMapping("/student/save")
	public ModelAndView saveStudent(ModelAndView model, @RequestBody Student student) {
		
		try {
			studentController.saveStudent(student);
			return listStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("student", student);
		
		return formStudent(model);
	}
	
	/*@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SchoolClassService schoolClassService;
	
	@PostMapping("/schoolclass/save")
	public void saveSchoolClass(@RequestBody SchoolClass schoolClass) throws Exception {
		try {
			schoolClassService.saveSchoolClass(schoolClass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/schoolclass/update/{id}")
	public void updateSchoolClass(@PathVariable Integer id,@RequestBody SchoolClass schoolClass) {
		try {
			schoolClassService.updateSchoolClass(id, schoolClass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/schoolclass/delete/{id}")
	public void deleteSchoolClass(@PathVariable("id") Integer id) throws Exception {
		try {
			schoolClassService.deleteSchoolClass(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/schoolclass/get/{id}")
	public Optional<SchoolClass> getOneSchoolClass(@PathVariable Integer id) {
		try {
			return schoolClassService.accessOneClass(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/schoolclass/getAll")
	public List<SchoolClass> getAllSchoolClasses(){
		try {
			return schoolClassService.listAllClasses();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/teacher/save")
	public void saveTeacher(@RequestBody Teacher teacher) throws Exception {
		try {
			teacherService.saveTeacher(teacher);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/teacher/update/{id}")
	public void updateTeacher(@PathVariable Integer id,@RequestBody Teacher teacher) {
		try {
			teacherService.updateTeacher(id, teacher);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/teacher/delete/{id}")
	public void deleteTeacher(@PathVariable("id") Integer id) throws Exception {
		try {
			teacherService.deleteTeacher(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/teacher/get/{id}")
	public Optional<Teacher> getOneTeacher(@PathVariable Integer id) {
		try {
			return teacherService.accessOneTeacher(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/teacher/getAll")
	public List<Teacher> getAllTeachers(){
		try {
			return teacherService.accessAllTeachers();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/student/save")
	public void saveStudent(@RequestBody Student student) throws Exception {
		try {
			studentService.saveStudent(student);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/student/update/{id}")
	public void updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		try {
			studentService.updateStudent(id, student);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable("id") Integer id) throws Exception {
		try {
			studentService.deleteStudent(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/student/get/{id}")
	public Optional<Student> getOneStudent(@PathVariable Integer id) {
		try {
			return studentService.accessOneStudent(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/student/getAll")
	public List<Student> getAllStudents(){
		try {
			return studentService.accessAllStudents();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
