package com.dcits.govsbu.sd.taxbankplatform.deadline.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.deadline.model.DeadlineEntity;

@Repository
public interface DeadlineMapper extends BaseMapper<DeadlineEntity, String>{

	public int delDeadline(@Param(value="id") String id);

}
