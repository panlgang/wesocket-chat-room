package com.github.excellent.controller;
import com.github.excellent.entity.User;
import com.github.excellent.service.Register;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 处理注册的Servlet
 * @auther plg
 * @date 2019/8/3 11:07
 */

@WebServlet("/doRegister")
public class RServlet extends HttpServlet {
    private Register register = new Register();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setUserName(username);
        user.setPassword(password);
        System.out.println(user);
        try {
            if(register.regitser(user)){
                writer.println("<script>alert('恭喜注册成功，请直接登录！');</script>");
               writer.println("<script>" +
                       "window.location.href=\"/index.html\";" +
                       "</script>");


            }else{
                writer.println("<script>alert('注册失败，用户名已存在，请重新尝试！');</script>");
                //req.getRequestDispatcher("/registration.html").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}