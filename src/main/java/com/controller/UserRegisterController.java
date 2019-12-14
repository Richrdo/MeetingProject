package com.controller;

import com.service.RegisterService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/userRegister")
public class UserRegisterController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String name=request.getParameter("userName");
        String password=request.getParameter("userPassword");
        String email=request.getParameter("userEmail");

        RegisterService registerService=new RegisterService();
        if(registerService.register(name,email,password)){
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect("register.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
