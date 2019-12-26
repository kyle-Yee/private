package com.dcits.govsbu.sd.taxbankplatform.amount.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;

/**
 * 
 * @author 赵宝庆
 * @date 2016年8月8日
 * @caption 金额管理
 */
@Repository
public interface AmountMapper extends BaseMapper<AmountEntity, String>{

	public int delAmount(@Param(value="id") String id);

}
