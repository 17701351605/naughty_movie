<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--必要样式-->
    <link href="<%=request.getContextPath()%>/static/css/styles.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/static/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/Particleground.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }
    $().ready(function () {
        $("#fm").validate({
            rules:{
                username:{
                    required: true,
                },
                password:{
                    required: true,
                }
            },
            messages:{
                username: {
                    required:"请输入用户名"
                },
                password: {
                    required:"请输入密码"
                }
            }
        });
    });
    $.validator.setDefaults({
        submitHandler:function () {
            $.post("<%=request.getContextPath()%>/user/login",$("#fm").serialize(),
                function (data) {
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    window.location.href="<%=request.getContextPath()%>/index/toIndex";
            });
        }
    });
    //去注册用户
    function toRegister() {
        window.location.href="<%=request.getContextPath()%>/user/toRegister";
    }
    //去邮箱验证码页面
    function toEmail() {
        window.location.href="<%=request.getContextPath()%>/user/toEmail";
    }
</script>
<style>
    .error{
        color:red;
    }
</style>
<body style="text-align: center;color: #0000FF">
<form id="fm">
    <div class='login'>
        <div class='login_title'><span>用户登录</span></div>
        <div class='login_fields'>
            <div class='login_fields__user'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/user_icon_copy.png'></div>
                <input name="username" placeholder='用户名' type='text' autocomplete="off"/>
            </div>
            <div class='login_fields__password'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/lock_icon_copy.png'></div>
                <input name="password" placeholder='密码' type='password' autocomplete="off">
            </div>
            <div class='login_fields__submit'>
                <input type='submit' value='登录'><br/>
                <input type="button" value="去验证码登录" onclick="toEmail()"/><br/>
                <input type="button" value="没有帐号？去添加" onclick="toRegister()"/>
            </div>
        </div>
        <div class='disclaimer'><p>欢迎登陆点金淘气影城</p></div>
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
