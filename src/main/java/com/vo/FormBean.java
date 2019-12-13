package com.vo;

import java.util.Date;
import java.util.List;

public class FormBean {
    private String authorName;
    private long formID;
    private String themes;
    private String startTime;
    private String endTime;
    private String meetingAddress;
    private String hotelAddress;
    private String fillList;
    private List<Participant> participants;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "{" +
                "authorName='" + authorName + '\'' +
                ", formID=" + formID +
                ", themes='" + themes + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingAddress='" + meetingAddress + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", fillList=" + fillList +
                ", participants=" + participants +
                '}';
    }

    public long getFormID() {
        return formID;
    }

    public void setFormID(long formID) {
        this.formID = formID;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getFillList() {
        return fillList;
    }

    public void setFillList(String fillList) {
        this.fillList = fillList;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
