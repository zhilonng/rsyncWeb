package com.scut.rsyncWeb.test;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;

import com.scut.rsyncWeb.util.FtpClientUtil;

public class ConnectFtpServerTest {

	public static void main(String[] args) throws IOException{
		FtpClientUtil ftpClient = new FtpClientUtil("119.29.188.78",22,"uftp","huangzhilong...0");
		boolean isConnect = ftpClient.open();
		System.out.println(isConnect);
		FTPFile[] files;
		files = ftpClient.getServerName("/home/server");
		System.out.println(files[0]);
		
	}
}