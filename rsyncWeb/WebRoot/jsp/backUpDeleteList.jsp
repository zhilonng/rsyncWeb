<%@ page language="java" import="java.util.*,com.scut.rsyncWeb.entity.FileInfo" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<!-- 数据备份操作 -->
	<table style="margin:0;" class="layui-table" lay-skin="line">
	  <colgroup>
	    <col width="150">
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	      <th>文件名</th>
	      <th>所有者</th>
	      <th>类型</th>
	      <th>备份时间</th>
	      <th>操作</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <s:iterator value="#session.list_backup_delete_files" var="fileinfo"> 
	    <tr>
		      <td><i class="am-icon-folder colorFolder"></i><s:property value="#fileinfo.fileName" /></td>
		      <td><s:property value="#fileinfo.fileOwner" /></td>
		      <td><s:property value="#fileinfo.type" /></td>
		      <td><s:property value="#fileinfo.time" /></td>
		      <td>
		      	<img style="height:20px; width:20px;" src="images/ic_yes.png"/>
				<img style="height:20px; width:15px;" onclick="stopBackUp()" src="images/gf_loading.gif" />
				<button class="layui-btn layui-btn-small">
			    <i class="layui-icon">&#xe640;</i>
			 	 </button>
				</td>
		    </tr>
		</s:iterator> 
	 </tbody>
	</table>   
	
	<script src="lib/jquery.min.js"></script>
	<script src="lib/amazeui/js/amazeui.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
	
	<!-- 自己的js -->
	<script src="js/js.js"></script>
	<script>
	$("#setCatalog").on('click',function(){
	window.parent.setCatalog($("#Catalog").val());
	//关闭iframe页面
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
	});
	</script>
  </body>
</html>

