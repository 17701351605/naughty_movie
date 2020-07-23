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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        function search() {
            $.post("<%=request.getContextPath()%>/movieEvaluat/evaluatShow",
                {},
                function (data) {
                    var html = "";
                    var movie = data.data;
                    html += "<tr>";
                    html += "<td>" + movie.id + "</td>";
                    html += "<td>" + movie.movieName + "</td>";
                    html += "<td>" + movie.topTime + "</td>";
                    html += "<td>";
                    html += "<input type = 'button' value = '点赞' onclick = 'toLike(" + movie.id + ")'/>"
                    html += "</td>";
                    html += "</tr>";
                    $("#tb").html(html);
                });
        }
    </script>
</head>
<body style="text-align: center">
<table border="1px" cellpadding="20px" cellspacing="0px" align="center">
    <tr>
        <td>id</td>
        <td>电影名称</td>
        <td>上线时间</td>
        <td>操作</td>
    </tr>
    <tbody id="tb"></tbody>
</table>
</body>
</html>
