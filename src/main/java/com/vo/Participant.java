package com.vo;


import java.util.List;
import java.util.Map;

public class Participant {
    private String name;
    private int UID;
    private String email;
    private Map<String,String> fillMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getFillMap() {
        return fillMap;
    }

    public void setFillMap(Map<String, String> fillMap) {
        this.fillMap = fillMap;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", UID=" + UID +
                ", email='" + email + '\'' +
                ", fillMap=" + fillMap +
                '}';
    }
}
