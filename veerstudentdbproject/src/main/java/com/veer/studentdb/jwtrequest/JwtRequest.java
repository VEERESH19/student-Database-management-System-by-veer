package com.veer.studentdb.jwtrequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JwtRequest {

	private String userName;
	private String userPassword;

	public JwtRequest() {
		log.info("--  JwtRequest");
	}

}
