package com.veer.studentdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Table(name = "teacher")
@Slf4j
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teacher_id")
	private int teacherId;

	@Column(name = "teacher_name")
	private String teacherName;

	@Column(name = "teacher_age")
	private int teacherAge;

	@OneToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id")
	private Course course;

	public Teacher() {
		log.info(" -- Teacher Entity");
	}

}
