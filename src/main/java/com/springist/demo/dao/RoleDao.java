package com.springist.demo.dao;

import com.springist.demo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
