$(document).ready(function(){
	$("#menu li").unbind('click').click(function(){
		$(this).siblings('li').removeClass("active");
		$(this).addClass("active");
	});
	
	$("#createNewMeeting").click(function(){
		$("#showPage").load("createMeeting.html");
	});
	
	
	$("#addMan").unbind('click').click(function(){
		var newManList='<div class="form-group"><div class="col-sm-offset-1 col-sm-3"><input type="text" class="form-control" placeholder="姓名" name="attName"></div><div class="col-sm-4"><input type="text" class="form-control" placeholder="邮箱" name="attEmail"></div></div>';
		$("#ppsList").append(newManList);
	});

	$("#myCreateMeeting").unbind('click').click(function (){

		$.ajax({
			url:"getOwnMeetingListByUserEmail",
			type:"get",
			beforeSend:function () {
				$("#myCreateMeeting").attr({disabled:"disabled"});
				$("#loading").show();
			},
			complete:function () {
				$("#myCreateMeeting").removeAttr("disabled");
				$("#loading").hide();
			},
			success:function (data) {
				$("#showPage").load("ownMeeting.jsp");
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}
		})
	});

	$("#myJoinMeeting").unbind('click').click(function () {
		$.ajax({
			url:"getJoinMeeting",
			type:"get",
			beforeSend:function () {
				$("#myJoinMeeting").attr({disabled:"disabled"});
                $("#loading").show();
			},
			complete:function () {
				$("#myJoinMeeting").removeAttr("disabled");
				$("#loading").hide();
			},
			success:function (data) {
				$("#showPage").load("joinMeeting.jsp");
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}
		});

	});

    $("a[name='btn_fillMessage']").unbind('click').click(function () {
        var id=$(this).parent().siblings("td:first-child").html();
        console.log(id);
        $("#myModal").find(".modal-content").load('fillMessage.jsp',{
            "id":id
        },function () {
            console.log("ok");
            $("#myModal").modal('show');
        });

    });


	$("#btn_submitFill").unbind('click').click(function () {
		var fillString=' ';
		$(this).parents("form").find("input[type='text']").each(function () {
			fillString=fillString+$(this).val()+"#";
		});
		console.log("fillString is "+fillString);
		$.ajax({
			url:"setFillMessage",
			type:"post",
			data:{
				"meetingID":$("#meetingIDinModal").html(),
				"fillString":fillString
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			},
			success:function () {
				alert("数据填写成功");
			}
		})
	});

	$("#btn_checkMeetingMessage").unbind('click').click(function () {
        var id=$(this).parent().siblings("td:first-child").html();
        var json;
        $.ajax({
            url:"getMeetingDetailById",
            type:"post",
            data:{
                "id":id
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
            },
            success:function (jData) {
                console.log("获取数据成功"+jData);
                json=jData;
            }
        });
        $("#myModal").find(".modal-content").load('meetingDetail.jsp',{
            "json":json
        },function () {
            $("#myModal").modal('show');
        });
    });

});




























//$(document).ready(function(){
//	$(".att").click(function(){
//		$.post("AttForm.jsp",
//			{
//				MeetId:$(".v1").val,
//				OrgAccount:$(".v2").val,
//				meetTime:$(".v3").val
//			},
//			function(data,status){
//				alert(status);
//				//以下两个应该都可以，不行的话换着跑一下
//				$(".meet").load(data);
////			$(".meet").html(data);
//			})
//	})
//})