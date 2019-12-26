package com.dcits.govsbu.sd.taxbankplatform.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.user.mapper.UserMapper;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.service.UserService;
import com.dcits.govsbu.sd.taxbankplatform.util.EmailUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 
 * UserServiceImpl.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 用户
 */
@Service("userService")
public class UserServiceImpl extends AbstractService<UserEntity, String> implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmailUtil emailUtil;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(userMapper);
	}
	
	/**
	 * 重写用户插入，逻辑：
	 * 1、插入用户
	 * 2、插入用户和角色的对应关系
	 * 3、插入用户的个人资料信息
	 */
	@Override
	@Transactional
	public int insert(UserEntity userEntity, String password){
		
			String userId = IDGenerate.getZJID("YH");	//user 表ID
			String roleId = userEntity.getRole().getId();
			String uAr = IDGenerate.getZJID("JY");	//role user关系表ID
			userEntity.setU_id(userId);
			userEntity.getRole().setId(roleId);
			userEntity.setId(uAr);
			userEntity.getUserInfo().setId(userId);
			
			userMapper.insert(userEntity);
			userMapper.insertUserRole(userEntity);
			userMapper.insertUserInfo(userEntity);
			
//			// 发送邮件
//			// emailUtil.send126Mail(userEntity.getAccountName(), "系统消息通知", "您好,您的账户已创建,账户名:"+userEntity.getAccountName() +" ,密码:" + password);
	
			return 1;
	}
	

	/**
	 * 重写用户更新逻辑：
	 * 1、更新用户
	 * 2、更新用户和角色的对应关系
	 * 3、更新用户个人资料信息
	 */
	@Override
	public int update(UserEntity userEntity){
		try
		{
			if(userMapper.update(userEntity) == 1)
			{
				if(userMapper.updateUserRole(userEntity) == 1)
				{
					return userMapper.updateUserInfo(userEntity);
				}else
				{
					return 0;
				}
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 重写用户删除逻辑：
	 * 1、删除用户和角色的对应关系
	 * 2、删除用户
	 */
	@Override
	public int deleteBatchById(List<String> userIds){
		try
		{
			int result = userMapper.deleteBatchUserRole(userIds);
			if(result == userIds.size())
			{
				return userMapper.deleteBatchById(userIds);
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public int updateOnly(UserEntity userEntity) throws ServiceException{
		try
		{ 
			int cnt = userMapper.update(userEntity);
			// TODO 发送邮件
			// emailUtil.send126Mail(userEntity.getAccountName(), "系统密码重置", "您好，您的密码已重置，新密码是：" + password);
			return cnt;
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public int updatePerson(UserEntity userEntity) {
		try
		{
			if(userMapper.update(userEntity) == 1)
			{
				if(userMapper.updateUserRole(userEntity) == 1)
				{
					return userMapper.updateUserInfo(userEntity);
				}else
				{
					return 0;
				}
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Map<String, String>> getSwjdm() {
		// TODO Auto-generated method stub
		return userMapper.getSwjdm();
	}
	
}
