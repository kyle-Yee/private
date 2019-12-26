package com.dcits.govsbu.sd.taxbankplatform.deadline.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.deadline.mapper.DeadlineMapper;
import com.dcits.govsbu.sd.taxbankplatform.deadline.model.DeadlineEntity;
import com.dcits.govsbu.sd.taxbankplatform.deadline.service.DeadlineService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;

@Service("deadlineService")
public class DeadlineServiceImpl extends AbstractService<DeadlineEntity, String> implements DeadlineService {

	@Autowired
	private DeadlineMapper deadlineMapper;
	
	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
		@Autowired
		public void setBaseMapper() {
			super.setBaseMapper(deadlineMapper);
		}

		@Override
		public int delDeadline(String id) throws AjaxException {
			// TODO Auto-generated method stub
			try
			{
				int result = deadlineMapper.delDeadline(id);
				
				return result;
			}catch(Exception e)
			{
				throw new ServiceException(e);
			}
		}
}
