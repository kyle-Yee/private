package com.cap.service;

import java.util.List;

import com.cap.entity.Users;

public interface UserService {
	//登录
	public void login(String userName,String passWord);
	//注册
	public void register(String userName,String passWord,String vCode);
	//修改个人信息
	public void modifyInfo(Users user);
	//查找用户,查看个人信息
	List<Users> findUser(String userName,String userId);
	
	
}
