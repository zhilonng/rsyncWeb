<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String showDialogFileExit = "";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
try{
if((Boolean)session.getValue("file_exit")){
showDialogFileExit = "<script type='text/javascript'>alert('文件已备份，请选择其他目录')</script>";
}
}catch(Exception ev){

}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <%=showDialogFileExit  %>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'catalog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- Amaze UI CSS -->
	<link rel="stylesheet" href="lib/amazeui/css/amazeui.min.css">
	<link rel="stylesheet" href="css/layui.css"  media="all">
	<!-- 自己的css -->
	<link rel="stylesheet" href="css/FileSystem.css">

  </head>
  
  <body>
	<!-- 数据备份操作 -->
	<fieldset class="layui-elem-field layui-field-title site-demo-button" style="margin-top: 30px;">
	<legend style="font-size:20px;">请选取目标目录进行备份</legend>
	<div style="display:flex;flex-wrap:wrap;margin-left:20px;">
   <input  type="file" id="fileText" style="display:none;" multiple="multiple" webkitdirectory="">
   <input style="display:none;" type=button value=选择文件夹 onclick="BrowseFolder()"> <input style="display:none;" id="choosed-file-name" type="text" />
   <div data-method="notice" class="layui-btn layui-btn-danger" id="openLayerToCatalog" >选择目录 ></div>
   <input id="tv-localcatalog" type="text" value="您选择的备份目录" >
   <button class="layui-btn" onclick="startBackUps()">
	  <i class="layui-icon">&#xe608;</i> 开始备份
	</button>
    </div>
	<script src="lib/jquery.min.js"></script>
	<script src="lib/amazeui/js/amazeui.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
	
	<!-- 自己的js -->
	<script src="js/backUpFileSetUp.js"></script>
	<script>
	$("#openLayerToCatalog").on('click', function(){
	layui.use('layer', function(){
	 //iframe窗
	layer.open({
	  id:"iframe-chosecatalog",
	  type: 2,
	  title: false,
	  closeBtn: 0, //不显示关闭按钮
	  shade: [0],
	  area: ['50px', '0px'],
	  offset: 'rb', //右下角弹出
	  time: 2000, //2秒后自动关闭
	  anim: 2,
	  end: function(){ //此处用于演示
	    layer.open({
	      type: 2,
	      title: '请选择目录',
	      shadeClose: true,
	      shade: false,	
	      offset:"t",
	      maxmin: true, //开启最大化最小化按钮
	      area: ['893px', '600px'],
	      content: 'jsp/localcatalog.jsp'
	    });
	  }
	});
	});
  	});
	
	$("#setCatalog").on('click',function(){
	window.parent.setCatalog($("#Catalog").val());
	//关闭iframe页面
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
	});
	
	layui.use('element', function(){
	  var element = layui.element();
	  
	  //…
	});
	function setCatalog(path,index){
	if(index == 1){
	$("#tv-localcatalog").val(path);
	}
	}
	</script>
  </body>
</html>
