package com.veer.studentdb.jwtrequest;

import com.veer.studentdb.entity.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JwtResponse {

	private User user;
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {
		log.info(this.getClass().getSimpleName() + " - jwt response");
		this.user = user;
		this.jwtToken = jwtToken;
	}

}
