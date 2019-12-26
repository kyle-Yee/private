var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
		
  		var zNodes;		
  		$(function(){  
			var str = "[";
		    $.ajax({  
		        async : false,  
		        cache:false,  
		        type: 'POST',  
		        dataType : "json",  
		        url: '<%=request.getContextPath()%>/provincecities/findCities.html',//请求的action路径  
		        error: function () {//请求失败处理函数  
		            alert('请求失败');  
		        },  
		        success:function(data){ //请求成功后处理函数。    
		        	 jQuery.each(data, function(i, item) {
		                 str = str + "{ id:" + item.id + ", pId:" + item.pcpid + ", open: false , name:\"" + item.pcname + "\"},";
		             });
		             str = str.substring(0, str.length - 1);
		             str = str + "]";//此时str是JSON字符串//console.log(str);
		             zNodes = eval("(" + str + ")");//这货到底是做神马啊?把JSON字符串转换成JSON对象 
		        }  
		    });
		});
		/* var zNodes =[
			{id:1, pId:0, name:"北京"},
			{id:2, pId:0, name:"天津"},
			{id:3, pId:0, name:"上海"},
			{id:6, pId:0, name:"重庆"},
			{id:4, pId:0, name:"河北省", open:true},
			{id:41, pId:4, name:"石家庄"},
			{id:42, pId:4, name:"保定"},
			{id:43, pId:4, name:"邯郸"},
			{id:44, pId:4, name:"承德"},
			{id:5, pId:0, name:"广东省", open:true},
			{id:51, pId:5, name:"广州"},
			{id:52, pId:5, name:"深圳"},
			{id:53, pId:5, name:"东莞"},
			{id:54, pId:5, name:"佛山"},
			{id:6, pId:0, name:"福建省", open:true},
			{id:61, pId:6, name:"福州"},
			{id:62, pId:6, name:"厦门"},
			{id:63, pId:6, name:"泉州"},
			{id:64, pId:6, name:"三明"}
		 ]; */

		function beforeClick(treeId, treeNode) {
			var check = (treeNode && !treeNode.isParent);
			/* if (!check) alert("只能选择城市...");
			return check; */
		} 
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#regionName");
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#regionName");
			var cityOffset = $("#regionName").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});