package com.scut.rsyncWeb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scut.rsyncWeb.entity.Configure;

/**
 * 配置信息操作类
 * @author zhilong Huang
 *
 */
public class ConfigureDAO {
	
	private Session session = null;//创建session对象
	/**
	 * 初始化
	 */
	public ConfigureDAO(){
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
	 * 查看配置信息
	 * @param id
	 * @return
	 */
	public Configure getConfigure(int id){
		try{
			Query q1 = session.createQuery(" from Configure as u where u.userid = ? ");
	        q1.setParameter(0, id);
	        System.out.println(q1.list());
	        List<Configure> list1 = q1.list();
	        return list1.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 添加配置信息
	 * @param configure
	 * @return
	 */
	public boolean addConfigure(Configure configure){
		if(configure.equals("")){
			return false;
		}
		try{
			Transaction tran = this.session.beginTransaction();//开始事务
			this.session.save(configure);//执行  
			tran.commit();//提交
		}catch(Exception ev){
			return false;
		}
		return true;
	}
	/**
	 * 更新数据
	 * @param configure
	 * @return
	 */
	public boolean updateConfigure(Configure configure){
		try{
			Transaction tx = this.session.beginTransaction();
	    	this.session.merge(configure);
//	    	this.session.merge(configure);
//	    	this.session.getTransaction().commit();
	    	tx.commit();
	    	return true;
		}catch(Exception ev){
			ev.printStackTrace();
		}
		return false;
		
	}

}
