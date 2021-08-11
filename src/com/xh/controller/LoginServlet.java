package com.xh.controller;

import com.alibaba.fastjson.JSONObject;
import com.xh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 贾大大
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1. 修改编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");

        //2. 接受前端过来的三个参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        //3.第一次登陆
        HttpSession session = req.getSession();
        String codeFromSession= (String)session.getAttribute("code");
        if (!codeFromSession.equals(code)){
            PrintWriter writer = resp.getWriter();
            Map map = new HashMap();
            map.put("code",400);
            map.put("msg","密码不正确");
            //把map 变为json
            String jsonString = JSONObject.toJSONString(map);
            writer.println(jsonString);
            writer.close();

        }else {
            System.out.println("验证码正确，该判断账号密码了");

            UserService service = new UserService();
            Map map = service.login(username,password,req);
            String jsonString = JSONObject.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.println(jsonString);
            writer.close();
        }

    }
}
