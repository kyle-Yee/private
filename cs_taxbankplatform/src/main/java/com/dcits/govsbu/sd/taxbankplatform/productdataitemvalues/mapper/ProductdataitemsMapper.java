package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;
@Repository 
public interface ProductdataitemsMapper  extends BaseMapper<Productdataitems, String> {
	@Override
	List<Productdataitems> queryListByPage(Map<String, Object> parameter);
	Productdataitems queryListById(String id);
    int deleteByPrimaryKey(String pdiId);
    /**
     * 逻辑删除
     */
    int   updateenableByID(String id);
    @Override
	int insert(Productdataitems record);

    int insertSelective(Productdataitems record);

    Productdataitems selectByPrimaryKey(String pdiId);

    int updateByPrimaryKeySelective(Productdataitems record);

    int updateByPrimaryKey(Productdataitems record);
    /**
     * 
     * @param 新增数据项
     * @return
     */
    int insertItem(Productdataitems productdataitems);
    /***
     * 
     * @param 新增数据项值
     * @return
     */
    int insertValues(Productdataitemvalues record);
    /**
     * 修改数据项
     */
    int updateItemId(Productdataitems productdataitems,Productdataitemvalues productdataitemvalues);
    
    /***
     * 根据数据项值名称查询数据项值ID
     */
    Productdataitemvalues selectByvalueName(String pdivName);
    /**
     *根据数据项ID删除数据
     */
    int deleteByPdiId(String pdiId);
    /**
     * 根据数据项ID查询
     */
    List<Productdataitems> selectByItemId(String pdiid);
    /***
     * 根据数据项值ID删除数据
     * 
     */
     int  deleteByPdivId(String pdivId);
     /***
      *根据数据项itemID修改（无value）
      */
     int  updateNoValueId(Productdataitems productdataitems);
     /***
      * 
      * @param 根据数据项值查询数据项Id
      *
      * @return
      */
     Productdataitems findItemIdByValueName(Productdataitems productdataitems);
     /***
      * 根据数据项ID查询数据项值的个数
      */
     int countItemId(Productdataitems productdataitems);
     /**
      * 根据PdiID查询数据项值 
      */
     List<Productdataitemvalues> selectPdivByPdiId(Map<String, Object> parameter);
     /**
      * 根据pdivID修改数据项
      */
     int updatePdivByPdivID(Productdataitemvalues productdataitemvalues);
     /**
      * 
      *根据pdivId查询数据项值 
      */
     Productdataitemvalues selectPdivByPdivId(String pdivId);
     /***
      * 根据pdiId修改数据项名称 
      */
     int updatePdiByPdiID(Productdataitems productdataitems);
     Productdataitems selectPdiByPdiId(String pdiId);
     
    /**
     * 数据项验证唯一
     * @param productdataitemvalues
     * @return
     */
	public int ckPdivName(Productdataitemvalues productdataitemvalues);
}