package com.github.excellent.controller;

import com.github.excellent.entity.User;
import com.github.excellent.service.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 处理登录的Servlet
 * @auther plg
 * @date 2019/8/3 11:07
 */
@WebServlet("/login")
public class LServlet extends HttpServlet {
    private Login login = new Login();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("Text/html;charset=UTF8");
        PrintWriter writer = resp.getWriter();
        User user = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setUserName(username);
        user.setPassword(password);
        try {
            if(login.login(user)){
                writer.println("<script>" +
                        "alert(\"登录成功\")\n" +
                        "</script>");
            }else{
                writer.println("<script>\n" +
                        "    alert(\"登录失败\")\n" +
                        "    window.location.href(\"/index.html\")\n" +
                        "</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
