$(document).ready(function() {
    $("body").css("background","#16a08");
    /*
    //背景粒子效果
    $('.main-container').particleground({
        dotColor : '#5cbdaa',
        lineColor : '#5cbdaa'
    });
    $('.main-content').css({
        'margin-top' : -($('.main-content').height())
    });
    */
    //刷新验证码
    $('#kaptchaImage').click(function() {//生成验证码
        $(this).hide().attr('src', 'captcha.html?' + new Date().getTime()).fadeIn();
    });

    //登录、注册、找回密码切换
    $(document).on('click', '.toolbar a[data-target]', function(e) {
        e.preventDefault();
        var target = $(this).data('target');
        //隐藏其他dom
        $('.widget-box.visible').removeClass('visible');
        //显示目标dom
        $(target).addClass('visible');
    });
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            login();
        }
    };
    
});


//登录
function login() {
	
    if($("#accountName").val() == "")
    {
        layer.alert('请输入账户邮箱', {icon : 5,shift : 6,time : 0});
        $("#accountName").focus();
        return false;
    }else
    {	
        var reg = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        if(!reg.test($("#accountName").val()))
        {
            layer.alert('请输入正确的邮箱', {icon : 5,shift : 6,time : 0});
            $("#accountName").focus();
            return false;
        }
    }
    if($("#password").val() == "")
    {
        layer.alert('请输入密码', {icon : 5,shift : 6,time : 0});
        $("#password").focus();
         return false;
    }
    
    if($("#captcha").val() == "")
    {
        $("#captcha").focus();
        layer.alert('请输入验证码', {icon : 5,shift : 6,time : 0});
         return false;
    }
    if($("#rememberMeCheckBox").is(':checked'))
    {
        $("#rememberMe").val(true);
    }
    testname();
    tespassword();
    $("#loginform").submit();
}
function testname(){
var testname=$("#accountName").val();
var base = new Base64();  
var userNameBase64 = base.encode(testname); 
$("#accountName").val(userNameBase64)
}
function tespassword(){
	var testpassword=$("#password").val();
	var base = new Base64();  
	var passwordBase64 = base.encode(testpassword); 
	$("#password").val(passwordBase64)
}
//注册
function register() {
    var accountName = $("#registerAccountName").val();
    if(accountName == "")
    {
        layer.alert('请输入邮箱', {icon : 5,shift : 6,time : 0});
        $("#registerAccountName").focus();
        return false;
    }else
    {
        var reg = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        if(!reg.test(accountName))
        {
            layer.alert('请输入正确的邮箱', {icon : 5,shift : 6,time : 0});
            $("#registerAccountName").focus();
            return false;
        }else
        {
            var flag = true;
            $.ajax({
                type : "POST",
                url : sys.rootPath + "/user/validateAccountName.html",
                data : {
                    "accountName" : accountName
                },
                dataType : "json",
                async : false,
                success : function(resultdata) {
                    if (!resultdata) {
                        layer.msg("该邮箱已注册,请使用其他邮箱",{icon:5});
                        $("#registerAccountName").focus();
                        flag = false;
                    } 
                },
                error : function(errorMsg) {
                    layer.msg('服务器未响应,请稍后再试',{icon:2});
                    $("#registerAccountName").focus();
                    flag = false;
                }
            });
            if(!flag)
            {
                return flag;   
            }
        }
    }
    if($("#registerUserName").val() == "")
    {
        layer.alert('请输入真实姓名', {icon : 5,shift : 6,time : 0});
        $("#registerUserName").focus();
        return false;
    }
    if($("#registerPassword").val() == "")
    {
        layer.alert('请输入密码', {icon : 5,shift : 6,time : 0});
        $("#registerPassword").focus();
        return false;
    }else
    {
        if($("#registerPassword").val().length<6)
        {
            layer.alert('密码长度不能小于6位字符', {icon : 5,shift : 6,time : 0});
            $("#registerPassword").focus();
            return false;
        }
    }
    if($("#registerRePassword").val() == "")
    {
        layer.alert('请输入确认密码', {icon : 5,shift : 6,time : 0});
        $("#registerRePassword").focus();
        return false;
    }
    if($("#registerPassword").val() != $("#registerRePassword").val())
    {
        layer.alert('两次输入密码不一致,请重新输入', {icon : 5,shift : 6,time : 0});
        $("#registerPassword").focus();
        return false;
    }
    
    // add by zhangmz 2016-06-24 增加用户协议同意认证
    // 服务器端暂时未添加这个校验
    if(!($("#registerAccept").is(':checked')))
    {
    	$("#registerAccept").focus();
        layer.alert('请查阅并同意用户协议', {icon : 5,shift : 6,time : 0});
        return false;
    }
    
    $("#registerform").submit();
}


        

