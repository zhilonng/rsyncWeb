package com.scut.rsyncWeb.DAO;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.opensymphony.xwork2.ActionContext;
import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.User;
//修改用户配置信息
public class configureReviseDAO {

	public boolean isReviseConfigure(Configure config){
		//用户session
		Map sess= ActionContext.getContext().getSession();
		//update
		Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx=null;
        //查询
        Configuration cfg1 = new Configuration().configure();
        SessionFactory sf1 = cfg1.buildSessionFactory();
        Session session1 = sf1.openSession();
        session1.beginTransaction();
        Query q1 = session1.createQuery(" from Configure as u where u.userid = ? ");
        q1.setParameter(0, config.getUserid());
        List<Configure> list1 = q1.list();
        Configure config1 = list1.get(0);
        config.setId(config1.getId());
        session1.close();
        System.out.println("config:");
        System.out.println(config.getFtpusername());
        System.out.println(config.getFtppassword());
        //修改
        try{
        	tx = session.beginTransaction();
        	session.update(config);
        	tx.commit();
        	//初始化用户session的值
        	sess.remove("ip");
	        sess.put("ip", config.getIp());
	        sess.remove("port");
	        sess.put("port", config.getPort());
	        sess.remove("catalog");
	        sess.put("catalog",config.getCatalog());
	        sess.remove("ftp_username");
	        sess.put("ftp_username",config.getFtpusername());
	        sess.remove("ftp_password");
	        sess.put("ftp_password",config.getFtppassword());
	        System.out.println("sess:"+sess.get("ip"));
        	session.close();
        	return true;
        }catch(Exception ex){
        	tx.rollback();
        	session.close();
        	ex.printStackTrace();
        	return false;
        }

	}
}
