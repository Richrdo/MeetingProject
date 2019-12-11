package com.controller;

import com.alibaba.fastjson.JSON;
import com.service.FormService;
import com.vo.FormBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/getJoinMeeting")
public class JoinMeetingController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        List<FormBean> formBeanList= FormService.getJoinMeetingByEmail((String) request.getSession().getAttribute("user"));

        request.getSession().setAttribute("joinMeeting", JSON.toJSONString(formBeanList));
        formBeanList.forEach(e->{
            System.out.println("开始插入第一条数据:"+e);
            request.getSession().setAttribute("fill"+e.getFormID(),e.getFillList());
        });
        System.out.println("参会josn:"+JSON.toJSONString(formBeanList));
    }
}
