package com.service;

import com.vo.FormBean;

import java.util.HashMap;
import java.util.Map;

public class MeetingData {
    private static Map<Long, FormBean> formBeanMap=new HashMap<>();

    public static void addToFormMap(FormBean formBean){
        formBeanMap.put(formBean.getFormID(),formBean);
    }

    public static FormBean getFormBeanByID(Long id){
        return formBeanMap.get(id);
    }
}
