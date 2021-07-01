package com.veer.studentdb.page;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class StudentPage {

	private int pageNumber = 0;
	private int pageSize = 10;
	private Sort.Direction soDirection = Sort.Direction.ASC;
	private String sortBy = "studentId";

}
