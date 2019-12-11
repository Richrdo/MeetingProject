package com.controller;

import com.service.LoginService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userLogin")
public class LoginController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        String email=request.getParameter("userEmail");
        String password=request.getParameter("userPassword");

        LoginService service=new LoginService();

        try {
            response.setCharacterEncoding("GBK");
            PrintWriter out= response.getWriter();
            if (service.isMatchUser(email,password)){
                //TODO 登入成功跳转原页面
                System.out.println("接下来跳转到主页");
                HttpSession session=request.getSession();
                session.setAttribute("user",email);
                response.sendRedirect("index.jsp");
            }else{
                //TODO 登录失败跳回原登入页面
                response.sendRedirect("login.html");
                out.println("登录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
