package com.scut.rsyncWeb.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.ConfigureDAO;
import com.scut.rsyncWeb.DAO.UserDAO;
import com.scut.rsyncWeb.DAO.controlFileInfoDAO;
import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.FileInfo;
import com.scut.rsyncWeb.entity.User;

public class UserAction {

	private String name;
	private String password;

	 public String execute() throws Exception {
		
		Map sess= ActionContext.getContext().getSession();//用户session
	    UserDAO userdao = new UserDAO();//用户DAO
	    ConfigureDAO config = new ConfigureDAO();//ConfigureDAO
	    User user = new User();//user对象
	    /**
	     * 添加用户账号密码
	     */
	    try{
	    user.setUsername(name);
	    user.setPassword(password);
	    }catch(Exception e){
	       e.printStackTrace();
	    }
	    //System.out.println(user.getUsername());
	    /**
	     * 判断账号密码是否有意义
	     * 查看是否存在该账号
	     * 验证登陆
	     * 初始化配置参数信息
	     * 初始化fileinfo列表信息
	     */
	    if(user.getUsername().equals("")||user.getUsername() == null){return "false";}
	    else{
	      if(user.getPassword().equals("")||user.getPassword() == null){return "false";}
	      else{
	        List<User> list = userdao.queryUser(user);
	        /**
	         * 用户存在
	         * 进一步获得配置参数信息与fileinfo信息
	         */
	        if(!list.isEmpty()){
	        	/**
	        	 * 获得配置参数信息
	        	 */
	        	sess.put("userid", list.get(0).getId());
	        	Configure configure = config.getConfigure(list.get(0).getId());
	        	//初始化用户session的值;
	            sess.put("ip", configure.getIp());
	            sess.put("port", configure.getPort());
	            sess.put("catalog",configure.getCatalog());
	            sess.put("ftp_password", configure.getFtppassword());
	            sess.put("ftp_username", configure.getFtpusername());
	            sess.put("path", configure.getCatalog());
	            sess.put("isShowCatalog", "none");
	            config.closeSession();
	            /**
	             * 获得fileinfo列表信息
	             */
	            controlFileInfoDAO fileinfo_config = new controlFileInfoDAO();
	            List<FileInfo> file_info_list_complete = new ArrayList<FileInfo>();
	            List<FileInfo> file_info_list_ing = new ArrayList<FileInfo>();
	            List<FileInfo> file_info_list_delete = new ArrayList<FileInfo>();
	            List<FileInfo> file_info_list = fileinfo_config.queryData(list.get(0).getId());
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
	        	return "success";
	        }else{return "false";}
	        }
	    }
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
