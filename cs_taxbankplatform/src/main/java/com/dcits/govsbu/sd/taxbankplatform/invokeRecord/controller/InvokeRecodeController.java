package com.dcits.govsbu.sd.taxbankplatform.invokeRecord.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.model.InvokeRecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;


@Controller
@Scope("prototype")
@RequestMapping("/invokeRecode/")
public class InvokeRecodeController extends BaseController {

	@Autowired
	private InvokeRecodeService invokeRecodeService;

	
	@RequestMapping("listUI.html")
	public void listUI(Model model, HttpServletRequest request) {
		InvokeRecordEntity in = new InvokeRecordEntity();
	}
	
	
	
}