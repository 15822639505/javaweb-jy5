package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.User;
import com.itdr.service.UserService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UserController extends HttpServlet {
   private UserService uc = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理编码集
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//如何获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

//判断请求
        ResponseCode rs = null ;
        switch (path) {
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = login(request);
                break;
            case"disableuser":
                rs = disableuser(request);
                break;
        }

        response.getWriter().write(rs.toString());

        //获取前端的参数
       /* String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
*/
        //创建数据层的对象


        /*rs = uc.selsctAll(pageSize, pageNum);*/
//返回响应的数据


    }

    //获取所有用户列表的请求
    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            rs.setStatus(3);
            rs.setMag("请登录此操作");
            return rs;
        }
        if (user.getType()!=1) {
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            return rs;
        }


        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");


               rs= uc.selsctAll(pageSize, pageNum);
               return rs;
    }


    //用户登录的请求
    //用户登录成功把session数据存起来 ，要在控制层进行代码操作
    private ResponseCode login(HttpServletRequest request) {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用方法
        ResponseCode rs = uc.selectOne(username, password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user", rs.getData());
        return rs;
        //return uc.selectOne(username, password);
    }

    //禁用用户
    private ResponseCode disableuser(HttpServletRequest request) {
        //获取参数
        String uid = request.getParameter("uid");

        //调用方法
        ResponseCode rs = uc.selectOne(uid);

        return rs;
        //return uc.selectOne(username, password);
    }
}