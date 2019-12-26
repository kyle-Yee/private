package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.PandectstatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.PandectstatisticsService;

@Service("pandectstatisticsService")
public class PandectstatisticsServiceImpl  extends AbstractService<DataCountEntity, String> implements PandectstatisticsService {

	@Autowired
	private PandectstatisticsMapper pandectstatisticsMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(pandectstatisticsMapper);		
	}
	/**
	 * 查询用户统计数据
	 */
	@Override
	public Map<String, Object> userstatistics(Map<String, Object> parameters) {
		/****************************统计功能优化**************************************/
		/*modify by xiecui 2018/04/04 begin*/
//		List<DataCountEntity> dataCount = pandectstatisticsMapper.userstatistics(parameters);
		return pandectstatisticsMapper.userstatistics(parameters);
		/*modify by xiecui 2018/04/04 end*/
		
//		DataCountEntity data=null;
//		for(int i=0;i<dataCount.size();i++){
//			data = dataCount.get(i);
//			if("zcyh".equals(data.getDataName())){
//				data.setDataName("注册用户");
//			}else if("rzyh".equals(data.getDataName())){
//				data.setDataName("通过认证用户");
//			}else if("sqdkyh".equals(data.getDataName())){
//				data.setDataName("申请贷款用户");
//			}else if("sxyh".equals(data.getDataName())){
//				data.setDataName("授信用户");
//			}		
//		}
		
	}
	@Override
	public List<PandectEntity> monthstatistics(Map<String, Object> parameters) {
		List<PandectEntity> monthDataCount=pandectstatisticsMapper.monthstatistics(parameters);
		
//		PandectEntity pe1= new PandectEntity();
//		for(int i = 0;i < monthDataCount.size();i++){
//			if (!"zcyh".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("zcyh");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			if (!"rzyh".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("rzyh");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			if (!"sqdkbs".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("sqdkbs");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			if (!"sxbs".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("sxbs");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			if (!"sdze".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("sdze");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			if (!"sxze".equals(monthDataCount.get(i).getCountName())){
//				pe1.setCountName("sxze");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				monthDataCount.add(pe1);
//				break;
//			}
//			dataCount = monthDataCount.get(i);
//			if("zcyh".equals(dataCount.getCountName())){
//				dataCount.setCountName("注册用户");
//			}else if ("rzyh".equals(dataCount.getCountName())){
//				dataCount.setCountName("认证用户");
//			}else if ("sqdkbs".equals(dataCount.getCountName())){
//				dataCount.setCountName("申请贷款笔数");
//			}else if ("sxbs".equals(dataCount.getCountName())){
//				dataCount.setCountName("授信笔数");
//			}else if ("sdze".equals(dataCount.getCountName())){
//				dataCount.setCountName("申贷总额");
//			}else if ("sxze".equals(dataCount.getCountName())){
//				dataCount.setCountName("授信总额");
//			}
//		}
		return monthDataCount;
	}
	@Override
	public List<BanklistInfoEntity> banklistinfo(Map<String, Object> parameters) {
		return pandectstatisticsMapper.banklistinfo(parameters);
	}
	
	/****************************统计功能优化**************************************/
	/*add by xiecui 2018/04/04 begin*/
	@Override
	public Map<String, Object> loanstatistics(Map<String, Object> parameters) {
		return pandectstatisticsMapper.loanstatistics(parameters);
	}
	@Override
	public Map<String, Object> bankstatistics(Map<String, Object> parameters) {
		return pandectstatisticsMapper.bankstatistics(parameters);
	}
	@Override
	public List<Map<String, Object>> usermonthstatistics(Map<String, Object> parameters) {
		return pandectstatisticsMapper.usermonthstatistics(parameters);
	}
	@Override
	public List<Map<String, Object>> loanmonthstatistics(Map<String, Object> parameters) {
		return pandectstatisticsMapper.loanmonthstatistics(parameters);
	}
	/*add by xiecui 2018/04/04 end*/

	/****************************new 统计功能**************************************/
	@Override
	public Map<String, Object> statisticsinfo(Map<String, Object> param) {
		return pandectstatisticsMapper.statisticsinfo(param);
	}

	@Override
	public List<Map<String, Object>> loanamountstatisticsinfo(Map<String, Object> param) {
		return pandectstatisticsMapper.loanamountstatisticsinfo(param);
	}

	@Override
	public List<Map<String, Object>> loancountstatisticsinfo(Map<String, Object> param) {
		return pandectstatisticsMapper.loancountstatisticsinfo(param);
	}

	@Override
	public List<Map<String, Object>> bankproduckstatisticsinfo(Map<String, Object> param) {
		return pandectstatisticsMapper.bankproduckstatisticsinfo(param);
	}
}
