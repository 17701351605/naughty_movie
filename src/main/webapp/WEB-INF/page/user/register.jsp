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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
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
</script>
<style>
    .error{
        color:red;
    }
</style>
<body style="text-align: center">
<form id="fm">
    用户名：<input type="text" name="username" id="username"/><br/><br/>
    密码：<input type="text" name="password"/><br/><br/>
    手机号：<input type="text" name="phoneNumber" id="phoneNumber"/><br/><br/>
    邮箱：<input type="text" name="email" id="email"/><br/><br/>
    级别：
        普通用户<input type="radio" name="level" value="0"/>
        商家<input type="radio" name="level" value="1"/><br/><br/>
            <input type="submit" value="注册"/><br/><br/>
</form>
</body>
</html>
