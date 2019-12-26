var zNodes;
var setting = {  
    check: {  
        nocheckInherit: false,
        enable: true,  
        chkboxType:{ "Y" : "s", "N" : "ps" }
    },  
    data: {  
        simpleData: {  
            enable: true  
        }  
    },
    callback: {
		 onCheck: onCheck
	}  
};  
//ztree用于初始化的静态数据          
$(function(){  	
	validateForm();
	getzTree();
	//showMenu();
	//showData();
});
function showData(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var node = treeObj.getNodeByParam("id", 5, null);
	treeObj.checkNode(node, true, true);
}
function getzTree(){
	var str = "[";
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",  
        url: sys.rootPath + '/taxauthority/findAuthority.html?idList='+$("#idList").val(idList),
        error: function () {
            alert('请求失败');  
        },  
        success:function(data){     
        	 jQuery.each(data, function(i, item) {
                 str = str + "{ id:" + item.id + ", pId:" + item.taxPrcId + ", open: false , name:\"" + item.taxName + "\"},";
             });
             str = str.substring(0, str.length - 1);
             str = str + "]";
             zNodes = eval("(" + str + ")");
             $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }  
    });
}


/**
 * 表单验证
 */
function validateForm()
{    
    $('#allocateForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	orgname : {
                required : true,
            },
    		regionname: {
                required : true,
            }
        },
        messages : {
        	orgname : {
                required : "区域名称不能为空",
            },
            regionname: {
            	required : "请选择区域机关",
            }
        },
        highlight : function(e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success : function(e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        },
        submitHandler : function(form) {
            var url = '/organization/allocate.html';
            webside.common.commit('allocateForm', url, '/organization/listUI.html');
        }
    });
}

//显示下拉选区域  
function showMenu() {
	var cityObj = $("#regionname");
	var cityOffset = $("#regionname").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
//隐藏下拉选区域
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getCheckedNodes(true);
	
	v = "";
	vid = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		vid += nodes[i].id + ",";
	}
	
	var cityObj = $("#regionname");
	cityObj.attr("value", v);
	var rcid = $("#tax");
	rcid.attr("value",vid);
}