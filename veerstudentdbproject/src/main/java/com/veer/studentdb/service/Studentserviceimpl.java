package com.veer.studentdb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veer.studentdb.Interface.StudentService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.entity.Student;
import com.veer.studentdb.repository.CourseRepository;
import com.veer.studentdb.repository.StudentRepository;
import com.veer.studentdb.request.StudentRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Studentserviceimpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	public Student addStudent(StudentRequest StudentRequest) throws Exception {

		Student student = new Student();
		System.out.println(student.toString());
		student.setStudentName(StudentRequest.getStudentName());
		student.setStudentDepartment(StudentRequest.getStudentDepartment());
		student.setStudentAge(StudentRequest.getStudentAge());

		Optional<Course> course = courseRepository.findById(StudentRequest.getCourse_id());
		if (!course.isPresent()) {
			throw new Exception("Course id not found");
		} else {
			student.setCourse(course.get());
		}

		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(int stuid) {
		return studentRepository.findById(stuid);
	}

	@Override
	public Student addNewStudent(Student stu) {
		return studentRepository.save(stu);
	}

	@Override
	public Student updateStudent(StudentRequest studentRequest, int studentId) throws Exception {
		log.info("Upadte coin service method");
		Student student = new Student();
		Optional<Student> stud = studentRepository.findById(studentId);
		if (!stud.isPresent()) {
			throw new Exception("Could not find Student with id- " + stud);
		} else {

			student.setStudentName(studentRequest.getStudentName());
			student.setStudentAge(studentRequest.getStudentAge());
			student.setStudentDepartment(studentRequest.getStudentDepartment());

			Optional<Course> Course = courseRepository.findById(studentRequest.getCourse_id());
			if (!Course.isPresent()) {
				throw new Exception("Course id not found");
			} else {
				student.setCourse(Course.get());
			}

			student.setStudentId(studentId);
		}
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(int studentId) throws Exception {
		Optional<Student> stu = studentRepository.findById(studentId);
		if (!stu.isPresent())
			throw new Exception("Could not find student with id- " + studentId);
		studentRepository.deleteById(studentId);
	}

	@Override
	public void deleteAllStudents() {
		studentRepository.deleteAll();
	}
}