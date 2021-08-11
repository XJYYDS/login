package com.xh.service;

import com.xh.dao.UserDao;
import com.xh.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    //登录
    public Map login(String username, String password, HttpServletRequest request){
        Map map = new HashMap();
        UserDao dao = new UserDao();
        User userFromDB = dao.login(username,password);
        if (null == userFromDB){
            map.put("code",400);
            map.put("msg","账户名或者密码不正确");
            return map;
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",userFromDB);
            map.put("code",0);
            map.put("msg","登陆成功");
            return map;
        }

    }
}
