package com.veer.studentdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veer.studentdb.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
