package com.xh.dao;


import com.xh.entity.User;
import com.xh.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //增删改查
    //查询 selece * from t_user;


    //步骤1 创建出 链接对象

    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        Connection connection = DBHelper.getConnection();
        //步骤2 创建出 sql 语句
        String sql = "select * from t_user";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //步骤3 使用链接对象 获取 预编译对象
            ps = connection.prepareStatement(sql);
            System.out.println("ps = " + ps);
            //步骤4 执行 预编译对象，得出结果集
            rs = ps.executeQuery();
            //步骤5 遍历结果集， 一一获取对象
            while (rs.next()) {
                /*System.out.println("username = " + rs.getString("username"));*/
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public int addUser(User user) {
        Connection connection = DBHelper.getConnection();
        String sql = "insert into t_user values (null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getReal_name());
            ps.setString(4, user.getImg());
            ps.setInt(5, user.getType());
            ps.setInt(6, user.getIs_del());
            ps.setString(7, user.getCreate_time());
            ps.setString(8, user.getModify_time());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    //修改
    public int update(User user) {
        //1、步骤1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql = "update t_user set username=?,password=?,real_name=?,img=?,type=?,is_del=?,create_time=?,modify_time=? where id=?";
        PreparedStatement ps = null;
        int i = 0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getReal_name());
            ps.setString(4, user.getImg());
            ps.setInt(5, user.getType());
            ps.setInt(6, user.getIs_del());
            ps.setString(7, user.getCreate_time());
            ps.setString(8, user.getModify_time());
            ps.setInt(9, user.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;

    }


    //删除
    public int delete(int id) {
        //1、步骤1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql = "delete from  t_user where id=?";

        PreparedStatement ps = null;
        int i = 0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public User login (String username,String password){
        User user = null;
        //1.创建链接
        Connection connection = DBHelper.getConnection();
        //2.sql
        String sql = "select * from t_user where username=? and password=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            //4.
            rs=ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public static void main(String[] args) {
        UserDao dao = new UserDao();

        //查询
        /*List<User> users = dao.selectAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }*/

        //新增
        /*User user = new User();
        user.setUsername("小红帽");
        user.setType(1);
        user.setReal_name("小红帽");
        user.setPassword("123");
        user.setModify_time("2021-01-03");
        user.setIs_del(1);
        user.setImg("xxxx");
        user.setCreate_time("2021-01-03");
        int i = dao.addUser(user);
        System.out.println("i = " + i);*/

        //修改
        /*User user = new User();
        user.setId(12);
        user.setUsername("小红帽");
        user.setType(1);
        user.setReal_name("小红帽");
        user.setPassword("1234567");
        user.setModify_time("2021-08-09 16:33:27");
        user.setIs_del(1);
        user.setImg("xxxx");
        user.setCreate_time("2020-01-03");
        int i=dao.update(user);
        System.out.println("i = " + i);*/

        //删除
//        int i = dao.delete(1);
//        System.out.println("i = " + i);
    }
}
