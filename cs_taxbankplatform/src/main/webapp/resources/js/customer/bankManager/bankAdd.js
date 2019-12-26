//@ sourceURL = bankAdd.js
/**
 * 表单验证
 */
function validateForm()
{    
	if($('#bankId').val()==''||$('#bankId').val()==null){
		layer.msg("请选择银行", {
            icon : 0
        });
		return false;
	}else if($('#bankdepartment').val()==''||$('#bankdepartment').val()==null){
		layer.msg("请输入银行联系部门", {
            icon : 0
        });
		return false;
	}else if($('#leadername').val()==''||$('#leadername').val()==null){
		layer.msg("请输入部门负责人名称", {
            icon : 0
        });
		return false;
	}else if(!/^1[3|4|5|7|8]\d{9}$/.test($('#leaderphone').val())){
		layer.msg("请输入正确的负责人手机号码", {
            icon : 0
        });
		return false;
	}else if($('#contactName').val()==''||$('#contactName').val()==null){
		layer.msg("请输入联系人名称", {
            icon : 0
        });
		return false;
	}else if(!/^1[3|4|5|7|8]\d{9}$/.test($('#contactphone').val())){
		layer.msg("请输入正确的联系人手机号码", {
            icon : 0
        });
		return false;
	}else if($('#fpId').val()==''||$('#fpId').val()==null){
		layer.msg("请选择产品", {
            icon : 0
        });
		return false;
//	}else if($('#applyRequire').val()==''||$('#applyRequire').val()==null){
//		layer.msg("请输入申请条件", {
//            icon : 0
//        });
//		return false;
	}else if($('#starttime').val()==''||$('#starttime').val()==null){
		layer.msg("请选择有效期起", {
            icon : 0
        });
		return false;
	}else if($('#endtime').val()==''||$('#endtime').val()==null){
		layer.msg("请选择有效期止", {
            icon : 0
        });
		return false;
		
	}else if($('#frequencylimit').val()==''||$('#frequencylimit').val()==null){
		layer.msg("请选择频次", {
            icon : 0
        });
		return false;
	}
	var checkList = $("input[name*='DM'][type='checkbox']:checked");
	if(!checkList.length>0){
		layer.msg("请配置数据项", {
            icon : 0
        });
		return false;
	}
	return true;
}
//选择银行时触发此事件
function bankChange(){
	//为bankCode赋值
	$('#bankCodeVal').val($('#bankId').find("option:selected").attr("codeVal"));
	$('#bankName').val($('#bankId').find("option:selected").text());
	//获取bankId
	var bankId = $('#bankId').val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/bankManager/findFPsByBankId.html",
		data:{"bankId":bankId},
		success:function(result){
			if(result.success){
				//清空select
				var selFPs = document.getElementById("fpId");//$("#fpId");//根据id获取select的jquery对象
				var options = $(selFPs).find("option");
				if(options.size()>1){
					$.each(options,function(index,value) { 
						if(index>0){selFPs.options.remove(index);}
				  }); 
				}
//				selFPs.html("");
				var fpList = result.data;
//				selFPs.append("<option value=''>请选择产品...</option>");//添加option
				//遍历循环金融产品list
				$.each(fpList,function(index,value) { 
					selFPs.options.add(new Option(value.fpName,value.fpId));
			    }); 
//				fpChang();
			}else{
				layer.msg(result.message, {
		            icon : 0
		        });
			}
        }
	});
}
//选择产品是触发此事件
function fpChang(){
	$('#fpName').val($('#fpId').find("option:selected").text());
}

function submitForm(){
	if(!validateForm()) return false;
	var data=$('#taxDataCfgForm').serialize();
	//return false;
	$.ajax({
		type:"POST",
		dataType:"json",
		url:sys.rootPath + "/bankManager/addAction.html",
		data:data,
		success:function(result){
			layer.msg("添加成功", {
	            icon : 0
	        });
			webside.common.loadPage('/bankManager/bankList.html');
        },  
        error: function(json) { 
            layer.msg("加载失败", {
	            icon : 0
	        });
            $("#loader").addClass('hide');
		}
	});
}
function submitEditForm(){
	layer.confirm('确认修改吗？', {
        icon : 3,
        title : '修改提示'
    },function(){
    	if(!validateForm()) return false;
    	var data=$('#taxDataCfgForm').serialize();
    	//return false;
    	$.ajax({
    		type:"POST",
    		dataType:"json",
    		url:sys.rootPath + "/bankManager/editAction.html",
    		data:data,
    		success:function(result){
    			layer.msg("修改成功", {
    	            icon : 0
    	        }); 
    			webside.common.loadPage('/bankManager/bankList.html');
            },  
            error: function(json) { 
                layer.msg("修改失败", {
    	            icon : 0
    	        });
                $("#loader").addClass('hide');
    		}
    	});
    });
}
//单行全反选
function checkBoxClick(obj){
	//拿到该tr标签下的所有复选框
	var checkBoxs = $(obj).parent().parent().parent().find("input[type='checkbox']")
	if($(obj).is(':checked')) {
		checkBoxs.each(function(index,perCheckBox){
			$(perCheckBox).prop("checked",true);
		});
	}else{
		checkBoxs.each(function(index,perCheckBox){
			$(perCheckBox).prop("checked",false);
		});
	}
}
//全反选
function checkAll(e){
	var flag=$(e).is(':checked');
	if(flag){
		$("#checkAll input[type='checkbox']").prop("checked",true);
		$("input[name='checkedAll'][type='checkbox']").prop("checked",true);
	}else{
		$("#checkAll input[type='checkbox']").prop("checked",false);
		$("input[name='checkedAll'][type='checkbox']").prop("checked",false);
	}
}
//初始化全选框
function initCheckBox(){
	//循环数据项table所有的tr
	$("tr[name='taxTableTitle']").each(function(index,trCheckBox){
		var selectedCount = 0;
		var totalCount = 0;
		//循环tr下name='dataContent'的所有td
		$(trCheckBox).find("td[name='dataContent']").each(function(index,tdCheckBox){
			//循环td下所有的复选框
			$(tdCheckBox).find("input[type='checkbox']").each(function(index,selectCheckBox){
				//累计计算总条数
				totalCount++;
				if($(selectCheckBox).is(':checked')){
					//如果被选中则累计
					selectedCount++;
				}
			});
		});
		//复选框总条数不为0并且总条数=被选中条数则将全选的复选框选中
		if(selectedCount!=0&&selectedCount==totalCount){
			$(trCheckBox).find("input[name='titleTd'][type='checkbox']").prop("checked",true);
		}
	});
}