package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.FormService;
import com.vo.FormBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getMeetingDetailById")
public class DetailController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Long meetingID= Long.parseLong(request.getParameter("id"));

        FormBean formBean=FormService.getMeetingDetail(meetingID);

        String json= JSON.toJSONString(formBean);
        System.out.println("传递的form为："+json);
    }
}
