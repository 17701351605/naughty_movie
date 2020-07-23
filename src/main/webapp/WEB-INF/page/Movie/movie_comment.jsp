<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/7/23
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">

        $(function(){
            search();
        })

        function search() {
            $.post("<%=request.getContextPath()%>/movie/evaluatShow",
                {},
                function (data) {
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var movie = data.data[i];
                        html += "<tr>";
                        html += "<td>" + movie.id + "</td>";
                        html += "<td>" + movie.remark + "</td>";
                        html += "<td>" + movie.createTime + "</td>";
                        html += "</tr>";
                    }
                    $("#tb").html(html);
                });
        }

        /** 点赞 */
        function toLike(id){
            $.post("<%=request.getContextPath()%>/movie/toLike/"+1,
                {},
                function(data){
                    if(data.code != 200){
                        alert(data.msg);
                        return;
                    }
                    alert(data.msg);
                    return;
                })
        }

        $(document).ready(function(){
            $("#show").click(function(){
                $("#testDiv").show();
            });
            $("#hide").click(function(){
                $("#testDiv").hide();
            });

        })
    </script>
</head>
<body style="text-align: center">
<table  align="center">
    电影名称:${movie.movieName} <br/>
    电影片长：${movie.longTime} <br/>
    上线时间：${movie.topTime}<br/>
    <input type = 'button' value = '点赞' onclick = 'toLike(" + movie.id + ")'/>

    <button id="hide">隐藏</button>
    <button id="show">评论</button><br/>

    <tr>
        <td>相关评论:</td>
    </tr>
    <tbody id="tb"></tbody>
</table>
<div id="testDiv" style="display:none">
    评论：<input type="text" name="remark" id="remark"/>
    <input type='button' value='评论提交' onclick='discuss()'/>
</div>
</body>
</html>
