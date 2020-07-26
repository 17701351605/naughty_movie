<%--
  Created by IntelliJ IDEA.
  User: Huangwk
  Date: 2020/7/24
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                $.post("<%=request.getContextPath()%>/movie/addMovieOffice",$("#fm").serialize(),
                    function(data){
                        if (data.code != 200) {
                            layer.msg(data.msg);
                            layer.close(index);
                            return;
                        }
                        layer.msg("添加成功", {icon: 6, time: 2000},
                            function(){
                                parent.location.href="<%=request.getContextPath()%>/movie/toMovieOffice?id="+${movie.id};
                                layer.close(index);
                            });
                    });
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#fm").validate({
                rules: {
                    playHall: {
                        required:true,
                    },
                    startTime: {
                        required:true,
                    },
                    price: {
                        required:true,
                    }
                },
                messages: {
                    playHall: {
                        required:"场次不能为空",
                    },
                    startTime: {
                        required:"开始时间不能为空",
                    },
                    price: {
                        required:"价格不能为空",
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
<body style="text-align: center;color: #BEE9F0">
<form id="fm">
    <input type="hidden" name ="movieId" value="${movie.id}">
    <input type="hidden" name ="movieName" value="${movie.movieName}">
    电影名称:${movie.movieName}<br>
    播放厅：
        <select name="playHall">
            <c:forEach items="${officeList}" var="o">
                <option value="${o.id}">${o.baseName}</option>
            </c:forEach>
        </select>
    <br/>
    开始时间：<input class="Wdate" type="text" name="startTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" ><br>
    单价：<input type="text" name="price"/><br/>
    <input type="hidden" name="seating" value="50"/>
    <input type="hidden" name="isDel" value="1"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>