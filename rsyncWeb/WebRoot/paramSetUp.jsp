<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'paramSetUp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Amaze UI CSS -->
	<link rel="stylesheet" href="lib/amazeui/css/amazeui.min.css">
	<link rel="stylesheet" href="css/layui.css"  media="all">
	<!-- 自己的css -->
	<link rel="stylesheet" href="css/FileSystem.css">

  </head>
  
  <body>
	<fieldset class="layui-elem-field layui-field-title site-demo-button" style="margin-top: 30px;">
	<legend>参数修改</legend>
	</fieldset>
    <!-- 配置参数设置界面 -->
    <div class="am-u-sm-10" id="dv_modyConfig">
    <div class="am-g am-g-collapse">
    <form action="reviseConfigure" method="post">
    <div class="FileSys-func-modyconfig">
    <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">ip地址：</label>
    <div class="layui-input-block">
      <input style="width:60%;" type="text" name="ip" lay-verify="title" autocomplete="off" placeholder="请输入ip地址" class="layui-input" value=<%=session.getValue("ip")  %>>
    </div>
 	</div>
    <div class="layui-form-item">
 	<label class="layui-form-label" style="width:100px;">端口号:</label>
    <div class="layui-input-block">
      <input style="width:60%;" type="text" name="port" lay-verify="title" autocomplete="off" placeholder="请输入端口号" class="layui-input" value=<%=session.getValue("port")  %>>
    </div>
 	</div>
    <div class="layui-form-item">
 	<label class="layui-form-label" style="width:100px;">FTP帐号:</label>
    <div class="layui-input-block">
      <input style="width:60%;" type="text" name="ftpusername" lay-verify="title" autocomplete="off" placeholder="请输入ftp账号" class="layui-input" value=<%=session.getValue("ftp_username")  %>>
    </div>
 	</div>
 	 <div class="layui-form-item">
    <label class="layui-form-label" style="width:100px;">FTP密码:</label>
    <div class="layui-input-inline">
      <input style="width:100%; margin-left:5%" type="password" name="ftppassword" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" value=<%=session.getValue("ftp_password")  %>>
    </div>
    <div style="margin-left:3%" class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
  </div>
    <div class="layui-form-item">
 	<label class="layui-form-label" style="width:100px;">备份目录:</label>
    <div class="layui-input-block" style="display:flex;flex-wrap:wrap;">
      <input id="lay-catalog" style="width:60%;" type="text" name="catalog" lay-verify="title" autocomplete="off" placeholder="备份目录地址" class="layui-input" value=<%=session.getValue("path")  %>>
      <div data-method="notice" class="layui-btn layui-btn-danger" id="openLayerToCatalog" >选择目录 ></div>
    </div>
 	</div>
 	<!--  ip地址:<input type="text" name="ip" value=<%=session.getValue("ip")  %>><br />
    端口号:<input type="text" name="port" value=<%=session.getValue("port")  %>><br />
    FTP帐号:<input type="text" name="ftpusername" value=<%=session.getValue("ftp_username")  %>><br />
    FTP密码:<input type="password" name="ftppassword" value=<%=session.getValue("ftp_password")  %>><br />
    备份目录:<input type="text" name="catalog" value= <%=session.getValue("path")  %>> <br /> -->
   
    <button class="layui-btn" type="submit" style="width:100%;margin:0 auto；">提交</button>
    </div>
    </form>
    </div>
    
    </div>
    
    <script src="lib/jquery.min.js"></script>
	<script src="lib/amazeui/js/amazeui.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
	
	<!-- 自己的js -->
	<script src="js/js.js"></script>
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
	      content: 'catalog.jsp'
	    });
	  }
	});
	});
  	});
	function setCatalog(path,index){
	if(index == 0){
	$("#lay-catalog").val(path);
	}
	}
	</script>

  </body>
</html>
