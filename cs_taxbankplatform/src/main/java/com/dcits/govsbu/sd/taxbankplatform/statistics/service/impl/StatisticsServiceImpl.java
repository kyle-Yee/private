package com.dcits.govsbu.sd.taxbankplatform.statistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.mapper.StatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanCompreInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanDetailsInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.BankManagerInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.service.StatisticsService;

/**
 * 统计报表业务接口实现
 * @author Sigua.Huang
 * @date 2018年6月28日
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{
	private Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public List<LoanDetailsInfo> queryLoanDetailsList(Map<String, Object> parameters) {
		return statisticsMapper.queryLoanDetailsList(parameters);
	}

	@Override
	public List<CommonEntity> queryHYList() {
		return statisticsMapper.queryHYList();
	}

	@Override
	public List<CommonEntity> queryDJZCLXList() {
		return statisticsMapper.queryDJZCLXList();
	}

	@Override
	public List<LoanCompreInfo> queryLoanCompreList(Map<String, Object> parameters) {
		List<LoanCompreInfo> rusultList = new ArrayList<LoanCompreInfo>();
		//获取所有产品的基本信息
		List<LoanCompreInfo> msgList = statisticsMapper.queryFPMsg(parameters);
		//根据产品信息获取数据
		if(msgList.size()>0){
			for (LoanCompreInfo loanCompreInfo : msgList) {
				Map<String,String> callLoanCompreMap = new HashMap<String,String>();
				callLoanCompreMap.put("bankCode", loanCompreInfo.getTbpcode());
				callLoanCompreMap.put("fpId", loanCompreInfo.getFpId());
				callLoanCompreMap.put("nsrzgswj", parameters.get("nsrzgswj").toString());
				callLoanCompreMap.put("starttime", parameters.containsKey("starttime")?parameters.get("starttime").toString():"");
				callLoanCompreMap.put("endtime", parameters.containsKey("endtime")?parameters.get("endtime").toString():"");
				//查询指定银行的统计数据
				LoanCompreInfo tjsj = statisticsMapper.callLoanCompre(callLoanCompreMap);
				tjsj.setTbpcode(loanCompreInfo.getTbpcode());
				tjsj.setTbpname(loanCompreInfo.getTbpname());
				tjsj.setFpId(loanCompreInfo.getFpName());
				tjsj.setFpName(loanCompreInfo.getFpName());
				//添加授权查询笔数
				tjsj.setGrantQueryCount(statisticsMapper.findSqcxbs(callLoanCompreMap));
				//添加再途审批数量
				int auditingCount = Integer.parseInt(tjsj.getApplyLoanCount()) -
						Integer.parseInt(tjsj.getAuditApproveCount()) -
						Integer.parseInt(tjsj.getAuditFailureCount()) ;
				tjsj.setAuditingCount(String.valueOf(auditingCount));
				//添加平均申贷金额
				if(!"0".equals(tjsj.getApplyLoanCount())){
				Double loanApplyAmountAvg = Double.parseDouble(tjsj.getLoanApplyAmountSum())/Double.parseDouble(tjsj.getApplyLoanCount());
				tjsj.setLoanApplyAmountAvg(String.valueOf(loanApplyAmountAvg));
				}
				//添加平均授信金额
				if(!"0".equals(tjsj.getApplyLoanCount())){
					Double loanAmountAvg = Double.parseDouble(tjsj.getLoanAmountSum())/Double.parseDouble(tjsj.getApplyLoanCount());
					tjsj.setLoanAmountAvg(String.valueOf(loanAmountAvg));
				}
				rusultList.add(tjsj);
			}
		}
 
		return rusultList;
	}
	
	//json转bean合成工具
	private LoanCompreInfo getLoanCompreInfo(String jsonStr,LoanCompreInfo loanCompreInfo){
		LoanCompreInfo entity = new LoanCompreInfo();
		if(!StringUtils.isBlank(jsonStr)){
			JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
	        if (jsonObj.getJSONArray("data") != null) {
	            jsonObj = jsonObj.getJSONArray("data").getJSONObject(0);
	        }
	        jsonObj.remove("******");
	        entity= JSONObject.toJavaObject(jsonObj,LoanCompreInfo.class);
		}else{
			entity = null;
		}
		entity.setTbpcode(loanCompreInfo.getTbpcode());
		entity.setTbpname(loanCompreInfo.getTbpname());
		entity.setFpId(loanCompreInfo.getFpName());
		entity.setFpName(loanCompreInfo.getFpName());
        return entity;
    }
	@Override
	public List<BankManagerInfo> queryBankManagerList(Map<String, Object> parameters) {
		List<BankManagerInfo> bankManagerList = statisticsMapper.queryBankManagerList(parameters);
		//获取实质性内容个数
		for (BankManagerInfo bankManagerInfo : bankManagerList) {
			String[] split = bankManagerInfo.getDataItems().split(",");
			int dataCount = statisticsMapper.findDataCount(split);
			bankManagerInfo.setDatasum(dataCount+"");;
		}
		return bankManagerList;
	}

	@Override
	public List<BusinessRegInfo> queryBusinessRegInfo(String dataStr) {
		List<BusinessRegInfo> list = new ArrayList<BusinessRegInfo>();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		String[] datas = dataStr.split(",");
		if(datas.length>0){
			for (String data : datas) {
				String dataName = statisticsMapper.queryMCByDataDM(data);
				String dataSub = data.substring(0, 4);
				if(StringUtils.isNotBlank(dataName)){
					if(map.containsKey(dataSub)){
						map.get(dataSub).add(dataName);
					}else{
						List<String> dataList = new ArrayList<String>();
						dataList.add(dataName);
						map.put(dataSub, dataList);
					}
				}
			}
		}

		Iterator<Entry<String, List<String>>> entries = map.entrySet().iterator(); 
		while (entries.hasNext()) { 
		  Entry<String, List<String>> entry = entries.next(); 
		  BusinessRegInfo businessRegInfo = new BusinessRegInfo();
		  String gsbmc = statisticsMapper.queryGSBMCByDataDM(entry.getKey());
		  businessRegInfo.setGsbmc(gsbmc);
		  businessRegInfo.setCftOptions(org.apache.commons.lang3.StringUtils.join(entry.getValue(), ";"));
		  list.add(businessRegInfo);
		}
		return list;
	}

	@Override
	public List<CommonEntity> querySsswjDmList(String ssswjDm) {
		// TODO Auto-generated method stub
		return statisticsMapper.querySsswjDmList(ssswjDm);
	}

}
