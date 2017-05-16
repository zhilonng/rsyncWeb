package com.scut.rsyncWeb.action;

import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.util.FtpClientUtil;

//下一页
public class nextPageAction {
	private String pageOpType;
	private int id;
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		FTPFile[] files1 = (FTPFile[]) session.get("files");
		if(files1 == null){
			return "false";
		}
		System.out.println("nextPage");
		String ip;
		String ftpusername;
		String ftppassword;
		int port;
		
		//初始化数据
		if(this.id==1){
		ip = (String) session.get("ip");
		port = 22;
		ftpusername = (String) session.get("ftp_username");
		ftppassword = (String) session.get("ftp_password");
		}else{
			ip = "127.0.0.1";
			port = 1;
			ftpusername = "huangzhilong";
			ftppassword = "huangzhilong...0";
		}
		String catalog = (String) session.get("catalog");
		//连接服务器
		FtpClientUtil ftpClient = new FtpClientUtil(ip,port,ftpusername,ftppassword);
		boolean isConnect = ftpClient.open();
		System.out.println(isConnect);
		//获取文件目录
		FTPFile[] files;
		try{
		files = ftpClient.getServerName("/");
		}catch(Exception ex){
			return "false";
		}
		String s = "";
		int pageNumber = (Integer) session.get("page");
		System.out.println("pageNumberNow="+pageNumber);
		if(pageOpType.equals("lastpage")&&pageNumber <2){
			return "false";
		}
		if(pageOpType.equals("lastpage")&&pageNumber >=2){
			pageNumber = pageNumber - 2;
		}
		System.out.println("pageNumberLast="+pageNumber);
		if(files.length<=pageNumber*10){
			return "false";
		}
		if((files.length-pageNumber*10)>=10){
			for(int i=pageNumber*10;i<10+pageNumber*10;i++){
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
				for(int i=pageNumber*10;i<files.length;i++){
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
			System.out.println(s);
			session.remove("cataloglist");
			session.put("cataloglist", s);
			session.remove("page");
			session.put("page", pageNumber+1);
			
			if(this.id==0){
				return "local";
			}else{
				return "success";
			}
		
	}
	public String getPageOpType() {
		return pageOpType;
	}
	public void setPageOpType(String pageOpType) {
		this.pageOpType = pageOpType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
