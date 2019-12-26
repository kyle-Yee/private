package com.dcits.govsbu.sd.taxbankplatform.base.basecontroller;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 
 * BaseController.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 基础Controller，只有日志功能
 */
public abstract class BaseController {
	
	public Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 添加公共模块
	 */
	public <T>void createModel(T t){
		try {
			PropertyUtils.setProperty(t, "creatorid", Common.getloginUserId());
			PropertyUtils.setProperty(t, "createtime", new Date());
			PropertyUtils.setProperty(t, "updatorid", Common.getloginUserId());
			PropertyUtils.setProperty(t, "updatetime", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改公共模块
	 */
	public <T>void updateModel(T t){
		try {
			PropertyUtils.setProperty(t, "updatorid", Common.getloginUserId());
			PropertyUtils.setProperty(t, "updatetime", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}