package com.scut.rsyncWeb.util;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOoperater {
	
	// 定义连接数据库需要的一些静态常量，并且这些常量一般不可被外界更改，所以定义为final型
	private final static String driver = "org.gjt.mm.mysql.Driver";

	// 定义需要连接的数据库
	private final static String url = "jdbc:mysql://localhost:3306/db_rsyncWeb";

	// 定义数据库用户名和密码
	private final static String sqName = "root";
	private final static String sqPassword = "root";
	static{
	try {
	Class.forName(driver);
	} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	
	// 连接数据库
	public static Connection getConnection(){
	Connection conn = null;
	try {
	conn = (Connection)DriverManager.getConnection(url, sqName, sqPassword);
	} catch (SQLException e) {
	// TODO Auto-generated catch block
		System.out.println("数据库连接失败！");  
	e.printStackTrace();
	}
	return conn;
	}

	// 定义两个参数的关闭方法，因为对数据库的一切操作，最后都必须关闭
	public static void close(Connection conn, PreparedStatement pst) {
	// TODO Auto-generated method stub
	if(conn != null){
	try {
	conn.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	if(pst != null){
	try {
	pst.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
	
	// 覆写关闭方法，定义3个参数的关闭方法
	public static void close(Connection conn, Statement st, ResultSet rs) {
	// TODO Auto-generated method stub

	// 调用双参关闭方法
	close(conn,null);
	if(st != null){
	try {
	st.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	if(rs != null){
	try {
	rs.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}

}
