<%--
  Created by IntelliJ IDEA.
  User: Huangwk
  Date: 2020/7/23
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/localization/messages_zh.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1, {shade: 0.2});
                $.post("<%=request.getContextPath()%>/movie/updateMovie",$("#fm").serialize(),
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
        color: #e80a0a;
    }
</style>
<body style="text-align: center">
<form id="fm">
    <input type="hidden" name="id"value="${movie.id}"/>
    <input type="hidden" name="movieId"value="${movie.movieId}"/>
    电影名称:<input type="text" name="movieName" value="${movie.movieName}"/><br/>
    电影类型：
    <c:forEach items="${list}" var="a">
        ${a.baseName}<input type="radio" name="movieType" value="${a.id}" <c:if test="${a.id == movie.movieType}">checked = checked</c:if>/>
    </c:forEach>
    <br/>
    上线时间:
    <input class="Wdate" type="text"  value="${movie.topTimeShow}" name="topTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" ><br/>
    电影时长:<input type="text" name="longTime"value="${movie.longTime}"/><br/>

    上下架:
    下架<input type="radio" name="status" value="0"<c:if test="${movie.status==0}">checked</c:if>/>
    上架<input type="radio" name="status" value="1"<c:if test="${movie.status==1}">checked</c:if>/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
