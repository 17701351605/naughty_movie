<%--
  Created by IntelliJ IDEA.
  User: Caohj
  Date: 2020/7/23
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--必要样式-->
    <link href="<%=request.getContextPath()%>/static/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/static/css/demo.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/Particleground.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    $.validator.setDefaults({
        submitHandler: function() {
            curCount = count;
            //设置button效果，开始计时
            $("#btnSendCode").attr("disabled", "true");
            $("#btnSendCode").val(+curCount + "秒再获取");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/user/sendEmail",$("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if (data.code != '200') {
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg("发送证码成功");
                    /* $("#code").val(data.data.num); */
                });
        }
    });
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $("#fm").validate({
            rules: {
                email: {
                    required:true,
                    email:true,
                }
            },
            messages: {
                email: {
                    required:"邮箱不能为空",
                    email: "请输入正确的邮箱",
                }
            }
        });
    });
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnSendCode").removeAttr("disabled");//启用按钮
            $("#btnSendCode").val("重新发送验证码");
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            curCount--;
            $("#btnSendCode").val( + curCount + "秒再获取");
        }
    }
    function codeLogin() {
        var index = layer.load(1, {shade: 0.2});
        $.post("<%=request.getContextPath()%>/user/codeLogin",$("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != '200') {
                    layer.msg(data.msg);
                    return;
                }
                layer.msg('登录成功！', {icon: 6, time: 2000},
                    function(){
                        window.location.href="<%=request.getContextPath()%>/movie/toMovieShow";
                    });
            }
        );
    }
    /** 注册用户*/
    function toRegister() {
        window.location.href="<%=request.getContextPath()%>/user/toRegister";
    }
    /** 去账号登陆页面*/
    function toLogin() {
        window.location.href="<%=request.getContextPath()%>/user/toLogin";
    }

</script>
<style>
    .error{
        color:red;
    }
</style>
<body style="text-align: center">
<form id="fm">
    <div class='login'>
        <div class='login_title'><span>邮箱验证码登录</span></div>
        <div class='login_fields'>
            <div class='login_fields__user'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/user_icon_copy.png'></div>
                <input name="email" placeholder='邮箱' id="email" type='text' autocomplete="off"/>
            </div>
            <div class='login_fields__password'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/key.png'></div>
                <input name="code" placeholder='验证码' type='text' autocomplete="off">
            </div>
            <input type="submit" id="btnSendCode" value="发送验证码"/>
            <div class='login_fields__submit'>
                <input type='button' value='登录' onclick="codeLogin()"><br/>
                <input type="button" value="去账号登录" onclick="toLogin()"/><br/>
                <input type="button" value="没有帐号？去添加" onclick="toRegister()"/>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    //粒子背景特效
    $('body').particleground({
        dotColor: '#E8DFE8',
        lineColor: '#133b88'
    });
</script>
</html>
