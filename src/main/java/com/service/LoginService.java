package com.service;

import com.controller.DBUtil;

import java.sql.*;

public class LoginService {
    PreparedStatement pst=null;
    ResultSet resultSet=null;

    public boolean isMatchUser(String email,String pw){
        DBUtil dbUtil=new DBUtil();
        Connection connection = dbUtil.getConnection();
        String sql="select u_password from user where u_email=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,email);
            resultSet=pst.executeQuery();
            while (resultSet.next()){
                if (pw.equals(resultSet.getString("u_password"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
