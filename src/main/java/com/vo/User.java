package com.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Integer> ownConferenceList=new ArrayList<>();
    private List<Integer> joinConferenceList=new ArrayList<>();

    public void holdAConference(int conferenceID){
        ownConferenceList.add(conferenceID);
    }
    public void joinAConference(int conferenceID){
        joinConferenceList.add(conferenceID);
    }

    public List<Integer> getOwnConferenceList() {
        return ownConferenceList;
    }

    public void setOwnConferenceList(List<Integer> ownConferenceList) {
        this.ownConferenceList = ownConferenceList;
    }

    public List<Integer> getJoinConferenceList() {
        return joinConferenceList;
    }

    public void setJoinConferenceList(List<Integer> joinConferenceList) {
        this.joinConferenceList = joinConferenceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}