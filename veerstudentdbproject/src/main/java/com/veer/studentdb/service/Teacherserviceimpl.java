package com.veer.studentdb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veer.studentdb.Interface.TeacherService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.entity.Teacher;
import com.veer.studentdb.repository.CourseRepository;
import com.veer.studentdb.repository.TeacherRepository;
import com.veer.studentdb.request.TeacherRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Teacherserviceimpl implements TeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	CourseRepository courseRepository;

	public Teacher addTeacher(TeacherRequest teacherRequest) throws Exception {

		log.info(this.getClass().getSimpleName() + " - addTeacher Request is invoked ");
		Teacher teacher = new Teacher();
		System.out.println(teacher.toString());
		teacher.setTeacherName(teacherRequest.getTeacherName());
		teacher.setTeacherAge(teacherRequest.getTeacherAge());

		Optional<Course> course = courseRepository.findById(teacherRequest.getCourse_id());
		if (!course.isPresent()) {
			throw new Exception("Course id not found");
		} else {
			teacher.setCourse(course.get());
		}

		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getTeacherById(int stuid) {
		return teacherRepository.findById(stuid);
	}

	@Override
	public Teacher updateTeacher(TeacherRequest teacherRequest, int teacherId) throws Exception {
		log.info("Upadte teacher service method");
		Teacher teacher = new Teacher();
		Optional<Teacher> teach = teacherRepository.findById(teacherId);
		if (!teach.isPresent()) {
			throw new Exception("Could not find teacher with id- " + teach);
		} else {

			teacher.setTeacherName(teacherRequest.getTeacherName());
			teacher.setTeacherAge(teacherRequest.getTeacherAge());

			Optional<Course> Course = courseRepository.findById(teacherRequest.getCourse_id());
			if (!Course.isPresent()) {
				throw new Exception("Course id not found");
			} else {
				teacher.setCourse(Course.get());
			}

			teacher.setTeacherId(teacherId);
		}
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacherById(int teacherId) throws Exception {

		log.info(this.getClass().getSimpleName() + " - Delete Teacher by id is invoked.");
		Optional<Teacher> teach = teacherRepository.findById(teacherId);
		if (!teach.isPresent())
			throw new Exception("Could not find Teacher with id- " + teacherId);

		teacherRepository.deleteById(teacherId);
	}

	@Override
	public void deleteAllTeachers() {
		teacherRepository.deleteAll();
	}

}
