/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanForms.mapper;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.model.LoanForms;

/**
 * @author 胡宝龙2016-8-15 上午10:08:55
 *
 */
@Repository 
public interface LoanFormsMapper  extends BaseMapper<LoanForms, String>{
	LoanForms findByFpId(String id);
}
