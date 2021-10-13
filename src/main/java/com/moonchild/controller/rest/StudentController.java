package com.moonchild.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moonchild.domain.Student;
import com.moonchild.service.StudentService;

@RestController
@RequestMapping("api/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/save")
	public void saveStudent(@RequestBody Student student) throws Exception {
		try {
			studentService.saveStudent(student);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/update/{id}")
	public void updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		try {
			studentService.updateStudent(id, student);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") Integer id) throws Exception {
		try {
			studentService.deleteStudent(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@GetMapping("/get/{id}")
	public Optional<Student> getOneStudent(@PathVariable Integer id) {
		try {
			return studentService.accessOneStudent(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	@GetMapping("/getAll")
	public List<Student> getAllStudents(){
		try {
			return studentService.accessAllStudents();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
