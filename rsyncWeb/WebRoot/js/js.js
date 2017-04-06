function turnToParamSetUp(){
	$("#iframe-right-page").attr('src','paramSetUp.jsp');
}
function turnToBackUpControl(){
	$("#iframe-right-page").attr('src','backUpControl.jsp')
}
function tunToBackUpList(){
	$("#iframe-right-page").attr('src','backUpList.jsp')
}
function displayConfig(){
	$("#dv_modyConfig").show();
	$("#dv_opration").hide();
	$("#dv_catalog").hide();
	//document.getelementbyid('dv_modyConfig').style.display = "block"; 
	//document.getelementbyid('dv_opration').style.display == "none";
}
function displayOperation(){
	$("#dv_opration").show();
	$("#dv_modyConfig").hide();
	//document.getelementbyid('dv_modyConfig').style.display = "none"; 
	//document.getelementbyid('dv_opration').style.display == "block";
}
function displayTestList(){
	document.getelementbyid('btn_modyConfig').style.display = "none"; 
	document.getelementbyid('btn_opration').style.display == "none";
}
//选择目录
function doChooseCatalog(){
	window.location.href="doChooseCatalog";
}
//进入文件夹
function turnInToFile(path,obj){
	window.location.href="doChooseCatalog?filename="+path;
}
//下一页
function nextPage(){
	window.location.href="nextPage";
}
//选择文件夹
function BrowseFolder() 
{ 
	var Message="请选择文件夹"; 
	var Shell=new ActiveXObject("Shell.Application"); 
	var Folder=Shell.BrowseForFolder(0,Message,0x0040,0x11); 
	if(Folder!=null) 
	{ 
	Folder=Folder.items();
	Folder=Folder.item();
	Folder=Folder.Path;
	if(Folder.charAt(Folder.length-1)!="\\") 
	{ 
	Folder=Folder+"\\"; 
	} 
	$("#choosed-file-name").val(Folder);
	} 
} 
function getPath(obj)    
{    
  if(obj)    
    {    
   
    if (window.navigator.userAgent.indexOf("MSIE")>=1)    
      {    
        obj.select();    
   
      return document.selection.createRange().text;    
      }    
   
    else if(window.navigator.userAgent.indexOf("Firefox")>=1)    
      {    
      if(obj.files)    
        {    
   
        return obj.files.item(0).getAsDataURL();    
        }    
      return obj.value;    
      }    
    return obj.value;    
    }    
}    
//开始备份
function startBackUps(obj){ 	

//	window.open ("page.html", "newwindow", "height=100, width=400, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") //写成一行 
//	$(document).ready(function ()
//	        {
//	            cbMgr.LoadTo("FilePanel");
//	            cbMgr.Init();
//	            cbMgr.SetupCheck(); //安装检查
//
//	        });
	window.location.href="startBackUp";

}
function stopBackUp(){
	window.location.href="stopBackUp";
}