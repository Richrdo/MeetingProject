<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.vo.FormBean" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vo.Participant" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 17403
  Date: 2019/12/11
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        String jsonString=request.getParameter("formdata");
        FormBean formBean= JSON.parseObject(jsonString,FormBean.class);
    %>
    <div class="modal-title">
        <h3 class="text-center"><%=formBean.getThemes()%></h3>
    </div>
    <div class="modal-body"  >
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-sm-12">
                <h4 class="text-center">组织者：<small><%=formBean.getAuthorName()%></small></h4>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-2 column">
                <p>开始时间</p>
            </div>
            <div class="col-md-4 column">
                <p><%=formBean.getStartTime()%></p>
            </div>
            <div class="col-md-2 column">
                <p >结束时间</p>
            </div>
            <div class="col-md-4 column">
                <p><%=formBean.getEndTime()%></p>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-2">
                <p>会议地点</p>
            </div>
            <div class="col-md-4">
                <p><%=formBean.getMeetingAddress()%></p>
            </div>
            <div class="col-md-2">
                <p>宾馆地点</p>
            </div>
            <div class="col-md-4">
                <p><%=formBean.getHotelAddress()%></p>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-12">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>参会编号</th>
                        <th>姓名</th>
                        <th>邮箱</th>
                        <%
                            String []fillMap=null;
                            if (null!=formBean.getFillList()){
                                 fillMap=formBean.getFillList().split("#");
                                 for (int i=0;i<fillMap.length;i++){
                        %>
                        <th><%=fillMap[i]%></th>
                        <%
                                }
                            }
                        %>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            if (null!=formBean.getParticipants()){
                                List<Participant> participants=formBean.getParticipants();
                                for(int i=0;i<participants.size();i++){
                                    Participant participant=participants.get(i);
                        %>
                        <tr>
                        <td><%=participant.getUID()%></td>
                        <td><%=participant.getName()%></td>
                        <td><%=participant.getEmail()%></td>
                        <%
                                    if(null!=fillMap){
                                        Map<String,String> fillMessageMap=participant.getFillMap();
                                        for (int j=0;j<fillMap.length;j++){
                        %>
                        <td><%=fillMessageMap.get(fillMap[j])%></td>
                        <%
                                        }
                                    }
                        %>
                        </tr>
                    <%
                        }
                        }
                    %>
                    </tbody>
                </table>
                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <button type="button" class="btn btn-default left" id="btn_notify_participants" onclick="sendEmailToNotify(<%=formBean.getFormID()%>)">邮箱提醒</button>
                    </div>
                    <div class="col-md-6 column">
                        <button type="button" class="btn btn-default" id="btn_print_form" onclick="printForm(<%=formBean.getFormID()%>)">打印表单</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--        一个隐藏的span，用于存放formbean数据--%>
        <span id="formBeanHiddenSpan" hidden><%=JSONObject.toJSONString(formBean)%></span>
    </div>

    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
    <link href="css/style.css">

<script>
    function printForm(id) {
        var xhr=new XMLHttpRequest();
        xhr.open("get","downloadForm?id="+id,true);
        xhr.responseType="blob";

        xhr.onload=function (ev) {
            if (this.status==200){
                var blob=this.response;
                var URL=window.URL || window.webkitURL;

                var link=document.createElement('a');
                link.href=URL.createObjectURL(blob);
                link.click();
            }
        }
        xhr.send();
    }

    function sendEmailToNotify(id){
        $.ajax({
            url:"sendEmailToParticipants",
            type:'post',
            data:{
                "id":id
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
            },
            success:function() {
                alert("已发送邮件提醒参会人员填写信息");
            }
        });
    }
</script>
</body>
</html>
