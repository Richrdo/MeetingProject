package com.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    private static HtmlEmail email=new HtmlEmail();//创建HtmlEmail实例对象

    public static boolean sendNotifyEmail(String address,String name,String themes,String authorName){
        email=new HtmlEmail();
        try {
            email.setHostName("smtp.126.com");
            email.setCharset("UTF-8");
            email.addTo(address);
            email.setFrom("ceaserborgia@126.com","MeetingManager");
            email.setAuthentication("ceaserborgia@126.com","10652480qst");
            email.setSubject("南昌大学会议邮件提醒");
            email.setMsg("尊敬的"+name+"：\n"+authorName+"组织的\""+themes+"\"即将举行，请您进入如下链接填写：http://47.106.177.200：8080/meeting");

            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
