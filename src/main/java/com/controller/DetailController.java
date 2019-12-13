package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.FormService;
import com.vo.FormBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getMeetingDetailById")
public class DetailController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Long meetingID= Long.parseLong(request.getParameter("id"));

        System.out.println("获取的会议id为："+meetingID);
        FormBean formBean=FormService.getMeetingDetail(meetingID);

        String json= JSON.toJSONString(formBean);
        System.out.println("传递的form为："+json);

        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out=response.getWriter();
            out.println(json);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
