<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.vo.FormBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.TypeReference" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.text.Normalizer" %><%--
  Created by IntelliJ IDEA.
  User: 17403
  Date: 2019/12/9
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        List<FormBean> formBeans=JSONObject.parseArray((String)session.getAttribute("ownMeeting"),FormBean.class);
        if (formBeans.isEmpty()){
    %>
    <h2>你还没有创建的会议</h2>
    <%
        }else{
    %>
    <div class="container-fluid">
        <table class="table table-hover">
            <tr>
                <td>会议编号</td>
                <td>会议主题</td>
                <td>会议时间</td>
                <td></td>
            </tr>
            <%
                for (int i=0;i<formBeans.size();i++){
                    FormBean formBean=formBeans.get(i);
            %>
            <tr>
                <td><%=formBean.getFormID()%></td>
                <td><%=formBean.getThemes()%></td>
                <td><%=formBean.getStartTime()%></td>
                <td>
                    <a  rel="tooltip" data-placement="bottom" class="btn btn-info" name="btn_checkMeetingMessage" >查看信息
                    </a>
                </td>
            </tr>
            <%
                }
                }
            %>

        </table>
    </div>


    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>
