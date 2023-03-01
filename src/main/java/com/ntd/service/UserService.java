package com.ntd.service;

import com.ntd.model.User;

public interface UserService {
	public Object selectAll();
	public User checkAccount(User user) ;
	public Object insert(User user);
	public  User checkLogin(User user);
}
