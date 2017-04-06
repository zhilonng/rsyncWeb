package com.scut.rsyncWeb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scut.rsyncWeb.entity.FileInfo;
/**
 * 备份文件操作，插入、删除、提取备份信息
 * @author zhilong Huang
 * */
public class controlFileInfoDAO {
	
	private Session session = null;//创建session对象
	
	/**
	 * 完成数据初始化
	 */
	public controlFileInfoDAO(){
		Configuration config = new Configuration();	
		SessionFactory factory =  config.configure().buildSessionFactory();
		this.session = factory.openSession();
	}
	/**
	 * 插入数据
	 * @param fileinfo
	 * @return
	 */
	public boolean insertData(FileInfo fileinfo){
		if(fileinfo.equals("")){
			return false;
		}
		try{
			Transaction tran = this.session.beginTransaction();//开始事务
			this.session.save(fileinfo);//执行  
			tran.commit();//提交
		}catch(Exception ev){
			return false;
		}
		return true;
	}
	/**
	 * 删除记录
	 * @param fileinfo
	 * @return
	 */
	public boolean deleteData(FileInfo fileinfo){
		try{
			Transaction tran = this.session.beginTransaction(); //开始事务
			this.session.delete(fileinfo);
		}catch(Exception ev){
			return false;
		}
		return false;
	}
	/**
	 * 检索记录
	 * @param id
	 * @return
	 */
	public FileInfo queryData(int id){
		FileInfo fileinfo = null;
		try{
			Transaction tran = this.session.beginTransaction(); //开始事务
			fileinfo = (FileInfo)session.get(FileInfo.class,id);
			session.get(FileInfo.class,id);
			tran.commit(); //提交事务
			return fileinfo;
		}catch(Exception ev){
			ev.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 检测是否已 存在记录
	 * @param fileinfo
	 * @return
	 * @throws Exception
	 */
	public boolean isExitFile(FileInfo fileinfo) throws Exception{
		String hql = "from rsync_backupfiles where backup_filename = ? and backup_server_ip= ? and backup_server_catalog = ?";  
		Query query = session.createQuery(hql);  
		query.setString(0, fileinfo.getFileName());  
		query.setString(1, fileinfo.getIp());
		query.setString(3,fileinfo.getServerCatalog());
		List<FileInfo> list = query.list();
		if(list.size() == 0) return true;
		return false;
	}
}
