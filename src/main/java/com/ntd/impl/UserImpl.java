package com.ntd.impl;

import org.springframework.stereotype.Service;

import com.ntd.mapper.UserMapper;
import com.ntd.model.User;
import com.ntd.service.UserService;

import jakarta.annotation.Resource;

@Service
public class UserImpl implements UserService{
	
	@Resource
	private  UserMapper usermapper;
	
	@Override
	public Object selectAll() {
		return usermapper.selectAll();
	}
	
	@Override
	public Object insert(User user) {
		return usermapper.insert(user);
	}
	@Override
	public User checkAccount(User user) {
		return usermapper.checkAccount(user);
	}

	@Override
	public User checkLogin(User user) {
		 return usermapper.checkLogin(user);
	}
}
