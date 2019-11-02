package com.springist.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springist.demo.entity.User;
import com.springist.demo.user.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
	
	public User findByUserId(Long userId);
}
