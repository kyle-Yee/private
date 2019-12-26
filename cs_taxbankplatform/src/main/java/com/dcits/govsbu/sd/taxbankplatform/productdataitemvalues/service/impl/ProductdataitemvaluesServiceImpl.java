package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper.ProductdataitemvaluesMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.ProductdataitemvaluesService;
@Service("ProductdataitemvaluesService")
public class ProductdataitemvaluesServiceImpl   extends AbstractService<Productdataitemvalues, String> implements
		ProductdataitemvaluesService {

	@Autowired
	private ProductdataitemvaluesMapper productdataitemvaluesMapper;
	 
		//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
		@Autowired
		public void setBaseMapper() {
			
			super.setBaseMapper(productdataitemvaluesMapper);
			
		}
	/*@Override
	public int insertValues(Productdataitemvalues record) {
		// TODO Auto-generated method stub
		int num;
		try {
		 productdataitemvaluesMapper.insertValues(record);
		  num=1;
		 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			num=0;
		}
		return num;
	}

	@Override
	public int insertItems(Productdataitemvalues productdataitemvalues) {
		// TODO Auto-generated method stub
		int num;
		try {
		 productdataitemvaluesMapper.insertItems(productdataitemvalues);
		  num=1;
		 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			num=0;
		}
		return num;
	}
*/

		@Override
		public int insert(Productdataitemvalues productdataitemvalues) {
			try
			{
				if(productdataitemvaluesMapper.insertValues(productdataitemvalues) == 1)
				{
					if(productdataitemvaluesMapper.insertItems(productdataitemvalues) == 1)
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}else
				{
					return 0;
				}
			}catch(Exception e)
			{
				throw new ServiceException(e);
			}
		}

		@Override
		public List<Productdataitemvalues> selectPdivByPdiId(
				Map<String, Object> parameter) {
			return productdataitemvaluesMapper.selectPdivByPdiId(parameter);
		}

		@Override
		public List<Productdataitems> searchPdivNameList(String id) {
			return productdataitemvaluesMapper.searchPdivNameList(id);
		}

		@Override
		public List<Productdataitemvalues> selectPdivSeqByPdiId(
				Map<String, Object> parameter) {
			// TODO Auto-generated method stub
			return productdataitemvaluesMapper.selectPdivSeqByPdiId(parameter);
		}
}
