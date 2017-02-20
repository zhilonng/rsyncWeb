package com.scut.rsyncWeb.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpClientUtil {

	String server;//服务器地址
	int port;//端口号
	String userName;//ftp服务器用户
	String userPassword;//ftp服务器密码
	FTPClient ftpClient;
	
	public FtpClientUtil(String server,int port,String userName,String userPassword)
	 {
	  this.server=server;
	  this.port=port;
	  this.userName=userName;
	  this.userPassword=userPassword;
	 }
	 /**
	  * 链接到服务器
	  * @return
	  */
	 public boolean open()
	 {

	  if(ftpClient!=null&&ftpClient.isConnected())
	   return true;
	  try
	  {
	      ftpClient= new FTPClient();
	      ftpClient.connect(server);
	      ftpClient.login(userName, userPassword);
	      ftpClient.enterLocalPassiveMode();
	      return true;
	  }
	  catch(Exception e)
	  {
	   e.printStackTrace();
	   ftpClient=null;
	   return false;
	  }
	 }
	 public FTPFile[] getServerName(String ftpDirectory) throws IOException{
		 return ftpClient.listFiles(ftpDirectory);
	 }
	/**
	  * 返回FTP目录下的文件列表
	  * @param ftpDirectory
	  * @return
	  */
//	  public List<String> getFileNameList(String ftpDirectory) 
//	  { 
//	     List<String> list = new ArrayList<String>(); 
//	     if(!open())
//	   return list;
//	     try  
//	     { 
//	        DataInputStream dis = new  DataInputStream((InputStream)ftpClient.listFiles(ftpDirectory)); 
//	        String filename = ""; 
//	        while((filename=dis.readLine())!=null)   
//	        {
//	          list.add(filename);         
//	        }   
//	     } catch (Exception e)  
//	     { 
//	        e.printStackTrace(); 
//	     } 
//	     return list; 
//	  }
}
