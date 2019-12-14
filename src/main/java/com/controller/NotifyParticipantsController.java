package com.controller;

import com.service.MeetingData;
import com.vo.FormBean;
import com.vo.Participant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/sendEmailToParticipants")
public class NotifyParticipantsController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Long id=Long.parseLong(request.getParameter("id"));
        FormBean formBean= MeetingData.getFormBeanByID(id);
        List<Participant> participants=formBean.getParticipants();

        participants.forEach(e->{
            EmailUtil.sendNotifyEmail(e.getEmail(),e.getName(),formBean.getThemes(),formBean.getAuthorName());
        });
    }
}
