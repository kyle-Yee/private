package com.dcits.govsbu.sd.taxbankplatform.shiro.filter;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.dcits.govsbu.sd.taxbankplatform.user.mapper.UserMapper;

/**
 * 
 * SysUserFilter.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption
 */
public class SysUserFilter extends PathMatchingFilter {

	@Inject
	private UserMapper userMapper;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String userName = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", userMapper.findByName(userName));
        return true;
    }
}