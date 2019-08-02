package com.itdr.testdemo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoTest {

@Test
   public void test1() throws Exception {
   //创建c3p0连接池
   ComboPooledDataSource co =new ComboPooledDataSource();
//连接数据库
   Connection connection= co.getConnection();
//sql语句
   String sql= "select * from user";
//执行sql语句
   PreparedStatement ps = connection.prepareStatement(sql);
   //查询sql
   ResultSet res = ps.executeQuery();

while (res.next()){
   System.out.println(res.getString(2));
  }
}





}
