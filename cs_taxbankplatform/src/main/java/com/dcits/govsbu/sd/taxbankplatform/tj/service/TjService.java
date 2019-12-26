package com.dcits.govsbu.sd.taxbankplatform.tj.service;

import com.dcits.govsbu.sd.taxbankplatform.tj.model.TjBean;

import java.util.List;

public interface TjService {
    Object getTjQuery();

    Object getTjMxQuery();

    List<TjBean> getDyMxQuery();

    List<TjBean> getSyMxQuery();

    List<TjBean> getDyTjMxQuery();

    void DyTjCl();

}
