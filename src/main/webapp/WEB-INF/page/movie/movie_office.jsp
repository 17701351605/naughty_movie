<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/7/23
  Time: 11:20
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
            $.post("<%=request.getContextPath()%>/movie/movieOfficeShow",
                $("#fm").serialize(),
                function (data) {
                    var html = "";
                    for (var i = 0; i < data.data.length; i++) {
                        var m = data.data[i];
                        html += "<tr>";
                        html += "<td>" + m.id + "</td>";
                        html += "<td>" + m.playHall + "</td>";
                        html += "<td>" + m.movieName + "</td>";
                        html += "<td>" + m.playHall + "</td>";
                        html += "<td>" + m.price + "</td>";
                        html += "<td>" + m.seating + "</td>";
                        html += "<td>" + m.startTime + "</td>";
                        html += "<td>";
                        html += "</td>";
                        html += "</tr>";
                    }
                    $("#tb").html(html);
                });
        }
    </script>
</head>
<body style="text-align: center">
<form id="fm">
    场次查询：<input type="text" name="startingTime"/>~<input type="text" name="endTime"/><br/>
    <input type="button" value="查询" onclick="search()"/>
</form>
<table border="1px" cellpadding=20px" cellspacing="0px" align="center">
    <tr>
        <td>id</td>
        <td>电影ID</td>
        <td>影院播放厅</td>
        <td>电影开始时间</td>
        <td>电影票单价</td>
        <td>电影剩余座位</td>
        <td>操作</td>
    </tr>
    <tbody id="tb"></tbody>
</table>
</body>
</html>
