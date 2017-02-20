package com.scut.rsyncWeb.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scut.rsyncWeb.entity.User;

public class hibernateTest {

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
      //查询
      Query q = session.createQuery(" from User as u where u.username = ?");
        q.setParameter(0, "zhilonng");
        List<User> list = q.list();
        for (User e : list) {
            System.out.println(e.getUsername() + ", password: " + e.getPassword());
            //System.out.println( e.getPassword() );
        }
        // 新建对象，并赋值
        User user = new User();
       user.setUsername("admin");
        user.setPassword("admin");

        // 保存对象
        session.save(user);

        // 提交事务
        session.getTransaction().commit();

        // 关闭 session 和 sessionFactory
        session.close();
        sessionFactory.close();
    }
}
