package com.veer.studentdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.jwtrequest.JwtRequest;
import com.veer.studentdb.jwtrequest.JwtResponse;
import com.veer.studentdb.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/Jwtcontroller")
public class JwtController {

	@Autowired
	private JwtService jwtService;

	public JwtController() {
		log.info("-- jwt Auth");
	}

	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		log.info("-- create token service");
		return jwtService.createJwtToken(jwtRequest);
	}
}
