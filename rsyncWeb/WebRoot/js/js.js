
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
//开始备份
function startBackUps(obj){ 	
//	var filesize = 0;
//	filesize = obj.files[0].size;
//	alert(filesize);
//	try{
//	var filepath="D:\\壁纸\\3.jpg";
//	var fso = new ActiveXObject("Scripting.FileSystemObject");
//	var file = fso.GetFile(filepath);
//    }catch(e){
//    alert(e);
//    }
	$(document).ready(function ()
	        {
	            cbMgr.LoadTo("FilePanel");
	            cbMgr.Init();
	            cbMgr.SetupCheck(); //安装检查
	 
	            //取MAC地址
	            //var mac = cbMgr.Browser.GetMacs();
	            //alert(mac[0]);
	 
	            //上传指定目录下的所有文件
	            //cbMgr.Browser.GetFiles("F:\\ftp\\", false);
	        });
	window.location.href="startBackUp?filename="+$("#fileText").val();

	
//	alert($("#fileText")[0].src);
}