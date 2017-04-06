package com.scut.rsyncWeb.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.ConfigureDAO;
import com.scut.rsyncWeb.DAO.UserDAO;
import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.User;
/**
 * 用户注册
 * @author zhilong Huang
 */
public class UserRegAction {
	private String name;
	private String password;
	 public String execute() throws Exception {
			Map sess= ActionContext.getContext().getSession();//用户session
	        UserDAO userregdao = new UserDAO();
	        ConfigureDAO config = new ConfigureDAO();
	        User user = new User();
	        Map session = ActionContext.getContext().getSession();
	        session.put("name", name);
	        session.put("newUser","<script type='text/javascript'>alert('已为您完成注册！')</script>");
	        
	        try{
	        user.setUsername(name);
	        user.setPassword(password);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        if(user.getUsername().equals("")||user.getUsername() == null){return "false";}
	        else{
	        	if(user.getPassword().equals("")||user.getPassword() == null){return "false";}
	        	else{
	        		User newUser = new User();
	        		newUser = userregdao.addUser(user);
	        		userregdao.closeSession();
	        		if(!newUser.equals(null)){
	    		        sess.put("userid", newUser.getId());//初始化用户session的值
	    		        /**
	    		         * 初始化configure
	    		         */
	    		        Configure configure = new Configure();
	    		        configure.setUserid(newUser.getId());
	    		        configure.setIp("");
	    		        configure.setPort("");
	    		        configure.setCatalog("");
	    		        configure.setFtpusername("");
	    		        configure.setFtppassword("");
	    		        
	    		        /**
	    		         * 初始化session
	    		         */
	    		        sess.put("ip", "");
	    		        sess.put("port", "");
	    		        sess.put("path","");
	    		        sess.put("ftp_password", "");
	    		        sess.put("ftp_username", "");
	    		        sess.put("isShowCatalog", "none");
	    		        
	    		        //添加初始化纪录
	    		        if(config.addConfigure(configure)){
	    		        	config.closeSession();
	    		        	return "success";
	    		        }
	    		        else return "false";
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
