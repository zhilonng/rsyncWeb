<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="no-js">
 <%=session.getValue("newUser")  %>
 <%
 session.setAttribute("newUser", "");
  %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Chuck文件管理系统</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/icon" href="i/favicon.ico">

  <!-- Amaze UI CSS -->
  <link rel="stylesheet" href="lib/amazeui/css/amazeui.min.css">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
	<!-- 自己的css -->
 <link rel="stylesheet" href="css/FileSystem.css">
</head>
<body class="doc-am-g " style="background-color: #fff;">
<header class="am-topbar am-topbar-inverse Header" >
  <h1 class="am-topbar-brand ">
    <a href="#"><img src="i/login_w.png" alt="Chuck文件管理系统"></a>
  </h1>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav">
      <li class="am-active"><a href="#">首页</a></li>
    </ul>

     <div class="am-topbar-right" id="exit">
    <form action="logout">
      <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">退出</button>
     </form>
    </div>

    <div class="am-topbar-right " id="userName">
    	<span class="am-icon-user"> <s:property value="name"/></span>
    </div>

  </div>
</header>
	
  <div class="am-g am-g-collapse">
    <div class="am-u-sm-2 left" style="width:16%;background:#393D49;">
	<ul class="layui-nav layui-nav-tree" lay-filter="demo" style="width:100%;">
	  <li class="layui-nav-item layui-nav-itemed">
	    <a href="javascript:;">操作功能</a>
	    <dl class="layui-nav-child">
	      <dd><a href="javascript:;" onclick="turnToParamSetUp()">修改配置参数</a></dd>
	      <dd><a href="javascript:;" onclick="turnToBackUpControl()">数据备份操作</a></dd>
	      <dd><a href="javascript:;">查看任务信息</a></dd>
	    </dl>
	  </li>
	  <li class="layui-nav-item">
	    <a href="javascript:;">传输列表</a>
	    <dl class="layui-nav-child">
	      <dd><a onclick="tunToBackUpList()">正在备份</a></dd>
	      <dd><a href="">完成备份</a></dd>
	      <dd><a href="">垃圾站</a></dd>
	    </dl>
	  </li>
	</ul>
    </div>
    <iframe id="iframe-right-page" src="paramSetUp.jsp" style="width:84%;height:1000px;"></iframe>
    <!-- 配置参数设置界面 -->
    <div class="am-u-sm-10" id="dv_modyConfig">
    <div class="am-g am-g-collapse">
    <form action="reviseConfigure" method="post">
    <div class="FileSys-func-modyconfig">
    ip地址:<input type="text" name="ip" value=<%=session.getValue("ip")  %>><br />
    端口号:<input type="text" name="port" value=<%=session.getValue("port")  %>><br />
    FTP帐号:<input type="text" name="ftpusername" value=<%=session.getValue("ftp_username")  %>><br />
    FTP密码:<input type="password" name="ftppassword" value=<%=session.getValue("ftp_password")  %>><br />
    备份目录:<input type="text" name="catalog" value= <%=session.getValue("path")  %>> <br />
    <input class="tv_selectcata" type="submit" value="修改">
    </div>
    </form>
    <input class="tv_selectcata"  type="submit" value="选择目录" onclick="doChooseCatalog()">
    <div id="dv_catalog" style="display:<%=session.getValue("isShowCatalog") %>">
    <ol class="am-breadcrumb">
			 	<li><a href='<s:url action="lastCatalog"/>'>返回上一级|</a></li>
			 	  <li><a href='<s:url action="doChooseCatalog"/>'>主目录</a></li>	 	  
			 	</ol>
    <table class="am-table am-table-hover " id ='fileTable'>
    <tbody>
    <%=session.getValue("cataloglist") %>
    </tbody>
    </div>
    </table>
    <ol class="am-breadcrumb">
	<li><a href="<s:url action="nextPage?pageOpType=lastpage"/>">上一页</a></li>
	<li><a href="<s:url action="nextPage?pageOpType=nextpage"/>" >下一页</a></li> 
	</ol>
	</div>
    </div>
    </div>
    
    

<script src="lib/jquery.min.js"></script>
<script src="lib/amazeui/js/amazeui.min.js"></script>
<script src="js/layui.js" charset="utf-8"></script>

<!-- 自己的js -->
<script src="js/js.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('element', function(){
  var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    layer.msg(elem.text());
  });
});
</script>
</body>
</html>