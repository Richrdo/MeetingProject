package com.controller;

import com.alibaba.fastjson.JSON;
import com.service.FormService;
import com.service.PrintFormUtil;
import com.vo.FormBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/downloadForm")
public class FormDownloadController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        FormBean formJson= FormService.getMeetingDetail(Long.parseLong(request.getParameter("id")));
        System.out.println("打印的formData为:"+formJson);

        HSSFWorkbook wb=PrintFormUtil.printMeetingForm(formJson);

        String fileName=formJson.getFormID()+".xls";
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            response.addHeader("Pragma","No-cache");
            response.addHeader("Cache-Control","No-cache");

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            OutputStream out=response.getOutputStream();


            wb.write(out);

            out.flush();
            out.close();
            System.out.println("文件写出成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
