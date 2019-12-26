/**
 * 贷款审批记录表单验证
 */

function validateForm() {
	

	 var radio = document.getElementsByName("lac_id"); 
	 var reg  = /^(\d+\.\d+)$|^(\d+)$/;
     for (var i=0; i<radio.length; i++) {  
         if (radio[i].checked) {  
             if(radio[i].value=='YDKZT03'){
         	   if(!reg.test($("#lar_rate").val())||parseFloat($("#lar_rate").val()) <=0||isNaN(parseFloat($("#lar_rate").val()))){
         		$("#lar_rate").focus();
         		 layer.msg("请输入正确的授信利率!", {icon : 0});
         		 return;
         	   }
         	}
         }  
     }  
     for (var i=0; i<radio.length; i++) {  
    	
         if (radio[i].checked) {  
             if(radio[i].value=='YDKZT03'){
            	
            	 var radiorwid = document.getElementsByName("rw_id");  
            	 var v=0;
            	  for ( var j=0; j<radiorwid.length; j++){
            	
            		  if (radiorwid[j].checked) { 
            	
            			  v++;
            		
                 	 }else{
  
                 	 }
            	  }
            	  if(v<1){
            	
               		 layer.msg("请勾选还款方式!", {icon : 0});
              		 return;
             	 }
            	  if($("#lar_credit_quota").val() == null || $("#lar_credit_quota").val() == ""){
            			$("#lar_credit_quota").focus();
            			 layer.msg("请输入授信额度!", {icon : 0});
            			 return;
            		}
            		
            		if($("#lar_loan_deadline").val() == null || $("#lar_loan_deadline").val() == ""){
            			$("#lar_loan_deadline").focus();
            			 layer.msg("请输入贷款期限!", {icon : 0});
            			 return;
            		}
            		
            		if(parseFloat($("#lar_credit_quota").val())>parseFloat($("#approverecAmount").val())){
            		
            			isNaN($("#lar_credit_quota").val());
            			$("#lar_credit_quota").focus();

            		 layer.msg("该产品最高授信额度为"+$('#approverecAmount').val()+"万，请修改!", {icon : 0});
            			 return;
            		}
            		
            		if((parseFloat($("#lar_loan_deadline").val()))>(parseFloat($("#approverecdeadline").val()))){
            			$("#lar_loan_deadline").focus();
            			 layer.msg("该产品最高贷款期限为"+$('#approverecdeadline').val()+"个月，请修改!", {icon : 0});
            			 return;
            		}
            		if($("#lar_rate").val() == null || $("#lar_rate").val() == ""){
            			$("#lar_rate").focus();
            			 layer.msg("请输入贷款利率!", {icon : 0});
            			 return;
            		}
            	  if($("#lar_begin").val() == null || $("#lar_begin").val() == ""){
            			$("#lar_begin").focus();
            			 layer.msg("请输入贷款开始日期!", {icon : 0});
            			 return;
            		}
            		
            		if($("#lar_end").val() == null || $("#lar_end").val() == ""){
            			$("#lar_end").focus();
            			 layer.msg("请输入贷款结束日期!", {icon : 0});
            			 return;
            		}
            		if($("#lar_begin").val()> $("#lar_end").val()){
//            			$("#lar_end").focus();
//            			$("#lar_end").html(" ");
            			 layer.msg("您输入的日期大于贷款开始时间 请重新输入!", {icon : 0});
            			 return;
            		}	
         	   
         	}else{
         		$("#lar_credit_quota").val();//?
         		$("#lar_begin").val();//?
         		if($("#lar_opinion").val() == null || $("#lar_opinion").val() == ""){
         			$("#lar_opinion").focus();
         			 layer.msg("请输入审批意见!", {icon : 0});
         			 return;
         		
         		}
         		
         	}//else end
         }  
     }  
	
	var index;
	var timeEnd = $("#lar_end").val();
	/*
	 * 授信结果
	 */
	var sxResult = $("input[name='lac_id']:checked").val(); 
	var data;
	if(sxResult == 'YDKZT04' || sxResult == 'YDKZT10' || sxResult == 'YDKZT09'){//受理不通过/退单
//		alert("去除");
		data = $("#loanApproveForm").find("*").not($("#lar_begin,#lar_loan_deadline,#lar_end")).serialize();
	}else{
		data = $("#loanApproveForm").serialize();
	}
//	console.log(data);

    if (undefined != $("#pageNum").val()) {
        jumpUrl = "/loanapprove/listUI.html" + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val() + '&status=' + $("#las_id").val();
    }
    $.ajax({
        type : "POST",
        url : sys.rootPath + "/loanapprove/loanApprove.html",
        data : data,
        dataType : "json",
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
            layer.close(index);
            if (resultdata.success) {
                layer.msg(resultdata.message, {
                    icon : 1
                });
                webside.common.loadPage(jumpUrl);
                var phonenumber = resultdata.phonenumber;
            	var qymc = resultdata.qymc;
            	var dksqsj = resultdata.dksqsj;
            	var sqyh = resultdata.sqyh;
            	var sqcp = resultdata.sqcp;
            	 $.ajax({
            	        type : "POST",
            	        url : sys.rootPath + "/sendmessage/approveresult.html",
            	        data : {"phonenumber" : phonenumber,
            	        		"qymc" : qymc,"dksqsj" : dksqsj,"sqyh" : sqyh,"sqcp" : sqcp},
            	        dataType : "json",
            	        success : function(resultdata) {
            	        	
            	        },
            	        error : function() {
            	        }
            	   });
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(data, errorMsg) {
            layer.close(index);
            layer.msg(data.responseText, {
                icon : 2
            });
        }
    });
}


/**
 * 功能:
 * 贷款审批时间插件
 */
$(function(){
	
	var nowDate = getNowFormatDate();
	$("#lar_begin").datetimepicker({
		lang : "ch",
		//scrollMonth:false,
		//scrollTime:false,
		scrollInput:false,
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
		
		maxDate: nowDate,
		
	});
	
	$("#lar_end").datetimepicker({
		lang : "ch",
		//scrollMonth:false,
		//scrollTime:false,
		scrollInput:false,
		timepicker : false,
		format : "Y-m-d",
		formatDate : "Y-m-d",
		
		minDate: nowDate,                // 设置datepicker最小的限制日期   如：2016/08/15
	    maxDate:false 
	});
	
	
});

function changemonth(changeone){
	var beginDate= $.trim($("#lar_begin").val());
	var endDate= $.trim($("#lar_end").val());
	
//	var change = dateDiff(beginDate,endDate);
//	console.log(change);
//	var dayTotal = Math.ceil(change/30);
	
	//时间格式 ：2017-04-06
	var dateBegin = beginDate.split("-");
	var dateEnd = endDate.split("-");
	var year = parseInt(dateEnd[0])-parseInt(dateBegin[0]);
	var month = parseInt(dateEnd[1])-parseInt(dateBegin[1]);
	var day = parseInt(dateEnd[2])-parseInt(dateBegin[2]);
	var upAndDown = day>=0?1:0;
	var dayTotal = year*12 + month + upAndDown;
	
//	console.log(year);console.log(month);console.log(day);console.log(upAndDown);console.log(dayTotal);
 
    if(beginDate.length>0 && endDate.length>0 && beginDate > endDate){
//    	$(changeone).val("");
//		$(changeone).focus();
		 $("#lar_loan_deadline").val(" ");
		 layer.msg("您输入的日期大于贷款开始时间 请重新输入!", {icon : 0});
		 return;
	}else if(beginDate.length>0 && endDate.length>0){
		   $("#lar_loan_deadline").val(dayTotal);
	}
	
}

function dateDiff(date1, date2){       
    var type1 = typeof date1, type2 = typeof date2;       
    if(type1 == 'string')       
        date1 = stringToTime(date1);       
    else if(date1.getTime)       
        date1 = date1.getTime();       
    if(type2 == 'string')       
        date2 = stringToTime(date2);       
    else if(date2.getTime)       
        date2 = date2.getTime();   
    return (date2 - date1) / 1000 / 60 / 60 / 24;//除1000是毫秒，不加是秒   
}   
//字符串转成Time(dateDiff)所需方法   
function stringToTime(string){       
    var f = string.split(' ', 2);       
    var d = (f[0] ? f[0] : '').split('-', 3);       
    var t = (f[1] ? f[1] : '').split(':', 3);       
    return (new Date(       
    parseInt(d[0], 10) || null,       
    (parseInt(d[1], 10) || 1)-1,       
    parseInt(d[2], 10) || null,       
    parseInt(t[0], 10) || null,      
    parseInt(t[1], 10) || null,       
    parseInt(t[2], 10) || null)).getTime();   
} 


/**
 * 功能:
 * 贷款审批结果(不同意/退单)自动设置审批表单的默认值
 */
function getFormData(){
    
    if($('input[name="lac_id"]:checked').val() == 'YDKZT03'){
    	$("#lar_opinion").val("同意");
    }else if($('input[name="lac_id"]:checked').val() == 'YDKZT04' || $('input[name="lac_id"]:checked').val() == 'YDKZT10'){
    	$("#lar_opinion").val("不同意");
    } 
}


//查看附件信息
function showAttachment(title, path){
	layer.open({
		type : 2,
        scrollbar : false,
		title : title,
		maxmin: true,
		area: ["750px", "500px"],
		content : sys.rootPath + "/loanapprove/showAttachment.html?path="+path
	});
}

//获取当前日期
function getNowFormatDate() 
{ 
   var day = new Date(); 
   var Year = 0; 
   var Month = 0; 
   var Day = 0; 
   var CurrentDate = ""; 
   //初始化时间 
   //Year       = day.getYear();//有火狐下2008年显示108的bug 
   Year       = day.getFullYear();//ie火狐下都可以 
   Month      = day.getMonth()+1; 
   Day        = day.getDate(); 
   CurrentDate += Year + "-"; 
   if (Month >= 10 ) 
   { 
    CurrentDate += Month + "-"; 
   } 
   else
   { 
    CurrentDate += "0" + Month + "-"; 
   } 
   if (Day >= 10 ) 
   { 
    CurrentDate += Day ; 
   } 
   else
   { 
    CurrentDate += "0" + Day ; 
   } 

   return CurrentDate; 
} 
