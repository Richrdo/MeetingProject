<%@ page import="com.vo.FormBean" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 17403
  Date: 2019/12/9
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
</head>
<body style="padding: 20px">


<div class="modal-title">
	<h1 class="text-center">会议编号:<span id="meetingIDinModal"><%=request.getParameter("id")%></span></h1>
</div>
<div class="modal-body">
	<form	class="form-horizontal">
		<%
            System.out.println("aefbwiejof");
			String fillListString= (String) session.getAttribute("fill"+request.getParameter("id"));
			String[] fillList=fillListString.trim().split("#");
			for (int i=0;i<fillList.length;i++){
		%>
		<div class="form-group">
			<label class="col-sm-2 control-label"><%=fillList[i]%></label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="<%=fillList[i]%>">
			</div>
		</div>
		<%
			}
		%>
		<div class="text-right">
				<a id="btn_closeModel" class="btn btn-default">关闭</a>
				<a id="btn_submitFill" class="btn btn-primary">提交</a>
		</div>
	</form>
</div>


	<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/index.js"></script>


</body>
</html>
