package com.veer.studentdb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veer.studentdb.Interface.CourseService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.repository.CourseRepository;
import com.veer.studentdb.request.CourseRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	public Course addCourse(CourseRequest courseRequest) {

		Course course = new Course();
		log.info(course.toString());
		course.setCourseName(courseRequest.getCourseName());
		course.setCourseDuration(courseRequest.getCourseDuration());
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> getCourseById(int courid) {
		return courseRepository.findById(courid);
	}

	public Course updateCourse(CourseRequest courseRequest, int courseId) throws Exception {
		log.info("Upadte cour service method");
		Course course = new Course();
		Optional<Course> cour = courseRepository.findById(courseId);
		if (!cour.isPresent()) {
			throw new Exception("Could not find Student with id- " + cour);
		} else {

			course.setCourseName(courseRequest.getCourseName());
			course.setCourseDuration(courseRequest.getCourseDuration());

			course.setCourseId(courseId);
		}
		return courseRepository.save(course);

	}

	@Override
	public void deleteAllCourses() {
		courseRepository.deleteAll();
	}

}
