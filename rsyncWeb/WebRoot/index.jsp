<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>	
<head>
<title>前端数据备份系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <%
 session.setAttribute("newUser", "");
  %>
 <!--SIGN UP-->
 <h1>登录</h1>
<div class="login-form">
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="images/avtar.png" />
	</div>
			<form  action="login" method="post">
					<input type="text" id="tv_name" class="text"  name="name" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
						<div class="key">
					<input type="password" id="tv_password" name="password"  value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
						</div>	
	<div class="signin">
		<input class="index_login"  type="submit" value="登录"  >
	</div>
	</form>
	<div class="signin">
		<input class="index_reg"  type="submit" onclick="doregister()"  value="注册"  >
	</div>

	<script type="text/javascript">
	function doregister() {
		var tv_name = document.getElementById("tv_name");
		var tv_password = document.getElementById("tv_password");
		var name =tv_name.value;
		var password = tv_password.value;
		if(name == ""){
		alert("帐号不能为空");
		}else if(password == ""){
		alert("密码不能为空");
		}else{
		window.location.href="register?name="+name+"&"+"password="+password;
		}
	}
	</script>
</div>

</body>
</html>
