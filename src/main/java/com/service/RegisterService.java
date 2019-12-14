package com.service;

import com.controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterService {
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public Boolean register(String name,String email,String password){
        Connection connection= DBUtil.getConnection();
        String sql="insert into user(u_name,u_password,u_email) values(?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            if (preparedStatement.executeUpdate()>0){
                System.out.println("注册成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}
