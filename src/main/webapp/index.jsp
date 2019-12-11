<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<title>会议系统</title>
<%--	<script src="js/jquery-3.3.1.js"></script>--%>

</head>
<body>
<%
    if (null==session.getAttribute("user")){
         response.sendRedirect("/login.html");
     }
 %>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-3 col-4">
					<span class="navbar-brand">南大会议系统</span>
				</div>
				<div class="col-md-9 col-9 fuc-menu">
					<ul class="nav nav-pills pull-right" id="menu">
						<li class="active"><a href="#" >首页</a></li>
						<li><a href="#" >个人中心</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-9 col-md-9 col-lg-9" id="showPage">

				<img src="image/loading.gif" id="loading" height="300px" width="300px" hidden>
			</div>
			<div class="col-md-3 col-lg-2" id="funcMenu">
				<ul class="nav nav-tabs">
					<li><a href="#" id="myCreateMeeting">我创建的会议</a></li>
					<li><a href="#" id="myJoinMeeting">我加入的会议</a></li>
					<li><a href="#" id="createNewMeeting">创建新的会议</a></li>
				</ul>
			</div>
		</div>
	</div>


<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body"></div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>





<script src="js/jquery-3.3.1.js"></script>
<script src="js/index.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/commons.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
<link href="css/style.css" rel="stylesheet"/>

</body>
</html>
