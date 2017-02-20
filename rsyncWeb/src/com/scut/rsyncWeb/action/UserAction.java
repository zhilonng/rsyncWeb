package com.scut.rsyncWeb.action;

import com.scut.rsyncWeb.DAO.UserDAO;
import com.scut.rsyncWeb.entity.User;

public class UserAction {

	private String name;
	private String password;

	 public String execute() throws Exception {

	        UserDAO userdao = new UserDAO();
	        User user = new User();
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
	        		boolean isExit = userdao.isExitUser(user);
	        		if(isExit){return "success";}
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
