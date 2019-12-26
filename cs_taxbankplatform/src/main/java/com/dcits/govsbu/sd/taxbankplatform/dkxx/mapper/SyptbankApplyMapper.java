package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.LoanApplyInfo;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
@Repository 
public interface SyptbankApplyMapper extends BaseMapper<Syptbankapply, String> {

	@Override
	public int insert(Syptbankapply syptbankapply);
	public List<Syptbankapply> findBylaid(@Param("lfId")String lfId,@Param("channelid")String channelid);
	public List<Syptbankapply> findByfpid(@Param("bankfpId")String bankfpId,@Param("channelid")String channelid);
	public int changetemp(Syptbankapply syptbankapply);
	public List<Syptbankapply> findAll(Map<String, Object> parameter);
	/**
	 * 贷款申请信息接口数据保存
	 * @author Sigua.Huang
	 * @date 2018年6月27日
	 */
	public int insertLoanApplyInfo(LoanApplyInfo loanApplyInfo);
	/**
	 * 同步授权结束时间
	 * @author Sigua.Huang
	 * @date 2018年7月20日
	 */
	public int updateGrantEndTime(Map<String, Object> sqMap);
	/**
	 * 根据Businessid查出授权编码
	 * @author Sigua.Huang
	 * @date 2018年7月20日
	 */
	public String queryGrantCodeByBusinessid(String businessid);
	   
}
