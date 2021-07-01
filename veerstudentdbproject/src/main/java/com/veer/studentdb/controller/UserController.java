package com.veer.studentdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.Interface.UserContIntrf;
import com.veer.studentdb.entity.User;
import com.veer.studentdb.request.UserRequest;
import com.veer.studentdb.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController implements UserContIntrf {

	@Autowired
	private UserService userService;

	@Override
	@PostMapping("/adduser")
	public User addUser(@RequestBody UserRequest userRequest) {

		System.out.println("User controller");
		return userService.addUser(userRequest);
	}
}
