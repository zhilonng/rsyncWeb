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
  备份目录:<input id="Catalog" type="text" name="catalog" value= <%=session.getValue("path")  %>>
  <a id="setCatalog" class="layui-btn" target="_blank" style="width:100px;">确认</a> <br />
    <input class="tv_selectcata"  type="submit" value="选择目录" onclick="doChooseCatalog()">
    <div id="dv_catalog" style="display:<%=session.getValue("isShowCatalog") %>">
    <ol class="am-breadcrumb">
			 	<li><a href="<s:url action='lastCatalog'/>">返回上一级|</a></li>
			 	  <li><a onclick="doChooseCatalog()">主目录</a></li>	 	  
			 	</ol>
    <table class="am-table am-table-hover " id ='fileTable'>
    <tbody class="width:400px;height:300px;">
    <%=session.getValue("cataloglist") %>
    </tbody>
    </table>
    <ol class="am-breadcrumb">
	<li><a href='<s:url action="nextPage?pageOpType=lastpage"/>'>上一页</a></li>
	<li><a href='<s:url action="nextPage?pageOpType=nextpage"/>'>下一页</a></li> 
	</ol>
	</div>
	
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
