package com.service;

import com.controller.DBUtil;
import com.vo.FormBean;
import com.vo.Participant;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FormService {
    private static List<Long> formIDList=new ArrayList<>();
    static {
        PreparedStatement pst;
        ResultSet rst;
        Connection connection=DBUtil.getConnection();
        String sql="select f_id from meeting";
        try {
            pst=connection.prepareStatement(sql);
            rst=pst.executeQuery();
            while(rst.next()){
                formIDList.add(rst.getLong("f_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
    }

    public static Long randomNewFormID(){
        Random random=new Random();
        long id=Math.abs(random.nextLong())%(1000000000000L);
        if(id<1e11){
            id+=1e11;
        }else
        while (formIDList.contains(id)){
            id=Math.abs(random.nextLong())%(1000000000000L);
            if(id<1e11){
                id+=1e11;
            }
        }
        return id;
    }

    public static List<FormBean> getOwnMeetingByEmail(String email){
        List<FormBean> formBeanList=new ArrayList<>();
        FormBean formBean;
        Connection connection=DBUtil.getConnection();
        ResultSet rst;
        String sql="select * from meeting where f_authorName=(select u_name from user where u_email=?)";
        try {
            PreparedStatement pst=connection.prepareStatement(sql);
            pst.setString(1,email);
            rst=pst.executeQuery();
            while (rst.next()){
                formBean=new FormBean();
                formBean.setFormID(rst.getLong("f_id"));
                formBean.setThemes(rst.getString("f_themes"));
                formBean.setStartTime(rst.getString("f_startTime"));

                formBeanList.add(formBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formBeanList;
    }

    public static void insertFillMessage(String fillString,long id,String email){
        Connection connection=DBUtil.getConnection();
        ResultSet rst;
        String sql="update participant set p_fillMessage=? where p_email=? and p_meetingID=?";
        try {
            PreparedStatement pst=connection.prepareStatement(sql);
            pst.setString(1,fillString);
            pst.setLong(3,id);
            pst.setString(2,email);
            if (pst.executeUpdate()>0){
                System.out.println("插入fill数据："+fillString+"成功");
            }else{
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<FormBean> getJoinMeetingByEmail(String email){
        List<FormBean> formBeanList=new ArrayList<>();
        FormBean formBean;
        Connection connection=DBUtil.getConnection();
        ResultSet rst;
        String sql="select * from meeting where f_id in (select p_meetingID from participant where p_email=?)";
        try {
            PreparedStatement pst=connection.prepareStatement(sql);
            pst.setString(1,email);
            rst=pst.executeQuery();
            while (rst.next()){
                formBean=new FormBean();
                formBean.setFormID(rst.getLong("f_id"));
                formBean.setThemes(rst.getString("f_themes"));
                formBean.setStartTime(rst.getString("f_startTime"));
                formBean.setFillList(rst.getString("f_fillList"));
                formBeanList.add(formBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formBeanList;
    }

    public static FormBean getMeetingDetail(Long meetingID){

        FormBean formBean=new FormBean();
        Connection connection=DBUtil.getConnection();
        ResultSet resultSet;
        String sql="select * from meeting where f_id="+meetingID;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                formBean=new FormBean();
                formBean.setThemes(resultSet.getString("f_themes"));
                formBean.setFormID(resultSet.getLong("f_id"));
                formBean.setAuthorName(resultSet.getString("f_authorName"));
                formBean.setFillList(resultSet.getString("f_fillList"));
                formBean.setStartTime(resultSet.getString("f_startTime"));
                formBean.setEndTime(resultSet.getString("f_endTime"));
                formBean.setHotelAddress(resultSet.getString("f_hotelAddress"));
                formBean.setMeetingAddress(resultSet.getString("f_meetingAddress"));

                if (null==formBean.getFillList()){
                    System.out.println("没有须填信息");
                }else{
                    formBean.setParticipants(getParticipantsByMeetingID(formBean.getFormID(),formBean.getFillList()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formBean;
    }

    public static List<Participant> getParticipantsByMeetingID(Long meetingID,String fillMap){
        List<Participant> participants=new ArrayList<>();
        Participant participant;
        Map<String,String> map=new HashMap<>();

        Connection connection=DBUtil.getConnection();
        String sql="select * from participant where p_meetingID="+meetingID;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                participant=new Participant();
                map=new HashMap<>();
                participant.setEmail(resultSet.getString("p_email"));
                participant.setName(resultSet.getString("p_name"));
                participant.setUID(resultSet.getInt("p_uid"));

                String[] fillMapList=null;
                if (fillMap.isEmpty()){
                    System.out.println("没有需填信息");
                }else if (null!=resultSet.getString("p_fillMessage")){
                    fillMapList=fillMap.split("#");
                    String[] fillMessages=resultSet.getString("p_fillMessage").split("#");
                    for (int i=0;i<fillMapList.length;i++){
                        map.put(fillMapList[i],fillMessages[i]);
                    }
                }else{
                    fillMapList=fillMap.split("#");
                    for (int i=0;i<fillMapList.length;i++){
                        map.put(fillMapList[i],"未填写");
                    }
                }

                participant.setFillMap(map);

                participants.add(participant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participants;
    }
}
