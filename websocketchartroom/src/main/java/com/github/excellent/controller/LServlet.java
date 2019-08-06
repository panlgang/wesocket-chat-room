package com.github.excellent.controller;

import com.github.excellent.config.FreeMarkerListener;
import com.github.excellent.entity.User;
import com.github.excellent.service.Login;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

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
        // 获取数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        user.setUserName(username);
        user.setPassword(password);
        try {
            if(login.login(user)){
                // 进入聊天页面
                Template template = getInstance(req,"chat.ftl");
                Map<String,String> map = new LinkedHashMap<>();
                // 将map返回给前端
                map.put("username",username);
                template.process(map,writer);

            }else{
                writer.println("<script>\n" +
                        "    alert(\"登录失败，用户名或者密码不正确\")\n" +
                        "    window.location.href(\"/index.html\")\n" +
                        "</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private Template getInstance(HttpServletRequest request,String fileName){
        Configuration configuration =
                (Configuration) request.getServletContext().getAttribute(FreeMarkerListener.TEMPLATE_KEY);
        try {
            return configuration.getTemplate(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found");
        }
        return null;
    }

}
