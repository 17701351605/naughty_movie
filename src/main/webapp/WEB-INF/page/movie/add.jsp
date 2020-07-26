<%--
  Created by IntelliJ IDEA.
  User: Huangwk
  Date: 2020/7/24
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/static/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/static/css/demo.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1, {shade: 0.2});
                $.post("<%=request.getContextPath()%>/movie/addMovie",
                    $("#fm").serialize(),
                    function(data){
                        if (data.code != 200) {
                            layer.msg(data.msg);
                            layer.close(index);
                            return;
                        }
                        layer.msg(data.msg, {icon: 6, time: 2000},
                            function(){
                                parent.location.href="<%=request.getContextPath()%>/movie/toMovieShow";
                                layer.close(index);
                            });
                    });
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#fm").validate({
                rules: {
                    movieName: {
                        required:true,
                    },
                    topTime: {
                        required:true,
                    },
                    longTime: {
                        required:true,
                    }
                },
                messages: {
                    movieName: {
                        required: "请输入电影",
                    },
                    topTime: {
                        required:"上架时间不能为空",
                    },
                    longTime: {
                        required:"电影时长不能为空",
                    }
                }
            });
        });
    </script>
</head>
<style>
    .error{
        color:red;
    }
</style>
<body style="text-align: center">
<form id="fm">
    电影名称:<input type="text" name="movieName"/><br/>
    电影类型：
    <c:forEach items="${list}" var="a">
        ${a.baseName}<input type="radio" name="movieType" value="${a.id}" />
    </c:forEach><br/>
    演员:
    <input type="text" name="actorName"/><br/>
    <input type="hidden" name="status" value="1"/><br/>
    <input type="hidden" name="isDel" value="1"/><br/>
    上线时间:
    <input class="Wdate" type="text" name = "topTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" ><br>
    电影时长:<input type="text" name="longTime"/><br/>
    <input type="submit" value="添加"/>
</form>
</body>
</html>