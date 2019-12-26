/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanformsattach.mapper;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model.LoanFormsAttach;

/**
 * @author 胡宝龙2016-9-9 上午10:18:24
 *
 */
public interface LoanFormsAttachMapper  extends BaseMapper<LoanFormsAttach, String>{
	List<LoanFormsAttach> queryListByLfId(Map<String, Object> parameter);
	LoanFormsAttach queryExist(Map<String, Object> parameter);
}
