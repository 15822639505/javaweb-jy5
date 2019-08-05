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
    //查找所有用户信息
    public List<User> selectAll(String pageNum, String pageSize) {
        //创建连接池
       //ComboPooledDataSource co=PoolUtil.getCo();//调用工具类的方法
        //连接数据库
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="select * from user";

        List<User>li =null;

        try {
            li = qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }
    //根据用户的账号和密码查找用户信息
    public User selsectOne(String username, String password) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="select * from user where name = ? and password = ?";//不懂

        User u =null;

        try {
            u= qr.query(sql, new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;

    }

    //根据id查找一个用户信息
    public User selsectOne(Integer uid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="select * from user where id=?";//不懂

        User u =null;

        try {
            u= qr.query(sql, new BeanHandler<User>(User.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;

    }

    //禁用用户信息
    public int updateByUid(Integer uid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCo());
        //sql语句
        String sql="update  user set stats = 1 where id = ?";//不懂

       int row =0;

        try {
            row= qr.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;

    }


}
