package com.corey.vidlib.dao;


import com.corey.vidlib.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
