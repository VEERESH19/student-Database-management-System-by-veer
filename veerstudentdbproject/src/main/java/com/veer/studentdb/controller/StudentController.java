package com.veer.studentdb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.Interface.StudentService;
import com.veer.studentdb.entity.Student;
import com.veer.studentdb.request.StudentRequest;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Get all students")
	public List<Student> getStudents(@PageableDefault(sort = { "coinId" }) final Pageable page) {
		log.info(this.getClass().getSimpleName() + " - Get all students studentService is invoked ");
		return studentService.getStudents();
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher')")
	@ApiOperation(value = "Get student details by id", notes = "enter correct id")
	public Student getStudentById(@PathVariable int studentId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Get student details by id is invoked");
		Optional<Student> stu = studentService.getStudentById(studentId);
		if (!stu.isPresent())
			throw new Exception("Could not find student with id- " + studentId);

		return stu.get();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Create new Student")
	public Student createStudent(@RequestBody StudentRequest studentRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new Student method is invoked ");
		return studentService.addStudent(studentRequest);
	}

	@RequestMapping(value = "/update/{studentId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "update student details by id", notes = "enter correct id")
	public Student updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable int studentId)
			throws Exception {

		log.info("update Student details");
		return studentService.updateStudent(studentRequest, studentId);
	}

	@RequestMapping(value = "/delete/{studentId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Delete student details by id", notes = "enter correct id")
	public void deleteStudentById(@PathVariable int studentId) throws Exception {

		log.info(this.getClass().getSimpleName() + " - Delete student by id is invoked ");
		studentService.deleteStudentById(studentId);
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Delete all student details")
	public void deleteAll() {
		log.info(this.getClass().getSimpleName() + " - Delete all students is invoked");
		studentService.deleteAllStudents();
	}
}