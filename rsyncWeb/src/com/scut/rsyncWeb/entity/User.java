package com.scut.rsyncWeb.entity;

public class User {
		
	   	private int id;//用户id
		private String username;//用户名
		private String password;//用户密码
		//共有构造函数
		public User(){}
		//getter and setter
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public String getUsername() {
		return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String passwoStringrd) {
			this.password = passwoStringrd;
		}
}
