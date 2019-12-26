package com.dcits.govsbu.sd.taxbankplatform.tj.mapper;

import com.dcits.govsbu.sd.taxbankplatform.tj.model.TjBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TjMapper {
    List<TjBean> getDyMapper(@Param("yymm") String yymm);

    List<TjBean> getTjAndMmMapper(@Param("zid") String zid);

    List<TjBean> getTjYf(@Param("yymm") String yymm);

    List<TjBean> getTjSYf(String yymm, String zid);

    List<TjBean> getTjJd(@Param("yymm") String yymm);

    void insertTj(List<TjBean> insertlistdymxList);

    void updateTj(List<TjBean> updatelistdymxList);
}
