<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-header" style="text-align:center">
	<h1>
		<a href="/test/loanapprove.html">信贷审批列表</a>
	</h1>	
</div>
<script>
	function onclickLoan(){
		$.ajax({
		    type:"POST",
		    url: sys.rootPath + "/test/loanapprove.html",
		    async:false,
		    data:null,
		    success:function(msg){
		        if (msg) {
		            window.location.href = "/welcome.jsp";
		        }
		
		    }
		});
	}
	
</script>