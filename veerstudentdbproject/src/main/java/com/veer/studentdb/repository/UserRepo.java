package com.veer.studentdb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.veer.studentdb.entity.BaseRepository;
import com.veer.studentdb.entity.User;

@Repository
public interface UserRepo extends BaseRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	User findByUserName(String userName);

}
