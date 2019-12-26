<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<link href="${ctx }/resources/css/style/style.css" type="text/css" rel="stylesheet"/>
        
<div class="title">总览</div>
<jsp:include page="/WEB-INF/view/regulator/areaForm.jsp" flush="true"></jsp:include>

<div class="row" id="dataALL">
    <div class="col-md-12 text-center">
        <div class="row">
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray box-count font-red">
                    <img class="img-user" src="${ctx }/resources/images/loanUsers.png"/>
                    <div class="font-eighteen" id="loanUsers">0</div>
                    <div>申请贷款用户（户）</div>
                </div>
            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray box-count font-green">
                    <img class="img-user" src="${ctx }/resources/images/creditUser.png"/>
                    <div class="font-eighteen" id="creditUser">0</div>
                    <div>授信用户（户）</div>
                </div>
            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray box-count font-purple">
                    <img class="img-user" src="${ctx }/resources/images/qy-six.png"/>
                    <div class="font-eighteen" id="loanAmount">0</div>
                    <div>申请贷款笔数（笔）</div>
                </div>
            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray box-count font-purple">
                    <img class="img-user" src="${ctx }/resources/images/qy-five.png"/>
                    <div class="font-eighteen" id="creditNote">0</div>
                    <div>成功授信笔数（笔）</div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-12 text-center">
        <div class="border-gray chart-box mar-bottom-twenty">
            <div id='container2'></div>
        </div>
    </div>

    <div class="col-md-7 text-center">
        <div class="row">
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray loan-count font-yellow">
                    <div class="mar-bottom-ten">申请贷款总额（万元）</div>
                    <div id="applyTotal">0</div>
                    <i class="triangle"></i>
                </div>
            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray loan-count font-blue">
                    <div class="mar-bottom-ten">平均申贷金额（万元）</div>
                    <div id="averageApply">0</div>
                    <i class="triangle"></i>
                </div>

            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray loan-count font-green">
                    <div class="mar-bottom-ten">授信总额（万元）</div>
                    <div id="creditTotal">0</div>
                    <i class="triangle"></i>
                </div>
            </div>
            <div class="col-md-3 mar-bottom-twenty">
                <div class="border-gray loan-count font-red">
                    <div class="mar-bottom-ten">平均授信金额（万元）</div>
                    <div id="averageCredit">0</div>
                    <i class="triangle"></i>
                </div>
            </div>
        </div>

        <div class="border-gray chart-box mar-bottom-twenty">
            <div id='container3'></div>
        </div>
    </div>

    <div class="col-md-5">
        <div class="row">
            <div class="col-md-6 mar-bottom-twenty">
                <div class="border-gray box-count-two border-blue">
                    <img src="${ctx }/resources/images/cooperBank.png"/>
                    <div class="font-sixteen mar-top-twenty">已合作银行（家）</div>
                    <div class="font-twenty-eight  mar-top-ten" id="coopBank">0</div>
                </div>
            </div>
            <div class="col-md-6 mar-bottom-twenty">
                <div class="border-gray box-count-two border-yellow">
                    <img src="${ctx }/resources/images/finaProducts.png"/>
                    <div class="font-sixteen  mar-top-twenty">银税互动金融产品（个）</div>
                    <div class="font-twenty-eight  mar-top-ten" id="finaProducts">0</div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-5 mar-bottom-twenty">
        <div class="widget border-gray">
            <div class="widget-header">
                <h4>产品名称</h4>
            </div>
            <div class="widget-content intProduct" id="intProduct">
            </div>
        </div>
    </div>
</div>

<div class="alert alert-info text-center fn-hide" id="dataNo"></div>

<!-- 加载效果 start -->
<div class="loader" id="loader">
    <div class="loader-backdrop loader-fade"></div>
    <div class="loader-inner ball-spin-fade-loader">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- 加载效果 end -->

<script id="search" type="text/javascript" 
	src="${ctx }/resources/js/customer/regulator/pandectstatistics/form.js"></script>