<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#signInDao").click(function (){
            alert("ok")
            $.ajax({
                type: "post",
                url: "SignInServlet",
                data:{
                    "user_name":$("#user_name").val(),
                    "user_pwd1":$("#user_pwd1").val(),
                    "user_account":$("#user_account").val(),
                    "user_phone":$("#user_phone").val(),
                    "user_email":$("#user_email").val(),
                    "department_user":$("#department_user").val()
                },
                datatype: "json",
                success: function (data) {
                    $("#user_pwd").val("");
                    $("#user_pwd1").val("");
                    $("#result").html(data);
                }
            })
        })

        $("#flash").click(function(){
            $("#user_name").val("");
            $("#user_pwd").val("");
            $("#user_pwd1").val("");
            $("#user_account").val("");
            $("#user_phone").val("");
            $("#user_email").val("");
            $("#pwd").html("");
        })

        $("#department_user").click(function(){
            $.ajax({
                type:"get",
                url:"SignInServlet?flag=1",
                datatype:"json",
                success:function (data){
                    // alert(data);
                    $("#department_user").empty();
                    var dataObj = eval("("+data+")");
                    for(var i=0;i<dataObj.length;i++){
                        $("#department_user").append("<option value='"+dataObj[i].department_name+"'>"+dataObj[i].department_name+"</option>");
                    }

                }
            })
        })
    })

    function pw(){
        var password = $("#user_pwd").val();
        if($("#user_pwd1").val() != password){
            $("#pwd").html("?????????????????????");
            $("#signInDao").attr("disabled",true);
        }
        else {
            $("#pwd").html("???");
            $("#signInDao").attr("disabled",false);
        }
    }
    function user_ac() {
        var user_account = $("#user_account").val();
        $.ajax({
            type:"get",
            url:"SignInServlet?flag=2&user_account=" + user_account,
            datatype:"json",
            success:function (data){
                    alert(data);
            }
        })
    }
    function pww() {
        if($("#user_pwd").val().length < 7) {
            $("#pwdd").html("");
            $("#pwdd").html("??????????????????6???");
        }
        else {
            $("#pwdd").html("");
        }
    }
</script>
<html>
<head>
    <title>????????????</title>
    <link rel="stylesheet" href="ind.css">
</head>
<body>
<div class="all">
    <div class="all1">
        <div class="top1">
            <br>
            <span>&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;??????????????????????????????</span>
        </div>

        <center>
            <h3>????????????</h3>
            <form >
                ?????????&emsp;&emsp;&emsp;<input type="text" name="user_name" id="user_name" maxlength="12" ><br><br>
                ????????????&emsp;&emsp;<input type="text" name="user_account" id="user_account" maxlength="12" onblur="user_ac()"><br><br>
                ?????????&emsp;&emsp;&emsp;<input type="password" name="user_pwd" id="user_pwd" maxlength="12" minlength="6" placeholder="???????????????6-12???" onblur="pww()"><br>
                &emsp;&emsp;&emsp;<span id="pwdd"></span><br>
                ???????????????&emsp;<input type="password" name="user_pwd1" id="user_pwd1" maxlength="12" minlength="6" placeholder="???????????????6-12???" onblur="pw()"><br>
                &emsp;&emsp;&emsp;<span id="pwd"></span><br>
                ???????????????&emsp;<input type="text" name="user_phone" id="user_phone" maxlength="11" placeholder="??????11????????????"><br><br>
                ???????????????&emsp;<input type="email" name="user_email" id="user_email"><br><br>
                ???????????????&emsp;<select name="department_user" id="department_user">
<%--                            <option>?????????</option>--%>
<%--                            <option>?????????</option>--%>
<%--                            <option>?????????</option>--%>
                        </select><br><br>
                <input type="submit" value="??????" name="signInDao" id="signInDao">&emsp;&emsp;
                <input type="button" value="??????" name="flash" id="flash"><br>
                <a href="login.jsp">????????????</a>
                <div id="result"></div>
            </form>
        </center>

        <div class="bottom">
            <div>
                ???????????????????????????<a href="javascript:;">?????????</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
