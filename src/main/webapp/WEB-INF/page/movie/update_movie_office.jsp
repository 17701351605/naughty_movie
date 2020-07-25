<%--
  Created by IntelliJ IDEA.
  User: Huangwk
  Date: 2020/7/24
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                $.post("<%=request.getContextPath()%>/movie/updateOffice",
                    $("#fm").serialize(),
                    function(data){
                        if (data.code != 200) {
                            layer.msg(data.msg);
                            layer.close(index);
                            return;
                        }
                        layer.msg(data.msg, {icon: 6, time: 2000},
                            function(){
                                parent.location.href="<%=request.getContextPath()%>/movie/toMovieOffice";
                                layer.close(index);
                            });
                    });
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#fm").validate({
                rules: {
                    movieId:{
                        required:true,
                    },
                    movieName:{
                        required:true,
                    },
                    playHall: {
                        required:true,
                    },
                    startTime: {
                        required:true,
                    },
                    seating:{
                        required:true,
                        digits:true,
                    },
                    price: {
                        required:true,
                    }
                },
                messages: {
                    movieId: {
                        required:"电影名不能为空",
                    },
                    movieName: {
                        required:"电影名不能为空",
                    },
                    playHall: {
                        required:"场次不能为空",
                    },
                    startTime: {
                        required:"开始时间不能为空",
                    },
                    seating: {
                        required:"剩余座位不能为空",
                        digits:"剩余座位只能为整数",
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
<body style="text-align: center">
<form id="fm">
    <input type="hidden" name="id" value="${movieOffice.id}" />

    电影名称:${movieOffice.movieName}<input type="hidden" name="movieName" value="${movieOffice.movieName}" /><br/>

    播放厅：<input type="text" value="${movieOffice.playHall}" name="playHall" ><br/>

    开始时间:
    <input class="Wdate" type="text"  value="${startTime}" name="startTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" ><br/>
   <%-- <input type="text" name="startTime" value="${startTime}" /><br/>--%>

    剩余座位:<input type="text" name="number" value="${movieOffice.seating}" /><br/>

    单价:<input type="text" name="price" value="${movieOffice.price}" /><br/>

    <input type="submit" value="提交" />
</form>
</body>
</html>