<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/static/layer/layer.js"></script>
<script type="text/javascript" src="/static/validate/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/static/validate/dist/localization/messages_zh.js"></script>
<script type="text/javascript">

        /** 购票价格 */
        function buy(){
            if (parseInt($("#buyNum").val()) > parseInt($("#seating").val())) {
                return;
            }
            //订票超过5张打9.5折
            if ($("#buyNum").val() > 4 ) {
                $("#buyPrice").val(parseInt($("#buyNum").val())*parseInt($("#price").val())*0.95);
                return;
            }
            $("#buyPrice").val(parseInt($("#buyNum").val())*parseInt($("#price").val()));
        }

        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1, {shade: 0.2});
                $.post(
                    "<%=request.getContextPath()%>/userOrder/addOrder",
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
                    buyNum:{
                        required:true,
                        digits:true,
                        min:1,
                        max:${movie.seating},
                    }
                },
                messages: {
                    buyNum: {
                        required:"购买票数不能为空",
                        digits:"购买票数必须为整数",
                        min:"购买票数最少为一",
                        max:"购买票数超过库存",
                    }
                }
            });
        });

        /**取消购买*/
        function back(){
            window.location.href="<%=request.getContextPath()%>/toMovieOffice?id="+${movie.id};
        }



    </script>
</head>
<style>
    .error{
        color: #ff0000;
    }

</style>
<body>
    <form id="fm">
        <input type="hidden" name="id" value="${movie.id}">
        <input type="hidden" name="movieId" value="${movie.movieId}">
        <input type="hidden" name="userId" value="${user.id}">

        电影名称:${movie.movieName}<input type="hidden" value="${movie.movieName}" name="movieName"/><br/>

        播放厅:
            <c:forEach items="${movieOfficeList}" var="a">
                <c:if test="${a.id == movie.playHall}">${a.baseName}</c:if>
            </c:forEach>
            <input type="hidden" value="${movie.playHall}" name="playHall"/><br/>

        开始时间:${movie.startTime}<br/>

        价格:${movie.price}<input type="hidden" value="${movie.price}" name="price" id="price"/><br/>

        剩余座位:${movie.seating}<input type="hidden" value="${movie.seating}" id="seating" name="seating"/><br/>

        购买数量:<input type="text" name="buyNum" id="buyNum" onchange="buy()"/><br/>

        总价:<input type="text" name="buyPrice" id="buyPrice" readonly="readonly" /><br/>

        <input type="submit" value="购买" /><br/>
        <input type="button" value="取消购买" onclick="back()"/><br/>
    </form>
</body>
</html>