package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyAttachEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyTailafterEntity;

/**
 * 
 * LoanApplyMapper.java
 * @author 严添麟
 * @date 2016年8月9日
 * @caption 贷款申请查询
 */
@Repository
public interface LoanApplyQueryMapper extends BaseMapper<LoanApplyQueryEntity, String>{
	//查询贷款申请数据项
	public List<LoanApplyAttachEntity> queryListAttach(Map<String, Object> parameter);
	
	public LoanApplyTailafterEntity findDataById(String id);
}
