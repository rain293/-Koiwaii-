package com.ntd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ntd.model.User;

@Repository
@Mapper
public interface UserMapper {
	List<User> selectAll();
	User checkAccount(User user);
	int insert(User user);
	User checkLogin(User user);
	
}
