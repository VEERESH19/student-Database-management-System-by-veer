package com.veer.studentdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veer.studentdb.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}