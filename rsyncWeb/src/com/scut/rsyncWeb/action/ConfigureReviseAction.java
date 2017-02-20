package com.scut.rsyncWeb.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.configureReviseDAO;
import com.scut.rsyncWeb.entity.Configure;

public class ConfigureReviseAction {
	private int userid;
	private String ip;
	private String port;
	private String catalog;
	private String ftpusername;
	private String ftppassword;
	
	public String execute() throws Exception {
		configureReviseDAO configdao = new configureReviseDAO();
		Map session = ActionContext.getContext().getSession();
		int userid1 = (Integer) session.get("userid");
		Configure config = new Configure();
		config.setUserid(userid1);
		config.setIp(ip);
		config.setPort(port);
		config.setCatalog(catalog);
		config.setFtpusername(ftpusername);
		config.setFtppassword(ftppassword);
		boolean isReviseConfigure = configdao.isReviseConfigure(config);
		if(isReviseConfigure){
			return "success";
		}else{
			return "false";
		}
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
