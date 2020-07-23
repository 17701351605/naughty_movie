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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }


    function toRegister() {
        layer.open({
            type: 2,
            title: '注册用户',
            shadeClose: true,
            shade: 0.4,
            area: ['500px', '80%'],
            content: '<%=request.getContextPath()%>/user/toRegister'
        });
    }
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
    邮箱：<input type="text" name="email"/><br/>
    验证码：<input type="text" name="code"/><br/>
        <input type="submit" value="登录"/><br/>
        <input type="button" value="账号登录" onclick="toLogin()"/><br/><br/>
        <input type="button" value="没有帐号？去添加" onclick="toRegister()"/><br/>
</form>
</body>
</html>
