package com.scut.rsyncWeb.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.DAO.userRegDAO;
import com.scut.rsyncWeb.entity.User;

public class UserRegAction {
	private String name;
	private String password;
	 public String execute() throws Exception {
	        userRegDAO userregdao = new userRegDAO();
	        User user = new User();
	        Map session = ActionContext.getContext().getSession();
	        session.put("name", name);
	        session.put("newUser","<script type='text/javascript'>alert('已为您完成注册！')</script>");
	        
	        try{
	        user.setUsername(name);
	        user.setPassword(password);
	        }catch(Exception e){
	        	System.out.println(name);
		        System.out.println(password);
	        }
	        System.out.println(user.getUsername());
	        if(user.getUsername().equals("")||user.getUsername() == null){return "false";}
	        else{
	        	if(user.getPassword().equals("")||user.getPassword() == null){return "false";}
	        	else{
	        		boolean isAdd = userregdao.addNewUser(user);
	        		if(isAdd){return "success";}
	        		else{return "false";}
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
