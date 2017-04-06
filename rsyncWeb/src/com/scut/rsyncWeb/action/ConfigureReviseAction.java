package com.scut.rsyncWeb.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.ConfigureDAO;
import com.scut.rsyncWeb.entity.Configure;

public class ConfigureReviseAction {
	private int userid;
	private String ip;
	private String port;
	private String catalog;
	private String ftpusername;
	private String ftppassword;
	
	public String execute() throws Exception {
		ConfigureDAO configure = new ConfigureDAO();//Configure操作对象
		Map session = ActionContext.getContext().getSession();//用户session
		int userid1 = (Integer) session.get("userid");
		Configure config = new Configure();
		config.setUserid(userid1);
		config.setIp(ip);
		config.setPort(port);
		config.setCatalog(catalog);
		config.setFtpusername(ftpusername);
		config.setFtppassword(ftppassword);
//		System.out.println(config.getUserid());
//		boolean isReviseConfigure = configdao.isReviseConfigure(config);
//		if(isReviseConfigure){
//			return "success";
//		}else{
//			return "false";
//		}
		Configure config1 = configure.getConfigure(userid1);
		config.setId(config1.getId());
		if( config1!=null){
			if(configure.updateConfigure(config)){
				configure.closeSession();
				//初始化用户session的值
	        	session.remove("ip");
	        	session.put("ip", config.getIp());
	        	session.remove("port");
	        	session.put("port", config.getPort());
		        session.remove("catalog");
		        session.put("path",config.getCatalog());
		        session.remove("ftp_username");
		        session.put("ftp_username",config.getFtpusername());
		        session.remove("ftp_password");
		        session.put("ftp_password",config.getFtppassword());
		        System.out.println("sess:"+session.get("ip"));
//				System.out.println("ftppassword="+session.get(key));
				return "success";
			}else{
				return "false";
			}
		}
		return "false";
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getFtpusername() {
		return ftpusername;
	}

	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}

	public String getFtppassword() {
		return ftppassword;
	}

	public void setFtppassword(String ftppassword) {
		this.ftppassword = ftppassword;
	}
}
