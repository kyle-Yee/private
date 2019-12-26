var dtGridColumns = [{
    id : 'id',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'title',
    title : '标题',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    columnStyle:'color:#337ab7;cursor:pointer;'
},  {
    id : 'unread',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
		if(value != null || value !=""){
			if(value == "Y"){
				return "<span style='color:red;'>未读</span>";
			}else{
				return "<span>已读</span>";
			}
		}
    }
},{
    id : 'creatorid',
    title : '发送人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//根据用户ID查询用户名
		var userName=0;
		if(value != null || value !=""){
			$.ajax({
				async:false,
				type : "POST",
				url : sys.rootPath + "/message/getUserName.html",
				data : {
					"userId" : value
				},
				dataType : "html",
				success : function(resultdata) {
					userName = resultdata;
				}
			});
		}
		return userName;
    }
}, {
    id : 'createtime',
    title : '发送时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;
var typeId = $("#typeId").val();
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/message/list.html?typeId='+typeId,
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : '',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if(column.id == "title"){
    		showDetail(record.id);
    	}
    }
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    $("#btnSearch").click(customSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});

//弹出详细信息
function showDetail(id){
	layer.open({
		  title: "",
		  type: 2,
		  area: ['900px', '600px'],
		  fix: false, //不固定
		  maxmin: true,
		  content: sys.rootPath + '/message/detail.html?id='+id,
		  cancel: function(){
			  grid.refresh(true);
		  }
	});
}

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['fpName'] = $("#searchKey").val();
    grid.refresh(true);
}