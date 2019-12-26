package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.DownLoadJiaoYanEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;

/**
 * @versions:1.0 
 * @filename：HNDataCheckOut.java
 * @Company:dfwyBank
 * @Created: 2017-4-21下午下午4:22:302:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName HNDataCheckOut
 */
public class HNDataCheckOut {
	private static final Logger logger = LoggerFactory.getLogger(HNDataCheckOut.class);
	/**
	 * @Author Zhongyj
	 * @date 2017-4-21 下午4:22:36
	 * @param id
	 * @param creatorid
	 * @param loanApproveService
	 * @return
	 */
	public boolean hNDataCheckOut(String id,String creatorid,LoanApproveService loanApproveService){
		logger.info("hNDataCheckOut 数据校验开始");
		Map<String, Object> parameters =new HashMap<String, Object>();
		DownLoadJiaoYanEntity downLoadJiaoYanEntity = null;
		parameters.put("id", id);
		parameters.put("creatorid",creatorid );
		boolean checkout = true;
		if(id != null){
			downLoadJiaoYanEntity = loanApproveService.findDownDateById(parameters);
		}
		if(downLoadJiaoYanEntity != null){
			//String qymc = downLoadJiaoYanEntity.getQymc();
			String amount = downLoadJiaoYanEntity.getAmount();
			String loandeadline = downLoadJiaoYanEntity.getLoandeadline();
			String rwid = downLoadJiaoYanEntity.getRwid();
			String dj = downLoadJiaoYanEntity.getDj();
			String nsrzglxdm = downLoadJiaoYanEntity.getNsrzglxdm();
			String fddbrxm = downLoadJiaoYanEntity.getFddbrxm();
			String fddbrsfzjhm = downLoadJiaoYanEntity.getFddbrsfzjhm();
			String fddbrsfzjlmdm = downLoadJiaoYanEntity.getFddbrsfzjlmdm();
			String fddbrbzxx = downLoadJiaoYanEntity.getFddbrbzxx();
			String fddbryddh = downLoadJiaoYanEntity.getFddbrgddh();
			String fddbrgddh = downLoadJiaoYanEntity.getFddbryddh();
			String xxse = downLoadJiaoYanEntity.getXxse();
			String jxse = downLoadJiaoYanEntity.getJxse();
			if(amount == null || loandeadline == null || rwid == null || dj== null || nsrzglxdm == null || fddbrxm == null || fddbrsfzjhm == null || fddbrsfzjlmdm == null 
					|| fddbrbzxx == null || fddbryddh == null || fddbrgddh == null){
				checkout = false;
			}else{
				if(xxse != null || jxse != null){
					checkout = true;
					}else {
						checkout = false;
					}
				}
			}
		return checkout;
	}
}
