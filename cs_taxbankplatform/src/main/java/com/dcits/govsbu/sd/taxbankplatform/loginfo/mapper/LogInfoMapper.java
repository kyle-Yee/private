package com.dcits.govsbu.sd.taxbankplatform.loginfo.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.loginfo.model.LogInfoEntity;

/**
 * 
 * @versions:1.0 
 * @filename：LogInfoMapper.java
 * @Company:dfwyBank
 * @Created: 2017-7-10下午上午10:12:272:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName LogInfoMapper
 */
@Repository
public interface LogInfoMapper extends BaseMapper<LogInfoEntity, String> {

	// 删除七天前的记录
	public void clear7daysBefore() ;
}
