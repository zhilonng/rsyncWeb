package com.scut.rsyncWeb.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.entity.FileInfo;
import com.scut.rsyncWeb.util.FtpClientUtil;
//选择目录
public class chooseCatalogAction {
	private String filename;
	public chooseCatalogAction(){}
	public String execute() throws Exception {
		//用户session
		Map sess= ActionContext.getContext().getSession();
		//初始化数据
		String ip = (String) sess.get("ip");
		int port = 22;
		String ftpusername = (String) sess.get("ftp_username");
		String ftppassword = (String) sess.get("ftp_password");
		String catalog = (String) sess.get("catalog");
		//连接服务器
		FtpClientUtil ftpClient = new FtpClientUtil(ip,port,ftpusername,ftppassword);
		boolean isConnect = ftpClient.open();
		System.out.println(isConnect);
		//获取文件目录
		FTPFile[] files;
		String s = "";
		String path = (String) sess.get("path");
		if(path == null){path = "";}
		if(filename !=null){
		if(path !="/"&&filename!=""){
		path += "/";
		}
		path +=filename;
		}else{
			path = "/";
		}
		System.out.println("path="+path);
		if(catalog == ""){catalog = "/";}
		try{
		files = ftpClient.getServerName("/"+path);
		}catch(Exception ex){
			return "false";
		}
		List<FileInfo> list = new ArrayList();
		if(files.length>=10){
		for(int i=0;i<10;i++){
				s+="<form action='turnInToFile' method='post'>"
						+"<tr>"
						+ "<td>"
						+ "<input type='checkbox'>"
						+ "</td>"
						+ "<td class='fileName'>"
						+ "<i class='am-icon-folder colorFolder'></i>"
						+"<a onclick=\"turnInToFile('"+files[i].getName()+"',this)\">"
						+files[i].getName()
						+"</a>"
						+"</td>"
						+ "<td class='fileOwner'>+"+files[i].getUser()
						+"</td>"
						+ "<td class='fileType'></td>"
						+ "<td class='fileCreateTime'></td>"
						+ "<td class='lockIcon'></td>"
						+ "</tr>"
						+"</form>";
		}
		}else{
			for(int i=0;i<files.length;i++){
				s+="<form action='turnInToFile' method='post'>"
						+"<tr>"
						+ "<td>"
						+ "<input type='checkbox'>"
						+ "</td>"
						+ "<td class='fileName'>"
						+ "<i class='am-icon-folder colorFolder'></i>"
						+"<a onclick=\"turnInToFile('"+files[i].getName()+"',this)\">"
						+files[i].getName()
						+"</a>"
						+"</td>"
						+ "<td class='fileOwner'>+"+files[i].getUser()
						+"</td>"
						+ "<td class='fileType'></td>"
						+ "<td class='fileCreateTime'></td>"
						+ "<td class='lockIcon'></td>"
						+ "</tr>"
						+"</form>";
		}
		}
			sess.remove("path");
			sess.put("path", path);
			sess.remove("isShowCatalog");
			sess.put("isShowCatalog", "block");
		if(sess.get("cataloglist") == null){
			sess.put("cataloglist", s);
			sess.put("files", files);
			sess.put("page",0);
			
		}else{
			sess.remove("cataloglist");
			sess.put("cataloglist", s);
			sess.remove("files");
			sess.put("files", files);
			sess.remove("page");
			sess.put("page", 1);
		}
		return "success";
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
