package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;
/**
 * @versions:1.0 
 * @filename：HandleProceduce.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:22:132:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName HandleProceduce
 */
public class HandleProceduce {
	private static final Logger logger = LoggerFactory.getLogger(HandleProceduce.class);
	/**
	 * @Author Zhongyj
	 * @date 2017-4-21 下午4:22:07
	 * @param nsryhxxEntity
	 * @param taxPdfNsqdService
	 * @param sqxh
	 * @throws SQLException
	 */
	public void callProceduc(NsryhxxEntity nsryhxxEntity,TaxPdfNsqdService taxPdfNsqdService,String sqxh) throws SQLException{
		//调用存储过程 生成pdf需要的数据项
		logger.info("HandleProceduce started ! ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("djxh", nsryhxxEntity.getDjxh());
		params.put("sqxh", sqxh);
		taxPdfNsqdService.zzsnsqkPrcdure(params);
		taxPdfNsqdService.nsqdPrcdure(params);
		taxPdfNsqdService.sdsyjnsqkPrcdure(params);
		taxPdfNsqdService.jtszPrcdure(params);
		taxPdfNsqdService.sdsyyefxPrcdure(params);
		taxPdfNsqdService.zzsxsefxPrcdure(params);
		taxPdfNsqdService.znsedPrcdure(sqxh);
		logger.info("HandleProceduce end ! ");
	}
	/**
	 * @Author Zhongyj
	 * @date 2017-4-21 下午4:21:56
	 * @param nsryhxxEntity
	 * @param taxPdfNsqdService
	 * @throws SQLException
	 */
    public void deleteProcedure(NsryhxxEntity nsryhxxEntity,TaxPdfNsqdService taxPdfNsqdService) throws SQLException {
    	logger.info("deleteProcedure started ! ");
		taxPdfNsqdService.deleteDxcznlfx(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteJtnsqd(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteNsqd(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteQsxx(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteSdsnsqk(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteSdsyyesrfx(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteYlnlfx(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteYynlfx(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteZzsns(nsryhxxEntity.getDjxh());
		taxPdfNsqdService.deleteZzsxsefx(nsryhxxEntity.getDjxh());
		logger.info("deleteProcedure end ! ");
    }
}
