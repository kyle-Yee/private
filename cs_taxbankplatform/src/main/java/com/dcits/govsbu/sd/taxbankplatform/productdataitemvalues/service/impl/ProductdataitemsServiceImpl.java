package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper.ProductdataitemsMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.ProductdataitemsService;

@Service("ProductdataitemsService")
public class ProductdataitemsServiceImpl  extends AbstractService<Productdataitems, String> implements ProductdataitemsService {
    @Autowired
    private ProductdataitemsMapper productdataitemsMapper;
  
    
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		
		super.setBaseMapper(productdataitemsMapper);
		
	}
	
	/**
	 * 重写用户插入，逻辑：
	 * 1、插入用户
	 * 2、插入用户和角色的对应关系
	 * 3、插入用户的个人资料信息
	 */
	@Override
	public int insert(Productdataitems productdataitems){
		try
		{
			if(productdataitemsMapper.insert(productdataitems) == 1)
			{
				
					return 1;
				
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}
	

	/**
	 * 重写用户更新逻辑：
	 * 1、更新用户
	 * 2、更新用户和角色的对应关系
	 * 3、更新用户个人资料信息
	 */
	@Override
	public int update(Productdataitems productdataitems){
		try
		{
			if(productdataitemsMapper.update(productdataitems) == 1)
			{
				return 1;
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
	public List<Productdataitems> queryListByPage(Map<String, Object> parameter) {
		return productdataitemsMapper.queryListByPage(parameter);
	}

	@Override
	public Productdataitems queryListById(String id) {
		return productdataitemsMapper.queryListById(id);
	}

	@Override
	public int updateenableByID(String id) {
		return productdataitemsMapper.updateenableByID(id);
	}

	@Override
	public int insert(List<String> values) {
		return 0;
	}

	@Override
	public int insertItem(Productdataitems productdataitems) {
		return productdataitemsMapper.insertItem(productdataitems);
	}

	@Override
	public int insertValues(Productdataitemvalues record) {
		return productdataitemsMapper.insertValues(record);
	}

	@Override
	public int updateItemId(Productdataitems productdataitems,Productdataitemvalues productdataitemvalues) {
		return productdataitemsMapper.updateItemId(productdataitems,productdataitemvalues);
	}

	@Override
	public Productdataitemvalues selectByvalueName(String pdivName) {
		return productdataitemsMapper.selectByvalueName(pdivName);
	}

	@Override
	public int deleteByPdiId(String pdiId) {
		return productdataitemsMapper.deleteByPdiId(pdiId);
	}

	@Override
	public List<Productdataitems> selectByItemId(String pdiid) {
		return productdataitemsMapper.selectByItemId(pdiid);
	}

	@Override
	public int deleteByPdivId(String pdivId) {
		return productdataitemsMapper.deleteByPdivId(pdivId);
	}

	@Override
	public int updateNoValueId(Productdataitems productdataitems) {
		return productdataitemsMapper.updateNoValueId(productdataitems);
	}

	@Override
	public Productdataitems findItemIdByValueName(
			Productdataitems productdataitems) {
		return productdataitemsMapper.findItemIdByValueName(productdataitems);
	}



	@Override
	public int updatePdivByPdivID(Productdataitemvalues productdataitemvalues) {
		return productdataitemsMapper.updatePdivByPdivID(productdataitemvalues);
	}

	@Override
	public List<Productdataitemvalues> selectPdivByPdiId(
			Map<String, Object> parameter) {
		return productdataitemsMapper.selectPdivByPdiId(parameter);
	}

	@Override
	public Productdataitemvalues selectPdivByPdivId(String pdivId) {
		return productdataitemsMapper.selectPdivByPdivId(pdivId);
	}

	@Override
	public int updatePdiByPdiID(Productdataitems productdataitems) {
		return productdataitemsMapper.updatePdiByPdiID(productdataitems);
	}

	@Override
	public Productdataitems selectPdiByPdiId(String pdiId) {
		return productdataitemsMapper.selectPdiByPdiId(pdiId);
	}

	/**
	 * 数据项验证唯一
	 */
	@Override
	public int ckPdivName(Productdataitemvalues productdataitemvalues) {
		try {
			return productdataitemsMapper.ckPdivName(productdataitemvalues);
		} catch (Exception e) {
			return 0;
		}
		
	}	
}
