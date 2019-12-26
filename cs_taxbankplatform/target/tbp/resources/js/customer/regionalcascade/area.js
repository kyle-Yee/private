var province=[];
var city=[];
var areas=[];
var currentCity;
$(function(){
	$.ajax({
		type : "post",
		url : sys.rootPath + '/cascade/findProvince_new.html',
		dataType : "json",
		success : function(list) {
			currentCity=list.currentCity;
			for(var i=0;i<list.province.length;i++){
				province[i]=list.province[i];
			}
			for(var i=0;i<list.city.length;i++){
				city[i]=list.city[i];
			}
			for(var i=0;i<list.area.length;i++){
				areas[i]=list.area[i];
			}
			   //调用外部文件area.js中的BindProvince函数，传递实在参数"province","city","area"（省、市、区）
			BindProvince("province","currentCity","city","area");
		}
	});
})

/*function promptinfo(){   //输入用户选择的省市区信息
	var getProv = document.getElementById('province');   //获取province（省）对象
	var selProv = getProv.options[getProv.selectedIndex].text;   //获取用户选择的省或直辖市名称
	var getCity = document.getElementById('city');   //获取city（市）对象
	var selCite = getCity.options[getCity.selectedIndex].text;   //获取用户选择的城市信息
	var getArea = document.getElementById('area');   //获取area（区）对象
	var selArea = getArea.options[getArea.selectedIndex].text;   //获取用户选择的区(或县)信息
	if(selProv.charAt(2)=="市"){ //根据第三个字判断是否是直辖市（北京市、上海市、天津市和重庆市）
		alert(selProv+selArea);  //输出直辖市（市和区）信息
	}else{
		alert(selProv+selCite+selArea);   //输出省市区（县）信息
	}
}*/
// 说明：省市区联动JS脚本

function BindProvince(ProvinceObjId,CcObjId,CityObjId,AreaObjId){   //绑定省，把html文件中的实在参数province,city,area分别传递给形式参数ProvinceObjId,CityObjId,AreaObjId
	var pobj=document.getElementById(ProvinceObjId);   //获取省对象
	var cobj=document.getElementById(CityObjId);   //获取市对象
	var aobj=document.getElementById(AreaObjId);   //获取区对象
	for(var i=0;i<province.length;i++){   //添加省份列表
	   pobj.options[i]=new Option(province[i].name,province[i].id);
//	   if (pobj.options[i].value == currentCity.id)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
//	      {
//	    	  pobj.options[i].selected = true;
//	    	  pobj.setAttribute("disabled","disabled");
//	      }
	}
//	if(arguments.length==3){   //用于设计省市二级联动，arguments是Javascript的特殊对象，引用属性arguments.length检测函数的参数个数。
//		ProvinceChange(ProvinceObjId,CityObjId); 
//		if(window.addEventListener){   //添加监听事件,如果省份发生变化调用ProvinceChange函数。
//			pobj.addEventListener('change',function(){ ProvinceChange(ProvinceObjId,CityObjId);}, false); 
//		} else { 
//			pobj.attachEvent('onchange',function(){ProvinceChange(ProvinceObjId,CityObjId);}); 
//		}     
//	}
	if(arguments.length==4){   //用于设计省市区三级联动，省市区三级联动时调用此段代码。
		ProvinceChange(ProvinceObjId,CityObjId); 
//		if (cobj.options[i].value == currentCity.id)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
//	      {
////	    	  pobj.options[i].selected = true;
////	    	  pobj.setAttribute("disabled","disabled");
//	      }
	 	if(window.addEventListener){   //添加监听事件,如果省份发生变化调用ProvinceChange函数。
			pobj.addEventListener('change',function(){ ProvinceChange(ProvinceObjId,CityObjId,AreaObjId);}, false);        
		} else { 
			pobj.attachEvent('onchange',function(){ProvinceChange(ProvinceObjId,CityObjId,AreaObjId);});       
		}    
		CityChange(CityObjId,AreaObjId);
//	 	var cobj=document.getElementById(CityObjId);
	 	if(window.addEventListener){   //添加监听事件,如果城市发生变化调CityChange函数。
			cobj.addEventListener('change',function(){ CityChange(CityObjId,AreaObjId);}, false);        
	 	} else { 
			cobj.attachEvent('onchange',function(){CityChange(CityObjId,AreaObjId);});       
		}     
	}
}
function ProvinceChange(ProvinceObjId,CityObjId,AreaObjId){   //省市区三级联动
	var pobj=document.getElementById(ProvinceObjId);
	var pid=pobj.value;
	var cobj=document.getElementById(CityObjId);
	var count=0;
	cobj.options[0]=new Option("市/州：",0);
   	for(var i=0;i<city.length;i++){
		if(city[i].topid==pid){   //根据id添加对应省份的城市列表
			if (city.length != 1){
				cobj.options[count+1]=new Option(city[i].name,city[i].id);
		   		count++;
			}else {
				cobj.options.length=0;
				cobj.options[count]=new Option(city[i].name,city[i].id);
		   		count++;
			}
	  		
	 	}
	}
	cobj.length=count+1;
  	if(arguments.length==3){   //如何有区（或县），调用CityChange函数，实现三级联动。
		CityChange(CityObjId,AreaObjId); 
	}
}
function CityChange(CityObjId,AreaObjId){   //市区（或县）联动
	var cobj=document.getElementById(CityObjId);
	var cid=cobj.value;
	var aobj=document.getElementById(AreaObjId);
	var count=0;
//	aobj.options[0]=new Option("请选择",0);
	var selectVal=document.getElementById(CityObjId).value;
	if(selectVal == 0){
		$("#area").empty();
		aobj.options[0]=new Option("区/县：",0);
	}else{
		for(var i=0;i<areas.length;i++){
			if(areas[i].topid==cid){   //根据id添加对应城市的区、县列表。
				
//				aobj.options[count]=new Option(areas[i].name,areas[i].id);
//				count++;
				
				if (areas.length != 1){
					aobj.options[count+1]=new Option(areas[i].name,areas[i].id);
			   		count++;
				}else {
					aobj.options.length=0;
					aobj.options[count]=new Option(areas[i].name,areas[i].id);
			   		count++;
				}
			}
		}
//		aobj.length=count;	
		aobj.length=count+1;
	}
}
