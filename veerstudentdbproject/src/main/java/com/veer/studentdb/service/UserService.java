package com.veer.studentdb.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.veer.studentdb.Interface.UserServIntr;
import com.veer.studentdb.entity.Role;
import com.veer.studentdb.entity.User;
import com.veer.studentdb.repository.RoleRepo;
import com.veer.studentdb.repository.UserRepo;
import com.veer.studentdb.request.UserRequest;

@Service
public class UserService implements UserServIntr {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passEncode;

	public String getEncodedPass(String user_password) {
		return passEncode.encode(user_password);

	}

	public User addUser(UserRequest userRequest) {
		User user = new User();
		Set<Role> roles = new HashSet<>();
		System.out.println(user.toString());
		user.setUserName(userRequest.getUserName());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmailId(userRequest.getEmailId());
		user.setUserPassword(getEncodedPass(userRequest.getUserPassword()));
		for (Integer role_id : userRequest.getRole()) {
			Optional<Role> role = roleRepo.findById(role_id);
			if (!role.isPresent()) {
			} else {
				roles.add(role.get());
			}
		}
		user.setRole(roles);
		return userRepo.save(user);
	}

}
