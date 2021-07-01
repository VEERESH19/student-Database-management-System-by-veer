package com.veer.studentdb.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.veer.studentdb.Interface.RoleServInter;
import com.veer.studentdb.entity.Role;
import com.veer.studentdb.repository.RoleRepo;
import com.veer.studentdb.request.RoleRequest;

@Service
public class RoleService implements RoleServInter {

	@Autowired
	private RoleRepo roleRepo;

	public Role addRole(RoleRequest roleRequest) {

		Role role = new Role();
		System.out.println(role.toString());
		role.setRoleName(roleRequest.getRoleName());
		role.setRoleDescription(roleRequest.getRoleDescription());

		return roleRepo.save(role);
	}
}
