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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getOwnMeetingListByUserEmail")
public class MeetingFormController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        List<FormBean> formList=new ArrayList<>();
        String email= (String) request.getSession().getAttribute("user");
        formList=FormService.getOwnMeetingByEmail(email);

        String json= JSON.toJSONString(formList);
        System.out.println(json);

        request.getSession().setAttribute("ownMeeting",json);
    }
}