//找回密码
function resetPassword()
{ 
    var accountName = $("#recoverAccoutName").val();
    if(accountName == "")
    {
        layer.alert('请输入账户邮箱', {icon : 5,shift : 6,time : 0});
        return;
    }
    var index = layer.load();
    $.ajax({
        type : "POST",
        // url : sys.rootPath + '/user/resetPassword.html',
        url : sys.rootPath + '/user/resetPassWithoutAuthc.html',
        data : {
            "accountName" : accountName
        },
        dataType : "json",
        success : function(resultdata) {
            layer.close(index);
            if (resultdata.success) {
                layer.msg('您好,' + resultdata.message + ',请登录您的邮箱查看', {
                    icon : 1
                });
                $('#recoverWidget').click();
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(errorMsg) {
            layer.close(index);
            layer.msg('服务器未响应,请稍后再试', {
                icon : 2
            });
        }
    });
    
}

//弹出用户协议
function userAgreement(){
	layer.open({
		  type: 2,
		  area: ['700px', '530px'],
		  fix: false, //不固定
		  maxmin: true,
		  content: sys.rootPath + '/sypt/userAgreement.html'
		});
}
function Base64() {  
	   
    // private property  
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";  
   
    // public method for encoding  
    this.encode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = _utf8_encode(input);  
        while (i < input.length) {  
            chr1 = input.charCodeAt(i++);  
            chr2 = input.charCodeAt(i++);  
            chr3 = input.charCodeAt(i++);  
            enc1 = chr1 >> 2;  
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
            enc4 = chr3 & 63;  
            if (isNaN(chr2)) {  
                enc3 = enc4 = 64;  
            } else if (isNaN(chr3)) {  
                enc4 = 64;  
            }  
            output = output +  
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +  
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);  
        }  
        return output;  
    }  
   
    // public method for decoding  
    this.decode = function (input) {  
        var output = "";  
        var chr1, chr2, chr3;  
        var enc1, enc2, enc3, enc4;  
        var i = 0;  
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");  
        while (i < input.length) {  
            enc1 = _keyStr.indexOf(input.charAt(i++));  
            enc2 = _keyStr.indexOf(input.charAt(i++));  
            enc3 = _keyStr.indexOf(input.charAt(i++));  
            enc4 = _keyStr.indexOf(input.charAt(i++));  
            chr1 = (enc1 << 2) | (enc2 >> 4);  
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);  
            chr3 = ((enc3 & 3) << 6) | enc4;  
            output = output + String.fromCharCode(chr1);  
            if (enc3 != 64) {  
                output = output + String.fromCharCode(chr2);  
            }  
            if (enc4 != 64) {  
                output = output + String.fromCharCode(chr3);  
            }  
        }  
        output = _utf8_decode(output);  
        return output;  
    }  
   
    // private method for UTF-8 encoding  
    _utf8_encode = function (string) {  
        string = string.replace(/\r\n/g,"\n");  
        var utftext = "";  
        for (var n = 0; n < string.length; n++) {  
            var c = string.charCodeAt(n);  
            if (c < 128) {  
                utftext += String.fromCharCode(c);  
            } else if((c > 127) && (c < 2048)) {  
                utftext += String.fromCharCode((c >> 6) | 192);  
                utftext += String.fromCharCode((c & 63) | 128);  
            } else {  
                utftext += String.fromCharCode((c >> 12) | 224);  
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);  
                utftext += String.fromCharCode((c & 63) | 128);  
            }  
   
        }  
        return utftext;  
    }  
   
    // private method for UTF-8 decoding  
    _utf8_decode = function (utftext) {  
        var string = "";  
        var i = 0;  
        var c = c1 = c2 = 0;  
        while ( i < utftext.length ) {  
            c = utftext.charCodeAt(i);  
            if (c < 128) {  
                string += String.fromCharCode(c);  
                i++;  
            } else if((c > 191) && (c < 224)) {  
                c2 = utftext.charCodeAt(i+1);  
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));  
                i += 2;  
            } else {  
                c2 = utftext.charCodeAt(i+1);  
                c3 = utftext.charCodeAt(i+2);  
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));  
                i += 3;  
            }  
        }  
        return string;  
    }  
}