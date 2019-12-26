package com.dcits.govsbu.sd.taxbankplatform.user.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;

/**
 * 
 * UserService.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 用户
 */
public interface UserService {

	public List<UserEntity> queryListByPage(Map<String, Object> parameter);

	public UserEntity findByName(String accountName);
	
	public int insert(UserEntity userEntity, String password);
	
	public UserEntity findById(String id);

	public int update(UserEntity userEntity);
	
	public int updateOnly(UserEntity userEntity) throws ServiceException;
    
    public int deleteBatchById(List<String> userIds);
    
	public int updatePerson(UserEntity userEntity);
	public List<Map<String,String>> getSwjdm();
    
}