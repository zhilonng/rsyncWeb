package com.scut.rsyncWeb.test;

import com.scut.rsyncWeb.DAO.controlFileInfoDAO;
import com.scut.rsyncWeb.entity.FileInfo;

public class FileInFoText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		controlFileInfoDAO controller = new controlFileInfoDAO();
		FileInfo fileinfo = new FileInfo();
		controller.reviseStateStart(122);
	}

	

}
