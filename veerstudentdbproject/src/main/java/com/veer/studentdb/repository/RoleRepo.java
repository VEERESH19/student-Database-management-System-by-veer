package com.veer.studentdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.veer.studentdb.entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

}
