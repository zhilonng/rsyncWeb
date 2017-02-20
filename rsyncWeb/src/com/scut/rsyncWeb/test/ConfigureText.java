package com.scut.rsyncWeb.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scut.rsyncWeb.entity.Configure;
import com.scut.rsyncWeb.entity.User;

public class ConfigureText {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // 获取 Hibernate 配置信息
        Configuration configuration = new Configuration().configure();

        // 根据 configuration 建立 sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 开启 session（相当于开启 JDBC 的 connection）
        Session session = sessionFactory.openSession();

        // 创建并开启事务对象
        session.beginTransaction();

        // 新建对象，并赋值
        Configure config = new Configure();
        config.setUserid(1);
        config.setIp("123");
        config.setPort("12312");
        config.setCatalog("asdasd");

        // 保存对象
        session.save(config);

        // 提交事务
        session.getTransaction().commit();

        // 关闭 session 和 sessionFactory
        session.close();
        sessionFactory.close();
    }
}
