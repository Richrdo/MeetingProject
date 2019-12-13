package com.service;

import com.alibaba.fastjson.JSON;
import com.vo.FormBean;
import com.vo.Participant;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

public class PrintFormUtil {
    public static HSSFWorkbook printMeetingForm(FormBean formBean){

//        创建excel
        HSSFWorkbook wb=new HSSFWorkbook();

//        创建sheet
        HSSFSheet sheet=wb.createSheet("会议信息");

//        创建第一列
        HSSFRow row=sheet.createRow(0);

//        表头信息
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("会议主题");

        cell=row.createCell(1);
        cell.setCellValue("组织者");

        cell=row.createCell(2);
        cell.setCellValue("开始时间");

        cell=row.createCell(3);
        cell.setCellValue("结束时间");

        cell=row.createCell(4);
        cell.setCellValue("会议地址");

        cell=row.createCell(5);
        cell.setCellValue("宾馆地点");

        row=sheet.createRow(1);

        cell=row.createCell(0);
        cell.setCellValue(formBean.getThemes());
        cell=row.createCell(1);
        cell.setCellValue(formBean.getAuthorName());
        cell=row.createCell(2);
        cell.setCellValue(formBean.getStartTime());
        cell=row.createCell(3);
        cell.setCellValue(formBean.getEndTime());
        cell=row.createCell(4);
        cell.setCellValue(formBean.getMeetingAddress());
        cell=row.createCell(5);
        cell.setCellValue(formBean.getHotelAddress());

        sheet=wb.createSheet("参会人员信息");
        row=sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("邮箱");

        int m=3;
        String[] fillList=null;
        if (null!=formBean.getFillList()){
            fillList = formBean.getFillList().split("#");
            for (int i=0;i<fillList.length;i++){
                row.createCell(m++).setCellValue(fillList[i]);
            }
        }
        List<Participant> participants=formBean.getParticipants();
        for (int i=0;i<participants.size();i++){
            Participant participant=participants.get(i);
            row=sheet.createRow(i+1);

            row.createCell(0).setCellValue(participant.getUID());
            row.createCell(1).setCellValue(participant.getName());
            row.createCell(2).setCellValue(participant.getEmail());

            if (null!=fillList){
                Map<String,String> fillMap=participant.getFillMap();
                for (int j=0;j<fillList.length;j++){
                    row.createCell(3+j).setCellValue(fillMap.get(fillList[j]));
                }
            }
        }
        return wb;
    }
}
