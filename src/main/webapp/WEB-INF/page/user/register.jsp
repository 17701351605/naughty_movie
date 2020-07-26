<%--
  Created by IntelliJ IDEA.
  User: Caohj
  Date: 2020/7/23
  Time: 11:48
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
    $().ready(function () {
        $("#fm").validate({
            rules:{
                username:{
                    required: true,
                    minlength:2,
                    remote: {
                        url: "<%=request.getContextPath()%>/user/findUserByName",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            username: function() {
                                return $("#username").val();
                            }
                        },
                        dataFilter:function(data,type){
                            if (data == 'true') {
                                return true;
                            }else {
                                return false;
                            }
                        }
                    }
                },
                password:{
                    required: true
                },
                email: {
                    required:true,
                    email:true,
                    remote: {
                        url: "<%=request.getContextPath()%>/user/findUserByName",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            email: function() {
                                return $("#email").val();
                            }
                        },
                        dataFilter:function(data,type){
                            if (data == 'true') {
                                return true;
                            }else {
                                return false;
                            }
                        }
                    }
                },
                phoneNumber:{
                    required:true,
                    isMobile : true,
                    remote: {
                        url: "<%=request.getContextPath()%>/user/findUserByName",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            phoneNumber: function() {
                                return $("#phoneNumber").val();
                            }
                        },
                        dataFilter:function(data,type){
                            if (data == 'true') {
                                return true;
                            }else {
                                return false;
                            }
                        }
                    }
                }
            },
            messages:{
                username:{
                    required: "请输入用户名",
                    minlength: "用户名必需由两个字母组成",
                    remote: "该用户名已存在"
                },
                password: {
                    required: "请输入密码",
                },
                email:{
                    required:"请输入邮箱",
                    email:"请输入正确的邮箱",
                    remote: "该邮箱已存在"
                },
                phoneNumber: {
                    required:"请输入手机号",
                    isMobile: "请输入正确的手机号",
                    remote: "该手机号已存在"
                }
            }
        });
    });
    //手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");

    $.validator.setDefaults({
        submitHandler:function () {
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/user/register",$("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if (data.data != true){
                        layer.msg("注册失败");
                    }
                    layer.msg('注册成功！', {icon: 6, time: 2000},
                        function(){
                            parent.location.href="<%=request.getContextPath()%>/user/toLogin";
                    });
            });
        }
    });
    /** 去账号登陆页面*/
    function toLogin() {
        window.location.href="<%=request.getContextPath()%>/user/toLogin";
    }
    /** 去邮箱验证码页面*/
    function toEmail() {
        window.location.href="<%=request.getContextPath()%>/user/toEmail";
    }
</script>
<style>
    .error{
        color:red;
    }
    .logins{
        box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
        opacity: 1;
        top: 20px;
        -webkit-transition-timing-function: cubic-bezier(0.68, -0.25, 0.265, 0.85);
        -webkit-transition-property: -webkit-transform,opacity,box-shadow,top,left;
        transition-property: transform,opacity,box-shadow,top,left;
        -webkit-transition-duration: .5s;
        transition-duration: .5s;
        -webkit-transform-origin: 161px 100%;
        -ms-transform-origin: 161px 100%;
        transform-origin: 161px 100%;
        -webkit-transform: rotateX(0deg);
        transform: rotateX(0deg);
        position: relative;
        width: 300px;
        height: 370px;
        position: absolute;
        left: 0;
        right: 0;
        margin: auto;
        top: 0;
        bottom: 0;
        padding: 100px 40px 40px 40px;
        background: #35394a;
        background: -webkit-gradient(linear, left bottom, right top, color-stop(0%, #35394a), color-stop(100%, rgb(0, 0, 0)));
        background: -webkit-linear-gradient(230deg, rgba(53, 57, 74, 0) 0%, rgb(0, 0, 0) 100%);
        background: linear-gradient(230deg, rgba(53, 57, 74, 0) 0%, rgb(0, 0, 0) 100%);
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='rgba(53, 57, 74, 0)', endColorstr='rgb(0, 0, 0)',GradientType=1 );
    }
</style>
<body style="text-align: center">
<form id="fm">
    <div class='logins'>
        <div class='login_title'><span>用户注册</span></div>
        <div class='login_fields'>
            <div class='login_fields__user'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/user_icon_copy.png'></div>
                <input name="username" placeholder='用户名' id="username" type='text' autocomplete="off"/>
            </div>
            <div class='login_fields__password'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/lock_icon_copy.png'></div>
                <input name="password" placeholder='密码' type='password' autocomplete="off">
            </div>
            <div class='login_fields__password'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/user_icon_copy.png'></div>
                <input name="phoneNumber" placeholder='手机号' id="phoneNumber" type='text' autocomplete="off">
            </div>
            <div class='login_fields__user'>
                <div class='icon'><img src='<%=request.getContextPath()%>/static/img/user_icon_copy.png'></div>
                <input name="email" placeholder='邮箱' id="email" type='text' autocomplete="off"/>
            </div>
            <div class='login_fields__password'>
                &nbsp&nbsp&nbsp
                级别：&nbsp
                普通用户<input name="level" type="radio" value="0" autocomplete="off"/>
                商家<input name="level" type="radio" value="1" autocomplete="off"/>
            </div>
            <div class='login_fields__submit'>
                <input type='submit' value='注册'><br/>
                <input type="button" value="去验证码登录" onclick="toEmail()"/><br/>
                <input type="button" value="已有帐号？去登录" onclick="toLogin()"/>
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
