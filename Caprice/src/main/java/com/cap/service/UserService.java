package com.cap.service;

import java.util.List;

import com.cap.entity.Users;

public interface UserService {
	//��¼
	public void login(String userName,String passWord);
	//ע��
	public void register(String userName,String passWord,String vCode);
	//�޸ĸ�����Ϣ
	public void modifyInfo(Users user);
	//�����û�,�鿴������Ϣ
	List<Users> findUser(String userName,String userId);
	
	
}
