package com.scut.rsyncWeb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.User;
import com.scut.rsyncWeb.util.DBOoperater;

//用户登录验证类
public class UserDAO {

	public boolean isExitUser(User user){
		//用户session
		Map sess= ActionContext.getContext().getSession();
		Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
		//查询是否存在用户
		Query q = session.createQuery(" from User as u where u.username = ? and u.password = ?");
	    q.setParameter(0, user.getUsername());
	    q.setParameter(1,user.getPassword());
	    List<User> list = q.list();
	    
	    sess.put("userid", list.get(0).getId());
	    
	    Query q1 = session.createQuery(" from Configure as u where u.userid = ? ");
        q1.setParameter(0, list.get(0).getId());
        List<Configure> list1 = q1.list();
        Configure config1 = list1.get(0);
      //初始化用户session的值;
        sess.put("ip", config1.getIp());
        sess.put("port", config1.getPort());
        sess.put("catalog",config1.getCatalog());
        sess.put("ftp_password", config1.getFtppassword());
        sess.put("ftp_username", config1.getFtpusername());
        sess.put("path", config1.getCatalog());
        sess.put("isShowCatalog", "none");
        session.close();
	    
	    //用户长度为1时，表示用户存在
	    if(list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
}
