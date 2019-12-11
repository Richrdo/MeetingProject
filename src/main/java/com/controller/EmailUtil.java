package com.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    HtmlEmail email=new HtmlEmail();//创建HtmlEmail实例对象

    public boolean sendLoginEmail(String address,String code){
        try {
            email.setHostName("smtp.126.com");
            email.setCharset("UTF-8");
            email.addTo(address);
            email.setFrom("ceaserborgia@126.com","MeetingManager");
            email.setAuthentication("ceaserborgia@126.com","10652480qst");
            email.setSubject("南大会议系统注册");
            email.setMsg("尊敬的用户：\n您的验证码为："+code);

            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
