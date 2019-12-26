package com.dcits.govsbu.sd.taxbankplatform.task.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.controller.LoanBankTaskBak;
import com.dcits.govsbu.sd.taxbankplatform.task.mapper.CallstasticsProcMapper;

/**
 * 
     * Title: DataStasticsTask.java    
     * Description: 定时任务跑批调用数据库存储过程进行数据统计
     * @author xiecui       
     * @created 2018年4月16日 下午5:49:10
 */
public class DataStasticsTask {
	private static Logger log = Logger.getLogger(LoanBankTaskBak.class);
	
	@Autowired
	private CallstasticsProcMapper callstasticsProcMapper;
	
	public void executeDataStasticsTask(){
		log.info("数据统计定时任务执行中......");
		try {
			callstasticsProcMapper.callstasticsProc();
		} catch (Exception e){
			log.info("数据统计定时任务执行出错");
			e.printStackTrace();
		}
		
		log.info("数据统计定时任务执行结束");
	}
}
