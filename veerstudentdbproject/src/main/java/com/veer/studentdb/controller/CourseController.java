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

import com.veer.studentdb.Interface.CourseService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.request.CourseRequest;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher','Student')")
	@ApiOperation(value = "getAll Course")
	public List<Course> getCourses() {
		log.info(this.getClass().getSimpleName() + "  - Get all Courses CourseService is invoked ");
		return courseService.getCourses();
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher','Student')")
	@ApiOperation(value = "Get Course details by id", notes = "Enter Correct courseId")
	public Course getCourseById(@PathVariable int courseId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Get Course details by id is invoked ");
		Optional<Course> cour = courseService.getCourseById(courseId);
		if (!cour.isPresent())
			throw new Exception("Could not find Course with id- " + courseId);

		return cour.get();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Create new Course")
	public Course createCourse(@RequestBody CourseRequest courseRequest) {
		log.info(this.getClass().getSimpleName() + " - Create new Course method is invoked ");
		return courseService.addCourse(courseRequest);
	}

	@RequestMapping(value = "/update/{courseId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Get Course details by id", notes = "Enter Correct courseId")
	public Course updateCourse(@RequestBody CourseRequest courseRequest, @PathVariable int courseId) throws Exception {
		log.info("update course details");
		return courseService.updateCourse(courseRequest, courseId);
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	@ApiOperation(value = "Delete all Courses")
	public void deleteAll() {
		log.info(this.getClass().getSimpleName() + " - Delete all Courses is invoked ");
		courseService.deleteAllCourses();
	}

}
