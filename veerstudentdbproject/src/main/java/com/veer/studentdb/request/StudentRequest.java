package com.veer.studentdb.request;

import lombok.Data;

@Data
public class StudentRequest {

	private String studentName;
	private String studentDepartment;
	private int studentAge;
	private int course_id;

}
