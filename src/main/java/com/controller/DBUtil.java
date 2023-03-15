package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static Connection connection;
    private static ResultSet resultSet;

    public static Connection getConnection(){
        String name="name";
        String pw="password";
        String url="jdbc:mysql://localhost:3306/conference?serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url,name,pw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
