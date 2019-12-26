var dashboardTimeoutSeed;
var webside = {
    index : {
        initHomePage : function() {
            $(".page-content").load(sys.rootPath + "/welcome.jsp");
            $(".breadcrumb").html('<li><i class="ace-icon fa fa-home home-icon"></i><a href="javascript:webside.index.initHomePage();">首页</a></li>');
        },
        initMenu : function() {
            $("[nav-menu]").each(function() {
                $(this).bind("click", function() {
                    var nav = $(this).attr("nav-menu");
                    var sn = nav.split(",");
                    if (sn[sn.length - 1] == '/sirona' || sn[sn.length - 1] == '/druid') {
                        window.open(sys.rootPath + sn[sn.length - 1]);
                        //处理目录类型的点击事件
                        $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
                        //处理菜单类型的点击事件
                        $(this).parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
                        $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
                        $(this).parent().addClass("active").parent().parent("li").addClass('active open');

                        //清除用户信息菜单样式
                        $(".user-menu").find('li').each(function() {
                            $(this).removeClass('active');
                        });

                    } else {
                        webside.index.beforeMenuClick();
                        var breadcrumb = '<li><i class="ace-icon fa fa-home home-icon"></i><a href="javascript:webside.index.initHomePage();">首页</a></li>';
                        for (var i = 0; i < sn.length - 1; i++) {
                            breadcrumb += '<li class="active">' + sn[i] + '</li>';
                        }
                        //设置面包屑内容
                        $(".breadcrumb").html(breadcrumb);
                        //加载页面
                        $(".page-content").load(sys.rootPath + sn[sn.length - 1]);
                        //处理目录类型的点击事件
                        $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
                        //处理菜单类型的点击事件
                        $(this).parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
                        $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
                        $(this).parent().addClass("active").parent().parent("li").addClass('active open');

                        //清除用户信息菜单样式
                        $(".user-menu").find('li').each(function() {
                            $(this).removeClass('active');
                        });
                    }
                });
            });
        },
        initNavigation : function() {
            $("#ace-settings-navbar").click();
            $("#ace-settings-sidebar").click();
            //$("#ace-settings-breadcrumbs").click();

        },
        initScrollBar : function() {
            $("html").niceScroll({
                cursorborderradius : "5px",
                cursorwidth : 10
            });
        },
        /*
         * 监听浏览器窗口大小变化
         */
        resizeScrollBar : function() {
            $(window).resize(function() {
                $("html").getNiceScroll().resize();
            });
        },
        initMenuStyle : function() {
            $(".user-menu").find('li').each(function() {
                $(this).bind('click', function() {
                    $(this).siblings().removeClass('active');
                    $(this).addClass('active');
                });
            });
        },
        beforeMenuClick: function () {
            //自定义菜单点击之前操作
            if(dashboardTimeoutSeed) {
                clearTimeout(dashboardTimeoutSeed);
                dashboardTimeoutSeed = null;
            }
            $(window).off(".regulator");
            $(window).off(".dashboard");
            var sidebarCollapse = $("#sidebar-collapse");
            if(sidebarCollapse.length > 0) {
            	sidebarCollapse.off(".regulator");
            	sidebarCollapse.off(".dashboard");
            }
        }
    },
    common : {
        /**
         *加载非菜单展示页面
         * @param nav 待加载的资源URL
         */
        loadPage : function(nav) {
            //加载页面
            $(".page-content").load(sys.rootPath + nav);
        },
        /**
         * 新增
         * @param {Object} nav  提交url
         */
        addModel : function(nav) {
            //加载新增页面
            webside.common.loadPage(nav);
        },
        addModel1 : function(nav, formID) {
            //加载新增页面
        	
            webside.common.loadPage(nav);
            $(':input',formID)
            .not(':button,:submit, :reset')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
        },
        /**
         * 编辑
         * @param {Object} nav  提交url
         */
        editModel : function(nav) {
            //当前页码
            var nowPage = grid.pager.nowPage;
            //获取每页显示的记录数(即: select框中的10,20,30)
            var pageSize = grid.pager.pageSize;
            //获取排序字段
            var columnId = grid.sortParameter.columnId;
            //获取排序方式 [0-不排序，1-正序，2-倒序]
            var sortType = grid.sortParameter.sortType;
            //获取选择的行
            var rows = grid.getCheckedRecords();
            if (rows.length == 1) {
            	 if (nav == '/role/editUI.html') {
                     if (rows[0].name == '超级管理员') {
                         layer.msg('该角色为基础角色,不能编辑!', {
                             icon : 0
                         });
                         return false;
                     }else{
                    	 webside.common.loadPage(nav + '?id=' + rows[0].id + "&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
                     }
                 }else{

	                  webside.common.loadPage(nav + '?id=' + rows[0].id + "&page=" + nowPage + "&rows=" + pageSize + "&sidx=" + columnId + "&sord=" + sortType);
	                 }
            	 
            	 
            } else {
                layer.msg("你没有选择行或选择了多行数据", {
                    icon : 0
                });
            }
        },
        /**
         * 删除
         * @param {Object} nav  提交url
         * @param callback 主函数执行完毕后调用的回调函数名称
         */
        delModel : function(nav, callback) {
            var rows = grid.getCheckedRecords();
            if (rows.length == 1) {
                if (nav == '/user/deleteBatch.html') {
                    if (rows[0].role.name == '超级管理员') {
                        layer.msg('该用户为超级管理员,不能删除!', {
                            icon : 0
                        });
                        return false;
                    }
                }
                if (nav == '/role/deleteBatch.html') {
                    if (rows[0].name == '超级管理员') {
                        layer.msg('该角色为基础角色,不能删除!', {
                            icon : 0
                        });
                        return false;
                    }
                }
                layer.confirm('确认删除吗？', {
                    icon : 3,
                    title : '删除提示'
                }, function(index, layero) {
                    var delete_ids = [];
                    /*
                     $.each(rows, function(index, value) {
                     ids.push(this.id);
                     });
                     */
                    delete_ids.push(rows[0].id);
                    $.ajax({
                        type : "POST",
                        url : sys.rootPath + nav,
                        data : {
                            "ids" : delete_ids.join(',')
                        },
                        dataType : "json",
                        success : function(resultdata) {
                            if (resultdata.success) {
                                layer.msg(resultdata.message, {
                                    icon : 1
                                });
                                if (callback) {
                                    callback();
                                }
                            } else {
                                layer.msg(resultdata.message, {
                                    icon : 5
                                });
                            }
                        },
                        error : function(errorMsg) {
                            layer.msg('服务器未响应,请稍后再试', {
                                icon : 3
                            });
                        }
                    });
                    layer.close(index);
                });
            } else {
                layer.msg("你没有选择行或选择了多行数据", {
                    icon : 0
                });
            }
        },
        /**
         * 提交表单
         * 适用场景：form表单的提交，主要用在用户、角色、资源等表单的添加、修改等
         * @param {Object} commitUrl 表单提交地址
         * @param {Object} listUrl 表单提交成功后转向的列表页地址
         */
        commit : function(formId, commitUrl, jumpUrl) {
            //组装表单数据
            var index;
            var data = $("#" + formId).serialize();
            if (undefined != $("#pageNum").val()) {
                jumpUrl = jumpUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val() + '&status=' + $("#las_id").val();
            }
            $.ajax({
                type : "POST",
                url : sys.rootPath + commitUrl,
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
    },
    form : {
        user : {
            validateUserForm : function() {
                $('#userForm').validate({
                    errorElement : 'div',
                    errorClass : 'help-block',
                    focusInvalid : false,
                    ignore : "",
                    rules : {
                    	regionid : {
                            required : true,
                        },
                        orgid : {
                            required : true,
                        },
                        accountName : {
                            required : true,
                            email : true,
                            remote : {//更新时不验证
                                param : {
                                    url : sys.rootPath + '/user/validateAccountName.html',
                                    cache : false
                                },
                                depends : function() {
                                    return typeof ($("#userId").val()) == "undefined";
                                }
                            }
                        },
                        password : {
                            required : true,
                            minlength : 6
                        },
                        repassword : {
                            required : true,
                            minlength : 6,
                            equalTo : "#password"
                        },
                        userName : {
                            required : true
                        },
                        phonenumber : {
                        	required : true
                        },
                        "role.id" : {
                            required : true
                        }
                    },
                    messages : {
                    	regionid : {
                            required : "请选择所属区域",
                        },
                        orgid : {
                            required : "请选择所属组织",
                        },
                        accountName : {
                            required : "请填写邮箱",
                            email : "请填写正确的邮箱",
                            remote : "该邮箱已注册,请使用其他邮箱"
                        },
                        password : {
                            required : "请填写密码",
                            minlength : "密码长度不能少于6个字符"
                        },
                        repassword : {
                            required : "请再次填写密码",
                            minlength : "密码长度不能少于6个字符",
                            equalTo : "两次填写密码不一致，请重新填写"
                        },
                        userName : "请填写真实姓名",
                        phonenumber : "请填写手机号",
                        "role.id" : "请选择角色"
                    },
                    highlight : function(e) {
                        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                    },
                    success : function(e) {
                        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                        $(e).remove();
                    },
                    errorPlacement : function(error, element) {
                        if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                            var controls = element.closest('div[class*="col-"]');
                            if (controls.find(':checkbox,:radio').length > 1)
                                controls.append(error);
                            else
                                error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                        } else if (element.is('.select2')) {
                            error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                        } else if (element.is('.chosen-select')) {
                            error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                        } else
                            error.insertAfter(element.parent());
                    },
                    submitHandler : function(form) {
                        var userId = $("#userId").val();
                        var url = "";
                        if (userId != undefined) {
                            url = '/user/edit.html';
                        } else {
                            url = '/user/add.html';
                        }
                        webside.common.commit('userForm', url, '/user/listUI.html');
                    }
                });
            },
            validateUserPasswordForm : function() {
                $('#userPassword').validate({
                    errorElement : 'div',
                    errorClass : 'help-block',
                    focusInvalid : false,
                    ignore : "",
                    rules : {
                        password : {
                            required : true,
                            minlength : 6
                        },
                        repassword : {
                            required : true,
                            minlength : 6,
                            equalTo : "#password"
                        },
                        verification :{
                        	required : true
                        },
                        currentpassword :{
                        	required : true
                        }
                    },
                    messages : {
                        password : {
                            required : "请填写密码",
                            minlength : "密码长度不能少于6个字符"
                        },
                        repassword : {
                            required : "请再次填写密码",
                            minlength : "密码长度不能少于6个字符",
                            equalTo : "两次填写密码不一致，请重新填写"
                        },
                        verification:{
                        	required : "请输入验证码"
                        },
                        currentpassword:{
                        	required:"请输入原始密码"
                        }
                    },
                    highlight : function(e) {
                        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                    },
                    success : function(e) {
                        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                        $(e).remove();
                    },
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    },
                    submitHandler : function(form) {
                    	var index;
                        var data = $("#userPassword").serialize();
                   
                      
                        $.ajax({
                            type : "POST",
                            url : sys.rootPath + '/user/password.html',
                            data : data,
                            dataType : "json",
                            beforeSend : function() {
                                index = layer.load();
                            },
                            success : function(result) {
                         
                                layer.close(index);
                                if (result.success) {
                             
                                    layer.msg(result.message, {
                                        icon : 1
                                    });
                                    setTimeout(function(){
                                    	window.location.href=sys.rootPath + '/logout.html';
                                    	},1000);
                                } else {
                                    layer.msg(result.message, {
                                        icon : 5
                                    });
                                }
                            }
                        });
                    	
                      //  webside.common.commit('userPassword', '/user/password.html', '/logout.html');
                    },
                    invalidHandler : function(form) {
                    }
                });
            }
        },
        userInfo : {
            initButton : function() {
                $("#btnEdit").click(function() {
                    //按钮切换
                    $(this).hide();
                    $("#btnAdd").show();
                    //表单切换
                    $("#lableDiv").hide();
                    $("#formDiv").show();
                });
            },
            initBirthday : function() {
                $("#birthday").datepicker({
                    format : 'yyyy-mm-dd',
                    autoclose : true,
                    language : 'zh-CN',
                    todayHighlight : true,
                    clearBtn : true,
                    immediateUpdates : true,
                    clearDate : function() {
                        $("#birthday").val('').datepicker('update');
                    }
                });
            },
            validateUserInfoForm : function() {
                jQuery.validator.addMethod("phone", function(value, element) {
                    return this.optional(element) || /^1[3|4|5|7|8]\d{9}$/.test(value);
                }, "请输入11位手机号码");

                $('#userInfoForm').validate({
                    errorElement : 'div',
                    errorClass : 'help-block',
                    focusInvalid : false,
                    ignore : "",
                    rules : {
                        userName : {
                            required : true
                        },
                        "userInfo.sex" : {
                            required : true
                        },
                        "userInfo.birthday" : {
                            required : true
                        },
                        "userInfo.telephone" : {
                            required : true,
                            phone : 'required'
                        },
                        "userInfo.address" : {
                            required : true
                        }
                    },
                    messages : {
                        userName : "请填写真实姓名",
                        "userInfo.sex" : "请选择性别",
                        "userInfo.birthday" : "请填写出生日期",
                        "userInfo.telephone" : {
                            required : "请填写手机号码",
                            phone : "请填写11位手机号码"
                        },
                        "userInfo.address" : "请填写联系地址"
                    },
                    highlight : function(e) {
                        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                    },
                    success : function(e) {
                        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                        $(e).remove();
                    },
                    errorPlacement : function(error, element) {
                        if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                            var controls = element.closest('div[class*="col-"]');
                            if (controls.find(':checkbox,:radio').length > 1)
                                controls.append(error);
                            else
                                error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                        } else if (element.is('.select2')) {
                            error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                        } else if (element.is('.chosen-select')) {
                            error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                        } else
                            error.insertAfter(element.parent());
                    },
                    submitHandler : function(form) {
                        webside.common.commit('userInfoForm', '/user/editPersonInfo.html', '/user/infoUI.html?id=' + $("#userInfoId").val());
                    }
                });
            }
        },
        role : {
            validateRoleForm : function() {
                $('#roleForm').validate({
                    errorElement : 'div',
                    errorClass : 'help-block',
                    focusInvalid : false,
                    ignore : "",
                    rules : {
                    	regionid : {
                            required : true
                        },
                        orgid : {
                            required : true
                        },
                        name : {
                            required : true
                        },
                        key : {
                            required : true
                        }
                    },
                    messages : {
                    	regionid : {
                            required : "请填写所属区域",
                        },
                        orgid : {
                            required : "请填写所属组织",
                        },
                        name : "请填写角色名称",
                        key : "请填写角色标识"
                    },
                    highlight : function(e) {
                        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                    },
                    success : function(e) {
                        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                        $(e).remove();
                    },
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    },
                    submitHandler : function(form) {
                        var roleId = $("#roleId").val();
                        var url = "";
                        if (roleId != undefined) {
                            url = '/role/edit.html';
                        } else {
                            url = '/role/add.html';
                        }
                        webside.common.commit('roleForm', url, '/role/listUI.html');
                    }
                });
            }
        },
        resource : {
            initSourceType : function() {
                $('#type').select2({
                    language : "zh-CN",
                    minimumResultsForSearch : Infinity
                });
            },
            initSourceTree : function() {
                $('#parentId').select2({
                    placeholder : "请选择上级资源...",
                    language : "zh-CN",
                    minimumResultsForSearch : Infinity,
                    templateResult : webside.form.resource.formatState,
                    templateSelection : function(selection) {
                        return $.trim(selection.text);
                    }
                });
            },
            formatState : function(state) {
                if (!state.id) {
                    return state.text;
                }
                var $state = $('<span><i class="fa fa-file-text-o green"></i>&nbsp;&nbsp;' + state.text + '</span>');
                return $state;
            },
            initIcon : function() {
                $("#icon").bind('focus', function(event) {
                    var iconLayer = layer.open({
                        type : 2,
                        scrollbar : false,
                        content : sys.rootPath + '/resource/icon.html',
                        area : 'auto',
                        maxmin : true,
                        shift : 4,
                        title : '<i class="fa fa-cogs"></i>&nbsp;选择图标'
                    });
                    //弹出即全屏
                    layer.full(iconLayer);
                });
            },
            initType : function() {
                $("#parentId").change(function() {
                    var parentId = $.trim($(this).children('option:selected').val());
                    if (parentId == null || parentId == '') {
                        $("#iconDiv").show();
                        $("#sourceUrlDiv").hide();
                    } else {
                        $("#iconDiv").hide();
                        $("#sourceUrlDiv").show();
                    }
                });
            },
            validateResourceForm : function() {
                $('#resourceForm').validate({
                    errorElement : 'div',
                    errorClass : 'help-block',
                    focusInvalid : false,
                    ignore : "",
                    rules : {
                        parentId : {
                            required : true
                        },
                        name : {
                            required : true
                        },
                        sourceKey : {
                            required : true
                        },
                        type : {
                            required : true
                        },
                        sourceUrl : {
                            required : function() {
                                //仅目录菜单不需要url
                                var parentId = $.trim($("#parentId").val());
                                if (parentId == null || parentId == "") {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        },
                        icon : {
                            required : function() {
                                //仅目录菜单需要icon
                                var parentId = $.trim($("#parentId").val());
                                if (parentId == null || parentId == "") {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    },
                    messages : {
                        parentId : "请选择上级资源",
                        name : "请填写资源名称",
                        sourceKey : "请填写资源标识",
                        type : "请选择资源类型",
                        sourceUrl : "请填写资源URL",
                        icon : "请选择菜单图标"
                    },
                    highlight : function(e) {
                        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                    },
                    success : function(e) {
                        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                        $(e).remove();
                    },
                    errorPlacement : function(error, element) {
                        if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                            var controls = element.closest('div[class*="col-"]');
                            if (controls.find(':checkbox,:radio').length > 1)
                                controls.append(error);
                            else
                                error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                        } else if (element.is('.select2')) {
                            error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                        } else if (element.is('.chosen-select')) {
                            error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                        } else
                            error.insertAfter(element.parent());
                    },
                    submitHandler : function(form) {
                        var resourceId = $("#resourceId").val();
                        var url = "";
                        if (resourceId != undefined) {
                            url = '/resource/edit.html';
                        } else {
                            url = '/resource/add.html';
                        }
                        webside.common.commit('resourceForm', url, '/resource/listUI.html');
                    }
                });
            },
            authorize:{
                ids:[],
                initResourceTree:function()
                {
                    $('#tree').jstree({
                        "core" : {
                            'data' : {
                                "url" : sys.rootPath + "/resource/resourceTree.html?roleId="+$("#id").val(),
                                "dataType" : "json"
                            },
                            "themes" : {
                                "responsive" : true
                            },
                            "multiple" : true,
                            "animation" : 200,
                            "dblclick_toggle" : true,
                            "expand_selected_onload" : true
                        },
                        "checkbox" : {
                            "keep_selected_style" : true,
                            "three_state" : false,
                            "cascade" : "up"
                        },
                        "plugins" : ["checkbox"]
                    });
                },
                /**
                 * 递归遍历节点，获取该节点的所有父节点
                 * @param {Object} treeObj  jstree对象
                 * @param {Object} nodeObj  jstree node节点对象
                 */
                getParents:function(treeObj,nodeObj)
                {
                    var parentId = treeObj.get_parent(nodeObj);
                    if(parentId != "#")
                    {
                        webside.form.resource.authorize.ids.push(parentId);
                        webside.form.resource.authorize.getParents(treeObj,treeObj.get_node(parentId));
                    }
                },
                /**
                 * 提交表单
                 * 适用场景：form表单的提交，角色授权提交
                 * @param {Object} commitUrl 表单提交地址
                 * @param {Object} listUrl 表单提交成功后转向的列表页地址
                 */
                commitPerm:function(commitUrl, listUrl)
                {
                    webside.form.resource.authorize.ids = [];
                    var tree = $('#tree').jstree();
                    //获取所有选中节点id
                    var selectedIds = tree.get_checked();
                    //获取所有选中节点
                    var selected = tree.get_checked(true);
                    //遍历节点，获取选中节点的所有父节点
                    for(var i = 0; i<selected.length; i++)
                    {
                        webside.form.resource.authorize.getParents(tree,selected[i]);
                    }
                    var index;
                    $.ajax({
                        type : "POST",
                        url : sys.rootPath + commitUrl,
                        data : {
                            "roleId" : $("#id").val(),
                            "resourceIds" : _.union(webside.form.resource.authorize.ids,selectedIds).join(',')
                        },
                        dataType : "json",
                        beforeSend : function()
                        {
                            index = layer.load();
                        },
                        success : function(resultdata) {
                             layer.close(index);
                            if (resultdata.success) {
                                layer.msg(resultdata.message,{icon:1});
                                webside.common.loadPage(listUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val());
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
            }
        }
    }
};

function showMessage(typeId){
	webside.common.loadPage('/message/listUI.html?typeId='+typeId);
}

//防抖函数
function debounce(fn, delay, immediate) {
	var timeout,
		args,
		context,
		timestamp,
		result;
	var later = function() {
		//IE8不支持Date.now()
		var last = (new Date().getTime()) - timestamp;
		
		if(last < delay && last >= 0) {
			timeout = setTimeout(later, delay - last);
		} else {
			timeout = null;
			if(!immediate) {
				result = fn.apply(context, args);
				
				if(!timeout) {
					context = args = null;
				}
			}
		}
	};
	
	return function() {
		context = this;
		args = arguments;
		timestamp = new Date().getTime();
		var callNow = immediate && !timeout;
		if(!timeout) {
			timeout = setTimeout(later, delay);
		}
		if(callNow) {
			result = fn.apply(context, args);
			context = args = null;
		}
		return result;
	}
};

//节流函数
function throttle(method, duration, delay) {
	var timer = null,
		begin = new Date();
	
	return function() {
		var context = this,
			args = arguments,
			current = new Date();
		
		clearTimeout(timer);
		
		if(current - begin >= duration) {
			method.apply(context, args);
			begin = current;
		} else {
			timer = setTimeout(function() {
				method.apply(context, args);
			}, delay);
		}
	}
};