package com.dcits.govsbu.sd.taxbankplatform.tj.controller;

import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.tj.service.TjService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tj/")
public class Tjcontroller extends BaseController {
    @Autowired
    private TjService tjService;

    @RequestMapping("welcome.html")
    @Scope("prototype")
    @ResponseBody
    public Object Welcome(Model model, HttpServletRequest request) {
        tjService.DyTjCl();
        Object tjQuery = tjService.getTjQuery();
        return tjQuery;
    }

    @RequestMapping("welcomemx.html")
    @ResponseBody
    public Object welcomemx(Model model, HttpServletRequest request) {
        return tjService.getTjMxQuery();
    }

    @RequestMapping("WelcomeTj.html")
    public String listUI(Model model, HttpServletRequest request) {
        return Common.BACKGROUND_PATH + "/tj/welcome";
    }
}
