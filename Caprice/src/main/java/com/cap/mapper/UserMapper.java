package com.cap.mapper;

import com.cap.entity.Users;

public interface UserMapper {

		public void login(String userName,String passWord);

		public void register(String userName,String passWord,String vCode);

		public void modifyInfo(Users user);

		public void findUser(String userName);
}
