/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：ClientService.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-7-25下午下午4:47:192:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.service;

import java.util.List;
import java.util.Map;

/**
 * @versions:1.0
 * @filename：ClientService.java
 * @Company:dfwyBank
 * @Created: 2017-7-25下午下午4:47:192:57:43
 * @department:深圳IT部门
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName ClientService
 */
public interface ClientService {
	/*** 查询特定时间内的数据 ***/
	public List<Map<String, Object>> findDataByTime(String sql) throws Exception;

	/*** 获取数据库表的主键id名称 ***/
	public Map<String, Object> findPrimaryKey(String sql) throws Exception;

	/*** 将同步插入语句数据的日志记录到表tb_data_synchro_yhi中 ***/
	public int insertYhi(Map<String, Object> map) throws Exception;

	/*** 将同步插入数据数据的结果更新到tb_data_synchro_yhi中 ***/
	public int updateYhi(Map<String, Object> map) throws Exception;

	/*** 将同步更新语句数据的日志记录到表tb_data_synchro_yhu中 ***/
	public int insertYhu(Map<String, Object> map) throws Exception;

	/*** 将同步更新语句数据的结果更新到tb_data_synchro_yhu中 ***/
	public int updateYhu(Map<String, Object> map) throws Exception;

	/*** 根据传入的表名获取上一次查询数据的最新的一条数据的主键id ***/
	public Map<String, Object> findPrimaryKeyByTableName(String sql)
			throws Exception;
	
	/***根据传入的表名同步开始的时间和接收的时间获取该表在上一次查询的数据可能遗漏的数据 ***/
	public List<Map<String, Object>> findDatai59BySynstarAndSynend(String sql)
			throws Exception;
	
	/***更新语句根据传入的表名同步开始的时间和接收的时间获取该表在上一次查询的数据可能遗漏的数据  ***/
	public List<Map<String, Object>> findDatau59BySynstarAndSynend(String sql)
			throws Exception;
	
	/*public int insertNsryh(Map<String, Object> map)throws Exception;*/
}
