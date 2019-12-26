function showProductPDetail(){
	$("#showProductPDetail").addClass("active").siblings('li').removeClass('active');
	$(".financialProductDetail").css("display","inline-block");
	/*$(".applyDetail").css("display","none");*/
}  
function showApplyDetail(){
	$("#showApplyDetail").addClass("active").siblings('li').removeClass('active');
	$(".applyDetail").css("display","inline-block");
	$(".financialProductDetail").css("display","none");
}
function showssContent(){ //查看贷款协议内容
	 var conentHtml = $("#loanAgreementssContent").html();
	 layer.open({
		 title: "协议内容",
		 type: 1,
		 area: ['800px', '600px'],
		 fix: false,
		 maxmin: true,
		 content: conentHtml
	 });
}

function showxyContent(){ //查看贷款协议内容
	 var conentHtml = $("#loanAgreementxyContent").html();
	 layer.open({
		 title: "协议内容",
		 type: 1,
		 area: ['800px', '600px'],
		 fix: false,
		 maxmin: true,
		 content: conentHtml
	 });
}