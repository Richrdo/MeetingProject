package com.controller;

import com.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDate {
    public static Map<Integer, User> userIDMap=new HashMap<>();
    public static Map<String,User> userEmailMap=new HashMap<>();

    static {
        init();
    }

    private static void init(){
        PreparedStatement pst;
        ResultSet rst;
        User user;
        Connection conn=DBUtil.getConnection();
        String sql="select * from user";
        try {
            pst=conn.prepareStatement(sql);
            rst=pst.executeQuery();
            while(rst.next()){
                user=new User();
                user.setEmail(rst.getString("u_email"));
                user.setName(rst.getString("u_name"));
                user.setId(rst.getInt("u_id"));
                userIDMap.put(user.getId(),user);
                userEmailMap.put(user.getEmail(),user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
    }
}
