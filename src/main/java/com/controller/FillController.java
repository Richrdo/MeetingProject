package com.controller;


import com.service.FormService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setFillMessage")
public class FillController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String fillString=request.getParameter("fillString");
        Long meetingID=Long.parseLong(request.getParameter("meetingID"));

        FormService.insertFillMessage(fillString,meetingID, (String) request.getSession().getAttribute("user"));
        System.out.println("插入数据"+fillString+"success");
    }
}
