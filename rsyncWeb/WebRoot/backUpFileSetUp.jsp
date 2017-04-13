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
   <input  type="file" id="fileText" multiple="multiple" webkitdirectory="">
   <input style="display:none;" type=button value=选择文件夹 onclick="BrowseFolder()"> <input style="display:none;" id="choosed-file-name" type="text" />
   <button class="layui-btn" onclick="startBackUps(document.getElementById('fileText'))">
	  <i class="layui-icon">&#xe608;</i> 开始备份
	</button>
    </div>
	<script src="lib/jquery.min.js"></script>
	<script src="lib/amazeui/js/amazeui.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
	
	<!-- 自己的js -->
	<script src="js/backUpFileSetUp.js"></script>
	<script>
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
	</script>
  </body>
</html>
