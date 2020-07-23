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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
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
                },
                email:{
                    email:true
                }
            },
            messages:{
                username: "请输入用户名",
                password: "请输入密码",
                email:"请输入正确的电子邮箱"
            }
        });
    });
    $.validator.setDefaults({
        submitHandler:function () {
            $.post("<%=request.getContextPath()%>/user/login",$("#fm").serialize(),
                function (data) {

            });
        }
    });

    function toRegister() {

    }
</script>
<style>
    .error{
        color:red;
    }
</style>
<body style="text-align: center">
<form id="fm">
    用户名：<input type="text" name="username"/><br/>
    邮箱：<input type="text" name="email"/><br/>
    密码：<input type="text" name="password"/><br/>
        <input type="submit" value="登录"/><br/>
        <input type="button" value="没有帐号？去添加" onclick="toRegister()"/><br/>

</form>
</body>
</html>
