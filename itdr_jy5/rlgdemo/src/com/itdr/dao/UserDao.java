package com.itdr.dao;

import com.itdr.pojo.User;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public List<User> selectAll(String pageNum, String pageSize) {
        //创建连接池
       // ComboPooledDataSource co=PoolUtil.getCo();//调用工具类的方法
        //连接数据库
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="select * from user ";

        List<User>li =null;

        try {
            li = qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }

    public User selectOne(String name, String password) {
        //创建连接池
        // ComboPooledDataSource co=PoolUtil.getCo();//调用工具类的方法
        //连接数据库
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="select * from user where name=? and password=?";//不懂

        User u =null;

        try {
            u= qr.query(sql, new BeanHandler<User>(User.class),name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         return u;
    }




}
