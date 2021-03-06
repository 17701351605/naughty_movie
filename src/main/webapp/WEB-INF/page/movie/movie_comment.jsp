<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/7/23
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<%=request.getContextPath()%>/static/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/static/css/demo.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css"  media="all">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/movie_like/css/style.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/Particleground.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/localization/messages_zh.js"></script>
    <script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/movie_like/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">

        $(function(){
            search();
        })

        function search() {
            $.post("<%=request.getContextPath()%>/movie/evaluatShow",
                {"movieId":${movie.id}},
                function (data) {
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var movie = data.data[i];
                        html += "<tr>";
                        html += "<td>" + movie.userName + "</td>";
                        html += "<td>" + movie.remark + "</td>";
                        html += "<td>" + movie.createTime + "</td>";
                        html += "</tr>";
                    }
                    $("#tb").html(html);
                });
        }

        /** 点赞 */
        function toLike(id){
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/toLike?id=" + id,
                {},
                function(data){
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            layer.close(index);
                            location.href = "<%=request.getContextPath()%>/movie/toMovieDetail/" + ${movie.id};
                        });
                })
        }

        /** 评论*/
        $(document).ready(function(){
            $("#show").click(function(){
                $("#testDiv").show();
            });
            $("#hide").click(function(){
                $("#testDiv").hide();
            });

        })

        /** 评分*/
        layui.use('rate', function () {
            var rate = layui.rate;
            //显示文字
            rate.render({
                elem: '#test2'
                <c:if test="${score!= null}">
                ,value: ${score}//初始值
                </c:if>
                <c:if test="${score== null}">
                ,value: 0//初始值
                </c:if>
                , text: true //开启文本
                ,choose: function (value) {
                    if (value < 4) layer.msg('感谢您的支持')
                    if (value >= 4) layer.msg('么么哒，感谢您的支持')
                    $.post("<%=request.getContextPath()%>/movie/updateMovieLikeScore",
                        {"score": value, "movieId":${movie.id}},
                        function (data) {
                            location.href = "<%=request.getContextPath()%>/movie/toMovieDetail/" + ${movie.id};
                            return;
                        })
                }
                <c:if test="${score!= null}">
                 ,readonly: true
                </c:if>
            });
        });


        $(document).ready(function() {
            $('body').on("click",'.heart',function() {
                var A=$(this).attr("id");
                var B=A.split("like");
                var messageID=B[1];
                var C=parseInt($("#likeCount"+messageID).html());
                $(this).css("background-position","")
                var D=$(this).attr("rel");
                if(D === 'like') {
                    $("#likeCount"+messageID).html(C+1);
                    $(this).addClass("heartAnimation").attr("rel","unlike");
                }
                else {
                    $("#likeCount"+messageID).html(C-1);
                    $(this).removeClass("heartAnimation").attr("rel","like");
                    $(this).css("background-position","left");
                }
            });
        });

        /** 电影评论*/
        function discuss() {
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/discuss",
                {"movieId":${movie.id}, "remark": $("#remark").val()},
                function (data) {
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        layer.close(index);
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            layer.close(index);
                            location.href = "<%=request.getContextPath()%>/movie/toMovieDetail/" + ${movie.id};
                        });
                })
        }

        /** 去场次 */
        function toMovieOffice(id){
            window.location.href="<%=request.getContextPath()%>/movie/toMovieOffice?id=" + ${movie.id};
        }
    </script>
    <style>
        .box{
            display: inline-block;
        }
    </style>
</head>
<body style="text-align:center;color: #dc7923">
<div id="test1"></div>
电影名称:${movie.movieName} <br/>
电影片长：${movie.longTime} <br/>
上线时间：${movie.topTime}<br/>
评分:${grade}<br/>
点赞数:${like}<br/>
<c:if test="${user.level == 0}">
    <input type='button' value='点赞' onclick='toLike("${movie.id}")'/>
    <button id="hide">隐藏</button>
    <button id="show">评论</button>
</c:if>
<c:if test="${movie.status == 1}">
    <input type = 'button' value = '查看场次' onclick = 'toMovieOffice("${movie.id}")'/><br/>
</c:if>
<c:if test="${user.level == 0}">
    电影评分:<div id="test2"></div>
</c:if>
<table align="center">
    <tr>
        <td>相关评论:</td>
    </tr>
    <tr>
        <td>用户名</td>
        <td>评论</td>
        <td>评论时间</td>
    </tr>
    <tbody id="tb"></tbody>
</table>
<div id="testDiv" style="display:none">
    评论：<input type="text" name="remark" id="remark"/>

    <input type='button' value='评论提交' onclick='discuss()'/>
</div>
</body>
</html>
