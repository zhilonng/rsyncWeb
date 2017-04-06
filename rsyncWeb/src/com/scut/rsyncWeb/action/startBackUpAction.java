package com.scut.rsyncWeb.action;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.jrsync.JsyncClient;
import com.scut.rsyncWeb.util.*;

public class startBackUpAction {
	private String filename;
	Thread thread;
	public startBackUpAction(){}
	 public String execute() throws Exception {
		 
		//用户session
		Map sess= ActionContext.getContext().getSession();
		//初始化数据
//		if(sess.get("backUpFiles"))
//		String backUpFiles = (String) sess.get("backUpFiles");
//	
		SingletonFileOperater a = null;
		SingletonFileOperater b =a.getOperater();
		b.syncDirectory("qaq");
		
		 return "success";
	 }
	
	public String stopbackup() throws Exception{
		SingletonFileOperater a = null;
		SingletonFileOperater b =a.getOperater();
		b.stopSync(0);
		
		return "success";
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
