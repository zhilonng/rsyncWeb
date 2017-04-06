package com.scut.rsyncWeb.test;

import com.scut.rsyncWeb.DAO.controlFileInfoDAO;
import com.scut.rsyncWeb.entity.FileInfo;

public class FileInFoText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		controlFileInfoDAO controller = new controlFileInfoDAO();
		FileInfo fileinfo = new FileInfo();
		fileinfo.setId(50);
		fileinfo.setFileName("binn");
		fileinfo.setIp("119.110.110.110");
		fileinfo.setType("文件夹");
		fileinfo.setFileOwner("zhilong");
		fileinfo.setTime(String.valueOf(System.currentTimeMillis()));
		fileinfo.setState("0");
		fileinfo.setClientCatalog("/bin");
		fileinfo.setServerCatalog("/bin");
		controller.insertData(fileinfo);
	}

}
