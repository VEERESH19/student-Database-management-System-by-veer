package com.veer.studentdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.Interface.RoleControIntr;
import com.veer.studentdb.entity.Role;
import com.veer.studentdb.request.RoleRequest;
import com.veer.studentdb.service.RoleService;

@RestController
@RequestMapping("/Role")
public class RoleController implements RoleControIntr {

	@Autowired
	private RoleService roleService;

	@PostMapping("/addrole")
	@PreAuthorize("hasRole('Admin')")
	public Role addRole(@RequestBody RoleRequest roleRequest) {
		return roleService.addRole(roleRequest);
	}
}
