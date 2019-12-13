$(document).ready(function(){
    //切换状态
    $("#menu li").unbind('click').click(function(){
        $(this).siblings('li').removeClass("active");
        $(this).addClass("active");
    });

    //点击显示创建会议的表
    $("#createNewMeeting").click(function(){
        $("#showPage").load("createMeeting.html");
    });

    //添加参会人员框
    $("#addMan").unbind('click').click(function(){
        var newManList='<div class="form-group"><div class="col-sm-offset-1 col-sm-3"><input type="text" class="form-control" placeholder="姓名" name="attName"></div><div class="col-sm-4"><input type="text" class="form-control" placeholder="邮箱" name="attEmail"></div></div>';
        $("#ppsList").append(newManList);
    });

    //获取我创建的会议
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

    //获取我参加的会议
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

    //显示参会人员需填项目
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


    //提交参会人员填写项目
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

    //会议组织者检查项目
    $("a[name='btn_checkMeetingMessage']").unbind('click').click(function () {
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
                $("#myModal").find(".modal-content").load('meetingDetail.jsp',{
                    "formdata":jData
                },function () {
                    $("#myModal").modal('show');
                });
            }
        });

    });



    //提交新建的项目
    $("#submitNewMeeting").unbind('click').click(function () {
        var themes=$("#themes").val();
        var startTime=$("#startTime").val();
        var endTime=$("#endTime").val();
        var meetingAddress=$("#meetingAddress").val();
        var hotelAddress=$("#hotelAddress").val();
        var fillList;
        $(":checked").each(function () {
            fillList=fillList+$(this).val()+"#";
        });

        var pattern=/^\d{4}(\.|\-|\/)\d{1,2}(\.|\-|\/)\d{1,2}\s+\d{1,2}(:|：)\d{1,2}\s*$/;
        if(null==themes||null==startTime||null==endTime||null==meetingAddress||null==hotelAddress){
            alert("会议信息不完整，请检查");
        }else  if (!(pattern.test(startTime)&&pattern.test(endTime))){
            alert("会议时间格式不对");
        }else{
            var participants=[];
            $("#ppsList").find(".form-group").each(function () {
                var name=$(this).find("input[name='attName']").val();
                var email=$(this).find("input[name='attEmail']").val();
                if (null!=name&&null!=email){
                    var emailPattern=/^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/;
                    if (emailPattern.test(email)){
                        var data={
                            "name":name,
                            "email":email
                        };
                        participants.push(data);
                    }else{
                        alert("参会人员email格式不对");
                        return;
                    }
                }else if (null==name||null==email){
                    alert("参会人员信息不完整");
                    return;
                }

            });
        }
        var jsondata={
            "themes":themes,
                "startTime":startTime,
                "endTime":endTime,
                "meetingAddress":meetingAddress,
                "hotelAddress":hotelAddress,
                "fillList":fillList,
                "participants":participants
        };
        jsondata=JSON.stringify(jsondata);
        $.ajax({
            url:"createService",
            type:"post",
            contentType: "application/json; charset=utf-8",
            // dataType:"json",
            data:jsondata,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
            },
            success:function() {
                alert("会议创建成功");
            }
        })
    });
});


