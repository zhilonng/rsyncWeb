package com.scut.rsyncWeb.test;
import java.sql.Connection;

import com.scut.rsyncWeb.util.DBOoperater;

public class ConnectTest {
public static void main(String[] args){
Connection conn = null;
conn = DBOoperater.getConnection();
if(conn != null){
System.out.println("连接成功");
}else{
System.out.println("连接失败");
}
}
}