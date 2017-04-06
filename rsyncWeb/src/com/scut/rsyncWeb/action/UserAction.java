package com.scut.rsyncWeb.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.ConfigureDAO;
import com.scut.rsyncWeb.DAO.UserDAO;
import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.User;

public class UserAction {

	private String name;
	private String password;

	 public String execute() throws Exception {
		 
		//用户session
		Map sess= ActionContext.getContext().getSession();
	    UserDAO userdao = new UserDAO();
	    ConfigureDAO config = new ConfigureDAO();
	    User user = new User();
	    try{
	    user.setUsername(name);
	    user.setPassword(password);
	    }catch(Exception e){
	       e.printStackTrace();
	    }
	    //System.out.println(user.getUsername());
	    if(user.getUsername().equals("")||user.getUsername() == null){return "false";}
	    else{
	      if(user.getPassword().equals("")||user.getPassword() == null){return "false";}
	      else{
	        List<User> list = userdao.queryUser(user);
	        if(!list.isEmpty()){
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
