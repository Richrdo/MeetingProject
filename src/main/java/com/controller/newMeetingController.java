package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.FormService;
import com.service.JsonService;
import com.service.MeetingService;
import com.vo.FormBean;
import com.vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createService")
public class newMeetingController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        /** 设置响应头允许ajax跨域访问 **/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        try {
            PrintWriter out=response.getWriter();
            out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonService jsonService =new JsonService();
        try {
            JSONObject jsonObject= jsonService.receivePost(request);
            MeetingService meetingService=new MeetingService();
            System.out.println(jsonObject);
            meetingService.holdMeeting(jsonObject,UserDate.userEmailMap.get((String)request.getSession().getAttribute("user")).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
