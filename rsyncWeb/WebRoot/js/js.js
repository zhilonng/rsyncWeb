
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
//开始备份
function startBackUps(obj){ 	
	var filesize = 0;
	filesize = obj.files[0].size;
//	alert(filesize);
//	try{
//	var filepath="D:\\壁纸\\3.jpg";
//	var fso = new ActiveXObject("Scripting.FileSystemObject");
//	var file = fso.GetFile(filepath);
//    }catch(e){
//    alert(e);
//    }
	
	alert($("#fileText")[0].src);
}