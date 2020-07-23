<%--
  Created by IntelliJ IDEA.
  User: CYS
  Date: 2020/7/7
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            search();
        })
        function search() {
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/show",
                {"page":$("#page").val()},
                $("#fm").serialize(),
                function (data) {
                    if (data.code != 200) {
                        layer.alert(data.msg);
                    }
                    layer.close(index);
                    var html = "";
                    var movieList = data.data;
                    for (var i = 0; i < movieList.length; i++) {
                        var movie = movieList[i];
                        html += "<tr>";
                        html += "<td>" + movie.movieName + "</td>";
                        html += "<td>" + movie.actorName + "</td>";
                        html += "<td>" + movie.baseName + "</td>";
                        html += "<td>" + movie.longTime + "</td>";
                        html += "<td>" + movie.topTime + "</td>";
                        html += "</tr>";
                    }
                    $("#tbd").html(html);
                });
        }

        function myMovie(){
            location.href = "<%=request.getContextPath()%>/movie/toShow"
        }
        function upd(id) {
            location.href = "<%=request.getContextPath()%>/user/toUpd?id=" + id;

        }

        function del(id) {
            $.post("<%=request.getContextPath()%>/user/delete",
                {"id": id},
                function (data) {
                    if (data.code != 200) {
                        alert(data.msg);
                        return;
                    }
                    location.href = "<%=request.getContextPath()%>/user/toShow";
                })
        }

        function selectMovie() {
            $("#pageNo").val(1);
            search();
        }

        function page(temp, pages) {
            var page = $("#pageNo").val();
            if (temp == 0) {
                if (parseInt(page) - 1 < 1) {
                    alert("已是首页");
                    return;
                }
                $("#pageNo").val(parseInt(page) - 1);
            }
            if (temp == 1) {
                if (parseInt(page) + 1 > pages) {
                    alert("已经尾页了");
                    return;
                }
                $("#pageNo").val(parseInt(page) + 1);
            }
            search();
        }
    </script>

</head>
<input type="button" value="我的影票" onclick="myMovie()"/>
<body style="text-align:center">
<form id="fm">
    <input type="hidden" name="pageNo" value="1" id="pageNo"/>
    电影名称：<input type="text" name="movieName"/><br/>
    类型：
    战争<input type="checkbox" name="baseName" value="2"/>
    武侠<input type="checkbox" name="baseName" value="3"/>
    动漫<input type="checkbox" name="baseName" value="4"/>
    科幻<input type="checkbox" name="baseName" value="5"/>
    悬疑<input type="checkbox" name="baseName" value="6"/>
    恐怖<input type="checkbox" name="baseName" value="7"/>
    爱情<input type="checkbox" name="baseName" value="8"/>
    <input type="button" value="查询" onclick="selectMovie()">
</form>
<table align="center">
    <tr>
        <th>电影名称</th>
        <th>演员名单</th>
        <th>电影类型</th>
        <th>电影片长</th>
        <th>上线时间</th>
    </tr>
<tbody id="tbd">
    </tbody>
</table>
<div id="pageDiv">
</div>
</body>
</html>
