package com.dcits.govsbu.sd.taxbankplatform.tj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FaqEntity;
import com.dcits.govsbu.sd.taxbankplatform.tj.mapper.TjMapper;
import com.dcits.govsbu.sd.taxbankplatform.tj.model.TjBean;
import com.dcits.govsbu.sd.taxbankplatform.tj.service.TjService;

@Service("tjServiceImlp")
@Transactional(rollbackFor = Exception.class)
public class TjServiceImlp extends AbstractService<FaqEntity, String> implements TjService {
    @Autowired
    private TjMapper tjMapper;

    @Override
    public Object getTjQuery() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 1;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM");
        int mm = Integer.parseInt(df.format(date)) + 1;
        String month = "";
        if (mm < 10) {
            month = "0" + String.valueOf(mm);
        } else {
            month = String.valueOf(mm);
        }
        String qyn = String.valueOf(year) + month;
        List<TjBean> list = tjMapper.getTjYf(qyn);
        System.out.println("=======查询统计====");

        return list;
    }

    @Override
    public Object getTjMxQuery() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 1;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM");
        int mm = Integer.parseInt(df.format(date)) + 1;
        String qyn = String.valueOf(year) + String.valueOf(mm);
        List<TjBean> list = tjMapper.getTjJd(qyn);
        System.out.println("=======查询季度统计====");

        return list;
    }

    @Override
    public List<TjBean> getDyMxQuery() {// 当月
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        int dy = Integer.parseInt(df.format(date));
        System.out.println("=======查询当月数据开始====");
        List<TjBean> getDyMapper = tjMapper.getDyMapper(String.valueOf(dy));
        System.out.println("=======查询当月数据结束====");

        return getDyMapper;
    }

    @Override
    public List<TjBean> getSyMxQuery() {// 上月
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        int syy = Integer.parseInt(df.format(date)) - 1;
        System.out.println("=======查询上月数据开始====");
        // List<TjBean> getTjSyMapper = tjMapper.getTjSYf(String.valueOf(syy));
        System.out.println("=======查询上月数据结束====");

        return null;
    }

    @Override
    public List<TjBean> getDyTjMxQuery() {//
        System.out.println("=======查询当月统计开始====");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        int dy = Integer.parseInt(df.format(date));
        List<TjBean> list = tjMapper.getTjYf(String.valueOf(dy));
        System.out.println("=======查询当月统计结束====");

        return list;
    }

    @Override
    public void DyTjCl() {
        try {
            List<TjBean> dymx = getDyMxQuery();// 当月数据
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
            int dqrq = Integer.parseInt(df.format(date));
            List<TjBean> insertlistdymxList = new ArrayList<TjBean>();
            List<TjBean> updatelistdymxList = new ArrayList<TjBean>();
            if (null != dymx && dymx.size() > 0) {
                for (int i = 0; i < dymx.size(); i++) {
                    TjBean tjBean = dymx.get(i);// 当月数据

                    List<TjBean> tjBeanInfo = tjMapper.getTjSYf(tjBean.getZid(), String.valueOf(dqrq));//
                    int syrq = dqrq - 1;
                    String zid = tjBean.getYhdm() + String.valueOf(syrq);

                    List<TjBean> sy = tjMapper.getTjSYf(zid, String.valueOf(syrq));
                    double dkye = Double.parseDouble(dymx.get(i).getDkye()) / 10000;
                    double sqje = Double.parseDouble(dymx.get(i).getSqje()) / 10000;
                    double sxje = Double.parseDouble(dymx.get(i).getSxje()) / 10000;
                    if (tjBeanInfo.size() > 0) {
                        double updatesqje = Double.parseDouble(sy.get(0).getSqje()) + sqje;
                        double updatesxje = Double.parseDouble(sy.get(0).getSxje()) + sxje;
                        double updatedkye = Double.parseDouble(sy.get(0).getDkye()) + dkye;
                        double hpsl = Double.parseDouble(tjBean.getHpsl()) + Integer.parseInt(sy.get(0).getHpsl());
                        double whpsl = Double.parseDouble(tjBean.getWhpsl()) + Integer.parseInt(sy.get(0).getWhpsl());
                        tjBean.setDkye(String.valueOf(updatedkye));
                        tjBean.setHpsl(String.valueOf(hpsl));
                        tjBean.setSqje(String.valueOf(updatesqje));
                        tjBean.setSxje(String.valueOf(updatesxje));
                        tjBean.setWhpsl(String.valueOf(whpsl));
                        tjBean.setZid(dymx.get(i).getZid());
                        updatelistdymxList.add(tjBean);
                    } else {
                        tjBean.setDkye(String.valueOf(dkye));
                        tjBean.setSqje(String.valueOf(sqje));
                        tjBean.setSxje(String.valueOf(sxje));
                        tjBean.setDkrq(String.valueOf(dqrq));
                        tjBean.setHpsl(dymx.get(i).getHpsl());
                        tjBean.setZid(dymx.get(i).getZid());
                        tjBean.setWhpsl(dymx.get(i).getWhpsl());
                        tjBean.setYhdm(dymx.get(i).getYhdm());
                        tjBean.setYhmc(dymx.get(i).getYhmc());
                        insertlistdymxList.add(tjBean);
                    }
                }
            }
            if (updatelistdymxList.size() > 0) {
                tjMapper.updateTj(updatelistdymxList);
                updatelistdymxList.clear();
            }
            if (insertlistdymxList.size() > 0) {
                tjMapper.insertTj(insertlistdymxList);
                insertlistdymxList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
