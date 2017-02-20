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
/*用户注册类，实现用户注册*/
public class userRegDAO {
	public boolean addNewUser(User user){
		//用户session
		Map sess= ActionContext.getContext().getSession();
		// 获取 Hibernate 配置信息
        Configuration configuration = new Configuration().configure();
        // 根据 configuration 建立 sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // 开启 session（相当于开启 JDBC 的 connection）
        Session session = sessionFactory.openSession();
        // 创建并开启事务对象
        session.beginTransaction();
        //查询是否存在账户
        Query q = session.createQuery(" from User as u where u.username = ? ");
        q.setParameter(0, user.getUsername());
        List<User> list = q.list();
		if(list.size() != 0){
				session.close();
		        sessionFactory.close();
				return false;
			}else{
				//创建Configure对象，并完成初始化
				Configure config = new Configure();
		        // 保存对象
		        session.save(user);
		        // 提交事务
		        session.getTransaction().commit();
		        // 关闭 session 和 sessionFactory
		        //查询用户id
		        Query q1 = session.createQuery(" from User as u where u.username = ? ");
		        q1.setParameter(0, user.getUsername());
		        List<User> list1 = q1.list();
		        User user1 = list1.get(0);
		        session.clear();
		        //写入config
		        Configuration cfg1 = new Configuration().configure();
		        SessionFactory sf1 = cfg1.buildSessionFactory();
		        Session session1 = sf1.openSession();
		        session1.beginTransaction();
		        config.setUserid(user1.getId());
		        config.setIp("");
		        config.setPort("");
		        config.setCatalog("");
		        config.setFtpusername("");
		        config.setFtppassword("");
		        //初始化用户session的值
		        sess.put("userid", user1.getId());
		        sess.put("ip", "");
		        sess.put("port", "");
		        sess.put("catalog","");
		        sess.put("user_password", "");
		        sess.put("ftp_username", "");
		        sess.put("isShowCatalog", "none");
		        session1.save(config);
		        session1.getTransaction().commit();
		        session.close();
		        sessionFactory.close();
				return true;
			}
	}
}
