<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
	  <div style="margin:0" class="layui-tab layui-tab-card">
	  <ul class="layui-tab-title">
	    <li class="layui-this" >目录选取</li>
	    <li id="li-backup-ing" onclick="get_backup_data()">正在备份</li>
	    <li onclick="get_backup_data()">备份完成</li>
	    <li onclick="get_backup_data()">垃圾箱</li>
	  </ul>
	  <div class="layui-tab-content" style="height: 100px;padding:0;">
	    <div class="layui-tab-item layui-show">
	    <iframe id="iframe-backupsetup" style="width:100%;height:700px;" src="backUpFileSetUp.jsp"></iframe></div>
	    <div class="layui-tab-item">
	    <iframe id="iframe-backuplist" style="width:100%;height:700px;" src="backUpList.jsp"></iframe></div>
	    <div class="layui-tab-item">
	    <iframe id="iframe-backupcopmplete" style="width:100%;height:700px;" src="backUpCompelteList.jsp"></iframe></div>
	    <div class="layui-tab-item">
	    <iframe id="iframe-backupdelete" style="width:100%;height:700px;" src="jsp/backUpDeleteList.jsp"></iframe></div></div>
	  </div>
	</div>
	<script src="lib/jquery.min.js"></script>
	<script src="lib/amazeui/js/amazeui.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
	
	<!-- 自己的js -->
	<script src="js/js.js"></script>
	<script src="js/backUpControl.js"></script>
	<script>
	$("#setCatalog").on('click',function(){
	window.parent.setCatalog($("#Catalog").val());
	//关闭iframe页面
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	console.log(index);
	parent.layer.close(index);
	});
	
	layui.use('element', function(){
	  var element = layui.element();
	  //…
	});
	</script>
  </body>
</html>
