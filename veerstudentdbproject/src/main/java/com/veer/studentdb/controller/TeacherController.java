package com.veer.studentdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.Interface.TeacherService;
import com.veer.studentdb.entity.Teacher;
import com.veer.studentdb.request.TeacherRequest;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher')")
	@ApiOperation(value = "get all student")
	public List<Teacher> getTeachers() {
		log.info(this.getClass().getSimpleName() + " - Get all Teachers teacherService is invoked.");
		return teacherService.getTeachers();
	}

	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher','Student')")
	@ApiOperation(value = "Get All student details")
	public Teacher getTeacherById(@PathVariable int teacherId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Get Teacher details by id is invoked.");
		Optional<Teacher> teach = teacherService.getTeacherById(teacherId);
		if (!teach.isPresent())
			throw new Exception("Could not find Teacher with id- " + teacherId);

		return teach.get();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "create student details")
	public Teacher createTeacher(@RequestBody TeacherRequest teacherRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new Teacher method is invoked.");
		return teacherService.addTeacher(teacherRequest);
	}

	@RequestMapping(value = "/update/{teacherId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "update Teacher details by id", notes = "enter correct id")
	public Teacher updateTeacher(@RequestBody TeacherRequest teacherRequest, @PathVariable int teacherId)
			throws Exception {
		log.info("update Student details");
		return teacherService.updateTeacher(teacherRequest, teacherId);
	}

	@RequestMapping(value = "/delete/{teacherId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Delete teacher details by id", notes = "enter correct id")
	public void deleteTeacherById(@PathVariable int teacherId) throws Exception {

		teacherService.deleteTeacherById(teacherId);
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Delete All teacher details")
	public void deleteAll() {
		log.info(this.getClass().getSimpleName() + " - Delete all Teachers is invoked.");
		teacherService.deleteAllTeachers();
	}

}
