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

/**
 * 用户登录验证类
 * @author zhilong Huang
 *
 */
public class UserDAO {
	private Session session = null;//创建session对象
	/**
	 * 初始化session对象
	 */
	public UserDAO(){
		Configuration config = new Configuration();	
		SessionFactory factory =  config.configure().buildSessionFactory();
		this.session = factory.openSession();
	}
	/**
	 * 关闭session
	 */
	public void closeSession(){
	this.session.close();
	}
	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 */
	public List<User> queryUser(User user){
		
		session.beginTransaction();
		//查询是否存在用户
		Query q = session.createQuery(" from User as u where u.username = ? and u.password = ?");
	    q.setParameter(0, user.getUsername());
	    q.setParameter(1,user.getPassword());
	    List<User> list = q.list();
		return list;
		
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user){
		List<User> list = queryUser(user);
		System.out.print(list.size());
		if(list.size() == 0){
			try{
				// 保存对象
		        session.save(user);
		        // 提交事务
		        session.getTransaction().commit();
				
			}catch(Exception ev){
				return null;
			}
			List<User> list1 = queryUser(user);
			return list1.get(0);
		}
		return user;
		
	}

//	public boolean isExitUser(User user){
//		//用户session
//		Map sess= ActionContext.getContext().getSession();
//		Configuration cfg = new Configuration().configure();
//        SessionFactory sf = cfg.buildSessionFactory();
//        Session session = sf.openSession();
//        session.beginTransaction();
//		//查询是否存在用户
//		Query q = session.createQuery(" from User as u where u.username = ? and u.password = ?");
//	    q.setParameter(0, user.getUsername());
//	    q.setParameter(1,user.getPassword());
//	    List<User> list = q.list();
//	    sess.put("userid", list.get(0).getId());
//	    
//	    Query q1 = session.createQuery(" from Configure as u where u.userid = ? ");
//        q1.setParameter(0, list.get(0).getId());
//        System.out.println(q1.list());
//        List<Configure> list1 = q1.list();
//        Configure config1 = list1.get(0);
//      //初始化用户session的值;
//        sess.put("ip", config1.getIp());
//        sess.put("port", config1.getPort());
//        sess.put("catalog",config1.getCatalog());
//        sess.put("ftp_password", config1.getFtppassword());
//        sess.put("ftp_username", config1.getFtpusername());
//        sess.put("path", config1.getCatalog());
//        sess.put("isShowCatalog", "none");
//        session.close();
//	    
//	    //用户长度为1时，表示用户存在
//	    if(list.size() == 0){
//			return false;
//		}else{
//			return true;
//		}
//	}
}
