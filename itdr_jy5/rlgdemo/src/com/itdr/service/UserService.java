package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.User;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();

    public ResponseCode selsctAll(String pageSize, String pageNum) {
        if (pageSize == null && pageSize.equals("")) {
            pageSize = "10";

        }
        if (pageNum == null && pageNum.equals("")) {
            pageNum = "1";
        }


        List<User> li = ud.selectAll(pageNum, pageSize);
        //如果返回的集合为空呢，出现空指针了
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs.setStatus(1);
            rs.setMag("账户或密码错误");
            return rs;
        }

        //查找是否有这样一个用户
        User u = ud.selsectOne(username, password);

        //如果用户不存在呢
        if (u == null) {
            rs.setStatus(1);
            rs.setMag("账户或密码错误");
            return rs;
        }
        //如果用户存在呢，判断是否是管理员
        if (u.getType() != 1) {
            rs.setStatus(2);
            rs.setMag("没有操作权限！");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(u);

        return rs;
    }

    //禁用用户
    public ResponseCode selectOne(String uids) {
        ResponseCode rs = new ResponseCode();
        if (uids == null || uids.equals("")) {
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMag(Const.USER_PARAMETER_MSG);
            return rs;
        }
//转型
        Integer uid = null;
        try {
            uid = Integer.parseInt(uids);
        } catch (Exception e) {
            rs.setStatus(106);
            rs.setMag("输入的参数有误");
            return rs;
        }

        //查找是否有这样一个用户
        User u = ud.selsectOne(uid);

        //如果用户不存在呢
        if (u == null) {
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMag(Const.USER_NO_MSG);
            return rs;
        }
        //用户是否已经禁用
        if (u.getStats() == 1) {
            rs.setStatus(Const.USER_DISADLE_CODE);
            rs.setMag(Const.USER_DISADLE_MSG);
            return rs;
        }
        //禁用用户
        int row = ud.updateByUid(uid);
        if (row <= 0) {
            rs.setStatus(107);
            rs.setMag("用户禁用失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMag("禁用用户成功");
        return rs;
    }
}
