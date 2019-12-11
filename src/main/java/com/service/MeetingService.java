package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.controller.DBUtil;
import com.vo.FormBean;
import com.vo.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MeetingService {
    FormBean formBean;


    public void holdMeeting(JSONObject jsonObject, String authorName){
        FormBean formBean=JSON.parseObject(jsonObject.toString(),FormBean.class);
        formBean.setFormID(FormService.randomNewFormID());
        formBean.setAuthorName(authorName);
        submitFormToDB(formBean);
        formBean.getParticipants().forEach(e->{
            submitParticipantToDB(e,formBean.getFormID());
        });

    }



    private void submitFormToDB(FormBean form){
        System.out.println("开始插入会议表"+formBean+"到数据库");
        Connection connection=DBUtil.getConnection();
        String sql="insert into meeting(f_id,f_authorName,f_themes,f_startTime,f_endTime,f_meetingAddress,f_hotelAddress,f_fillList) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst=connection.prepareStatement(sql);
            pst.setLong(1,form.getFormID());
            pst.setString(2,form.getAuthorName());
            pst.setString(3,form.getThemes());
            pst.setString(4,form.getStartTime());
            pst.setString(5,form.getEndTime());
            pst.setString(6,form.getMeetingAddress());
            pst.setString(7,form.getHotelAddress());
            pst.setString(8,form.getFillList());
            if (pst.executeUpdate()>0){
                System.out.println("插入该条记录成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void submitParticipantToDB(Participant participant,Long formID){
        System.out.println("开始插入用户"+participant+"到数据库");
        Connection connection=DBUtil.getConnection();
        PreparedStatement pst;
        String sql="insert into participant(p_name,p_email,p_meetingID) values (?,?,?)";
        try {
            pst= connection.prepareStatement(sql);
            pst.setString(1,participant.getName());
            pst.setString(2,participant.getEmail());
            pst.setLong(3,formID);
            if (pst.executeUpdate()>0){
                System.out.println("插入该条记录成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
    }
}
