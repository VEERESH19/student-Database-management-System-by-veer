package com.veer.studentdb.Interface;

import com.veer.studentdb.entity.User;
import com.veer.studentdb.request.UserRequest;

public interface UserServIntr {

	public User addUser(UserRequest userRequest);

}
