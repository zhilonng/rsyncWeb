package com.scut.rsyncWeb.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.controlFileInfoDAO;
import com.scut.rsyncWeb.entity.FileInfo;
import com.scut.rsyncWeb.util.SingletonFileOperater;

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
	private int id; //fileinfo表id
	Map sess= ActionContext.getContext().getSession();//用户session
	controlFileInfoDAO fileInfoController = new controlFileInfoDAO();;
	/**
	 * 构造器
	 */
	public FileBackUpControlAction(){
	}
	/**
	 * 开始备份
	 * @return
	 * @throws Exception
	 */
	public void execute() throws Exception {	 
	
	SingletonFileOperater a = null;//创建单例对象
	SingletonFileOperater b =a.getOperater();//获取单例对象
	System.out.println(this.filename);
	b.syncDirectory(this.filename);//开始同步
	FileInfo fileinfo = new FileInfo();//创建fileinfo，将备份数据备份至数据库
	/**
	 * 初始化数据
	 */
	fileinfo.setUserid((Integer) sess.get("userid"));
	fileinfo.setFileName("binn");
	fileinfo.setIp("119.110.110.110");
	fileinfo.setType("文件夹");
	fileinfo.setFileOwner("zhilong");
	fileinfo.setTime(String.valueOf(System.currentTimeMillis()));
	fileinfo.setState("1");
	fileinfo.setClientCatalog("/bin");
	fileinfo.setServerCatalog("/bin");
	fileInfoController.insertData(fileinfo);//插入数据库
//	if(!fileInfoController.isExitFile(fileinfo)){
//		fileInfoController.insertData(fileinfo);//插入数据库
//	}else{
//		sess.remove("file_exit");
//		sess.put("file_exit", true);
//		fileInfoController.close();
////	    return "false";	
//	}
	sess.remove("file_exit");
	sess.put("file_exit",false);
	fileInfoController.close();
	System.out.println("null laa!");
 	HttpServletResponse response=ServletActionContext.getResponse();   
    response.setContentType("text/html;charset=utf-8");  
    //response.setCharacterEncoding("UTF-8");  
    PrintWriter out = response.getWriter();  
    //JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试  
    String jsonString="{\"user\":{\"id\":\"123\",\"name\":\"张三\",\"say\":\"Hello , i am a action to print a json!\",\"password\":\"JSON\"},\"success\":true}";  
    out.println(jsonString);  
    out.flush();  
    out.close();  
//	return "success";
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
	/**
	 * 获得备份目录数据
	 * 0为备份完成
	 * 1为正在备份
	 * 2为垃圾箱
	 * @return
	 */
	public void getBackUpListData() throws Exception{
		/**
         * 获得fileinfo列表信息
         */
        controlFileInfoDAO fileinfo_config = new controlFileInfoDAO();
        List<FileInfo> file_info_list_complete = new ArrayList<FileInfo>();
        List<FileInfo> file_info_list_ing = new ArrayList<FileInfo>();
        List<FileInfo> file_info_list_delete = new ArrayList<FileInfo>();
        List<FileInfo> file_info_list = fileinfo_config.queryData((Integer) sess.get("userid"));
        for(int i=0;i<file_info_list.size();i++){
        	if(file_info_list.get(i).getState().equals("0")){
        		file_info_list_complete.add(file_info_list.get(i));
        	}
        	if(file_info_list.get(i).getState().equals("1")){
        		file_info_list_ing.add(file_info_list.get(i));
        	}
        	if(file_info_list.get(i).getState().equals("3")){
        		file_info_list_delete.add(file_info_list.get(i));
        	}
        }
        fileinfo_config.close();//关闭session
        /**
         * 利用用户session将数据传输至前端
         */
        sess.remove("list_backup_ing_files");
    	sess.put("list_backup_ing_files", file_info_list_ing);
    	sess.remove("list_backup_complete_files");
    	sess.put("list_backup_complete_files", file_info_list_complete);
    	sess.remove("list_backup_delete_files");
    	sess.put("list_backup_delete_files", file_info_list_delete);
    	System.out.println("查询成功");
	}
	/**
	 * 暂停->开始备份
	 * @return
	 */
	public void reStartBackUp() throws Exception{
		controlFileInfoDAO fileinfo_config = new controlFileInfoDAO();
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
