package com.scut.rsyncWeb.action;

import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.util.FtpClientUtil;

//下一页
public class nextPageAction {
	private String pageOpType;
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		FTPFile[] files1 = (FTPFile[]) session.get("files");
		if(files1 == null){
			return "false";
		}
		System.out.println("nextPage");
		String ip = (String) session.get("ip");
		int port = 22;
		String ftpusername = (String) session.get("ftp_username");
		String ftppassword = (String) session.get("ftp_password");
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
		return "success";
	}
	public String getPageOpType() {
		return pageOpType;
	}
	public void setPageOpType(String pageOpType) {
		this.pageOpType = pageOpType;
	}
}
