package com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhDkjsxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhFailure;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhSxjgxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Dksqxx;


@Repository
public interface DhDhrmxxMapper {
    int insertDhDkjsxx(List<DhDkjsxx> record);
    int insertDhsxjgxx(List<DhSxjgxx> record);
    //失败接口入库
    int insertFailure(List<DhFailure> record);
    /*//清除三十次最新记录之后的贷后日末信息
    int delTerDhrmxx();
    //清除六十次最新记录之后的授信结果信息
    int delSixrSxjgxx();*/
    /**
     * Description: 根据插入时间删除授信信息
     * @Author yaofang
     * @date 2017-10-13 上午10:37:47
     * @param createtime
     * @return
     * @throws Exception
     */
    int deleteSxjgxx(List<String> cjsj) throws Exception;
    
    //查询授信贷后记录
	public List<DhSxjgxx> findAllbybussiesid();
	public List<DhSxjgxx> querysxjgxxListAll(Map<String, Object> parameter);
	//关联查询授信结束信息
	public List<DhSxjgxx> queryListAll(Map<String, Object> parameter);
	public List<DhDkjsxx> querydkjsxxListAll(Map<String, Object> parameter);
	public List<DhFailure> queryfailureListAll(Map<String, Object> parameter);
}