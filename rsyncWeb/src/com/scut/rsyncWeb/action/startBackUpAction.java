package com.scut.rsyncWeb.action;

import java.io.File;

import com.scut.jrsync.JsyncClient;

public class startBackUpAction {
	private String filename;
	public startBackUpAction(){}
	 public String execute() throws Exception {
		 JsyncClient  jrsync = new JsyncClient();
		 System.out.println(filename);
		 File directory;
		 directory = new File("E:\\clientDir\\VC_RED.MSI");
		 System.out.println("start rsync");
		 try {
			jrsync.syncDirectory(directory, "119.29.188.78", 2466, "/home/serverDir");
			System.out.println("ok");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
