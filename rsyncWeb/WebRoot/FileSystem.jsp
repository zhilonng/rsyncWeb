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
    <div class="am-u-sm-2 left">

      <div class="am-panel am-panel-default">
        <div class="am-panel-hd"><i class="am-icon-th-large"></i> 操作功能</div>
        <div class="am-panel-bd">
        <button type="button" class="am-btn  am-round am-btn-block functionBtn"  onclick="displayConfig()">
        <i class="am-icon-plus-square"></i> 修改配置参数</button>
		<button type="button" class="am-btn  am-round am-btn-block functionBtn"  onclick="displayOperation()">
		<i class="am-icon-plus-square"></i> 数据备份操作</button>
		<button type="button" class="am-btn  am-round am-btn-block functionBtn"  onclick="displayTestList()">
		<i class="am-icon-minus-square"></i> 查看任务信息</button>
        </div>
      </div>
      <div class="am-panel am-panel-default">
        <div class="am-panel-hd"><i class="am-icon-th-large"></i> 其他功能</div>
        <div class="am-panel-bd">
        <div id="storgeInformation">
        <div class="am-progress am-active am-progress-striped am-progress-sm">
          <div class="am-progress-bar "  style="width: 99%"></div>
        </div>
        <div class="am-fc">
        	<span class="am-fr">25/30读取失败</span>
        </div>
        </div>
        
        <button type="button" id="showStorgeBtn" class="am-btn  am-round am-btn-block functionBtn ">
        <i class="am-icon-search"></i> 查看硬盘使用情况</button>
        </div>
      </div>
    </div>
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
			 	<li><a href="<s:url action="lastCatalog"/>">返回上一级|</a></li>
			 	  <li><a href="<s:url action="doChooseCatalog"/>">主目录</a></li>	 	  
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
    
    <!-- 数据备份操作 -->
    <div class="am-u-sm-10" id="dv_opration"  style="display:none">
    	<div class="am-g am-g-collapse" >
    		<div class="am-u-sm-12">
    		<span></span>
    		   
			 	<ol class="am-breadcrumb">
			 	<li><a href="#">返回上一级|</a></li>
			 	  <li><a href="javascript:enterFolder(0);">主目录</a></li>	 	  
			 	  <li><input id="fileText" type="file" value="选择备份目录" ></li>
			 	  <li><input type="submit" value="开始备份" onclick="startBackUps(document.getElementById('fileText'))"></li>
			 	</ol>
    		</div>
    		<div class="am-u-sm-12">
    			<table class="am-table am-table-hover " id ='fileTable'>
    			    <thead>

    			        <tr>
    			        	<th class= "checkbox">   			        	
    			        	<input type="checkbox" id="checkCtl" >		     
    			        	</th>
    			            <th>文件名</th>
    			            <th>所有者
    			            <th>类型</th>
    			            <th>创建时间</th>
    			            <th class="lockStateTitle">操作</th>
    			        </tr>
    			    </thead>
    			    <tbody>
    			    	<tr>
    			    	<td></td>
    			    	<td class="fileName">
    			    	<i class="am-icon-folder colorFolder"></i>haha</td>
    			    	<td class="fileOwner">haha</td>
    			    	<td class="fileType">haha</td>
    			    	<td class="fileCreateTime">haha</td>
    			    	<td class="lockIcon">haha</td>
    			    	</tr>
    			    </tbody>
    			</table>
    		</div>
    	</div>
    </div>
<!-- 以下为弹出框内容 -->
  <div class="am-modal am-modal-prompt" tabindex="-1" id="newFilePrompt">
    <div class="am-modal-dialog">
      <div class="am-modal-hd">新建文件</div>
      <div class="am-modal-bd">
        请输入要建立的文件名
        <input type="text" class="am-modal-prompt-input" value=".txt">
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      </div>
    </div>
  </div>
   <div class="am-modal am-modal-prompt" tabindex="-1" id="newFolderPrompt">
    <div class="am-modal-dialog">
      <div class="am-modal-hd">新建文件夹</div>
      <div class="am-modal-bd">
        请输入要建立的文件夹名
        <input type="text" class="am-modal-prompt-input" >
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      </div>
    </div>
  </div>
  <div class="am-popup" id="editPopUp">
    <div class="am-popup-inner">
      <div class="am-popup-hd">
        <h4 class="am-popup-title">读取失败.txt</h4>
        <span data-am-modal-close
              class="am-close">&times;</span>
      </div>
      <div class="am-popup-bd editAreaPlace">
        <form  method="POST" role="form" class="am-form">
          	  <div class="am-form-group">
                <textarea class="am-scrollable-vertical editArea"  id="doc-ta-1">读取失败</textarea>
              </div>
 			<div class="am-g am-cf">
 			<div class="am-btn am-btn-primary am-fr" id="editSaveBtn">保存</div>
            <div class="am-btn am-btn-primary am-fr " id="editCloseBtn">关闭</div>	
 			</div>        
        </form>
      </div>
    </div>
  </div>
	<div class="am-popup" id="strogePopUp">
    <div class="am-popup-inner">
      <div class="am-popup-hd">
        <h4 class="am-popup-title"><i class="am-icon-desktop"></i>硬盘使用状况</h4>
        <span data-am-modal-close
              class="am-close">&times;</span>
      </div>
      <div class="am-popup-bd storgePlace">
      	<div class="am-g" id="storgeList">
        </div>
        <div class="am-panel">
             <span class="strogeSum">共计分配空间：<span class="area">未读取</span> 使用率：<span class="percentage">未读取</span>%</span>
        </div>      
      </div>
       

      </div>
    </div>
  </div>
  <div class="am-modal am-modal-confirm" tabindex="-1" id="delConfirm">
    <div class="am-modal-dialog">
      <div class="am-modal-hd">警告</div>
      <div class="am-modal-bd">
        删除文件夹将导致删除文件夹下的所有内容！要删除吗？
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>我再想想</span>
        <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      </div>
    </div>
  </div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="alertEmpty">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">错误</div>
	    <div class="am-modal-bd">
	      禁止文件名或文件夹名为空，创建失败！
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
  <div class="am-modal am-modal-alert" tabindex="-1" id="alertBan">
    <div class="am-modal-dialog">
      <div class="am-modal-hd">错误</div>
      <div class="am-modal-bd">
        权限不足！
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn">确定</span>
      </div>
    </div>
  </div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="alertRepeat">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">错误</div>
	    <div class="am-modal-bd">
	      目录下已经存在同名文件或文件夹，创建失败！
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="alertMultiedit">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">错误</div>
	    <div class="am-modal-bd">
	      	禁止同时编辑多个文件！
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="alertEditFolder">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">错误</div>
	    <div class="am-modal-bd">
	      禁止编辑文件夹！
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
<script src="lib/jquery.min.js"></script>
<script src="lib/amazeui/js/amazeui.min.js"></script>

<!-- 自己的js -->
<script src="js/js.js"></script>


</body>
</html>