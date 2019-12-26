<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
  	</head>
 
	<body  style="text-align: center;">
	
		<img src="${imgPath }" style="max-width: 100%;height: auto;"/>
<!-- 		<img src="${imgPath }" id="img" onload="formatImg2(this,740,460);" /> -->
		
	</body>
	
	  <script type="text/javascript">
	  //不按比例压缩
	  function formatImg(imgObject,width,height){
		    imgObject.height = imgObject.height>width?width:imgObject.height;
		    imgObject.width = imgObject.width>height?height:imgObject.width;
		}
	  
	  //按比例压缩//750，460
	  function formatImg2(imgObject,width,height){
		  console.log(imgObject.parentNode.offsetHeight);
		  	var myHeight =  imgObject.height;//原来的高度
		  	var myWidth = imgObject.width;//原来的宽度
		  	var rate = myWidth/myHeight;//宽高比
		  	if(myHeight>height){
		  		imgObject.height = height;
		  		imgObject.width = height*rate;
		  	}
		  	if(myWidth > width){
		  		imgObject.width = width;
		  		imgObject.height = width/rate;
		  	}
		}
	  
// 	  $(".layui-layer-max").click(function(){
		  
// 		  alert("sss");
// 	  });
// 	    var img = document.getElementById("img");//通过ID获取IMG元素  
	  
// 	    var image = new Image();//new一个image对象  
	  
// 	    image.src=img.src;  
// 	    获取尺寸  
	  
// 	    image.width;//宽  
// 	    image.height;//高  
// 	    if(image.width>=749){
// 	   		 document.getElementById("img").setAttribute("width","749px");
// 	   	}

    
      </script>
</html>
