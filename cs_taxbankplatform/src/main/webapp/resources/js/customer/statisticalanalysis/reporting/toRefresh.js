/**
 * author： zhudh
 * time： 2017-05-11 15:37:28
 */
TOREFRESH = function(){};

/**
 * 初始化
 */
TOREFRESH.init = function(){
	$.ajax({
		type:"post",
		url:sys.rootPath+"/reporting/getData.html",
		data:{},
		dataType:"json",
		success:function(rdata){
			//console.log(rdata);
			$("#dr_id").val(rdata.drEntity.dr_id);
			$("#hasUpdate").val(rdata.drEntity.hasUpdate);
			
			if(!!!rdata.drEntity.updatetime){
				rdata.drEntity.updatetime = "本月尚未更新";
			}
			$("#updatetime").html(rdata.drEntity.updatetime);
			$("#bad_num").val(rdata.drEntity.bad_num);
			$("#bad_sum").val(rdata.drEntity.bad_sum);
			$("#bad_rate").val(rdata.drEntity.bad_rate);
			$("#yearly_plan_sum").val(rdata.drEntity.yearly_plan_sum);
			$("#yearly_plan_micro_sum").val(rdata.drEntity.yearly_plan_micro_sum);
		}
	});
	
	//提交
	$("#submitBtn").click(function(){
		TOREFRESH.updateData();
	});
	
};
/**
 * 更新数据
 */
TOREFRESH.updateData = function(){
	//数据验证：10位以内整数部分，两位小数
	var moneyReg = /^((([1-9][0-9]{0,9})|0)(\.[0-9]{1,2})?)$/;
	//1以内的百分比
	var rateReg = /^(((([1-9][0-9]{0,1})|0)(\.[0-9]{1,2})?)|(100(\.0{1,2})?))$/;
	
	var bad_num = $("#bad_num").val();
	var bad_sum = $("#bad_sum").val();
	var bad_rate = $("#bad_rate").val();
	var yearly_plan_sum = $("#yearly_plan_sum").val();
	var yearly_plan_micro_sum = $("#yearly_plan_micro_sum").val();
	
	
	if(!/^(([1-9]{1}[0-9]{0,4})|0)$/.test(bad_num)){//五位整数
		layer.msg("不良贷款笔数：整数", {icon : 0});
		return;
	}
	
	if(!moneyReg.test(bad_sum)){
		layer.msg("不良贷款金额：10位以内的整数或小数(2位)", {icon : 0});
		return;
	}
	
	if(!rateReg.test(bad_rate)){
		layer.msg("不良率：不大于100的整数或小数(2位)", {icon : 0});
		return;
	}
	
	if(!moneyReg.test(yearly_plan_sum)){
		layer.msg("全年计划银税互动授信总金额：10位以内的整数或小数(2位)", {icon : 0});
		return;
	}
	
	if(!moneyReg.test(yearly_plan_micro_sum)){
		layer.msg("全年计划银税互动小微企业授信总额：10位以内的整数或小数(2位)", {icon : 0});
		return;
	}
	
	//小微企业 不大于 全部
	if(parseFloat(yearly_plan_micro_sum) > parseFloat(yearly_plan_sum)){
		layer.msg("全年计划银税互动小微企业授信总额 不能大于 全年计划银税互动授信总金额", {icon : 0});
		return;
	}
	
	var data = $("#dataForm").serialize();
	$.ajax({
		type:"post",
		url:sys.rootPath+"/reporting/updateData.html",
		data:data,
		dataType:"json",
		success:function(rdata){
			if(rdata.success){
				layer.confirm('更新成功', {
					  btn: ['确定'], //按钮
					icon:'1'
					}, function(layero){
					   layer.close(layero);
					});
				//刷新页面信息	
			   $("#dr_id").val(rdata.dr_id);
			   $("#hasUpdate").val(rdata.hasUpdate);
			   $("#updatetime").html(rdata.updatetime);
			}else{
				layer.confirm('更新失败，请联系管理员', {
					  btn: ['确定'], //按钮
					icon:'2'
					}, function(layero){
					   layer.close(layero);
					});
			}
			
		}
	});
	
};
