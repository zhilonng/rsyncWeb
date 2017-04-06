package com.scut.rsyncWeb.entity;

public class FileInfo {

	private int id; //用户id
	private String ip;//备份目标服务器ip
	private String fileName; //文件名
	private String fileOwner; //文件拥有着
	private String type; //文件类型
	private String time; //创建时间
	private String state; //文件状态 0：已上传 1：正在上传 2：已暂停
	private String clientCatalog;//客户端目录
	private String serverCatalog;//目标服务器目录
	
	
	public FileInfo(){}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOwner() {
		return fileOwner;
	}
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClientCatalog() {
		return clientCatalog;
	}

	public void setClientCatalog(String clientCatalog) {
		this.clientCatalog = clientCatalog;
	}

	public String getServerCatalog() {
		return serverCatalog;
	}

	public void setServerCatalog(String serverCatalog) {
		this.serverCatalog = serverCatalog;
	}
}
