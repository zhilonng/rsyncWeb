package com.scut.rsyncWeb.action;

import java.util.ArrayList;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.controlFileInfoDAO;
import com.scut.rsyncWeb.entity.FileInfo;
import com.scut.rsyncWeb.util.SingletonFileOperater;

import antlr.collections.List;

/**
 * 文件备份Controller
 * 上传
 * 暂停
 * 恢复
 * 删除
 * 时间：2017/3/27
 * @author zhilong Huang
 *
 */
public class FileBackUpControlAction {
	
	private String filename;//文件名
	Map sess= ActionContext.getContext().getSession();//用户session
	controlFileInfoDAO fileInfoController = new controlFileInfoDAO();;
	/**
	 * 构造器
	 */
	public FileBackUpControlAction(){
	}
	public String execute() throws Exception {	 
	ArrayList<FileInfo> list = new ArrayList<FileInfo>(); 
	if(sess.get("list_backup_files") != null){
		list = (ArrayList<FileInfo>) sess.get("list_backup_files");
	}
	SingletonFileOperater a = null;//创建单例对象
	SingletonFileOperater b =a.getOperater();//获取单例对象
	b.syncDirectory("qaq");//开始同步
	FileInfo fileinfo = new FileInfo();//创建fileinfo，将备份数据备份至数据库
	/**
	 * 初始化数据
	 */
	fileinfo.setId((Integer) sess.get("userid"));
	fileinfo.setFileName("binn");
	fileinfo.setIp("119.110.110.110");
	fileinfo.setType("文件夹");
	fileinfo.setFileOwner("zhilong");
	fileinfo.setTime(String.valueOf(System.currentTimeMillis()));
	fileinfo.setState("0");
	fileinfo.setClientCatalog("/bin");
	fileinfo.setServerCatalog("/bin");
	if(!fileInfoController.isExitFile(fileinfo)){
		fileInfoController.insertData(fileinfo);//插入数据库
	}else{
		sess.remove("file_exit");
		sess.put("file_exit", true);
	    return "false";	
	}
	list.add(fileinfo);
	sess.remove("list_backup_files");
	sess.put("list_backup_files", list);
	sess.remove("file_exit");
	sess.put("file_exit",false);
	return "success";
	 }
	/**
	 * 停止备份
	 * @return
	 * @throws Exception
	 */
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
