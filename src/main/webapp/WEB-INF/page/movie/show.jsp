<%--
  Created by IntelliJ IDEA.
  User: CYS
  Date: 2020/7/7
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/static/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/static/css/demo.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">
        $(function(){
            search();
        })
        function search() {
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/show",
                $("#fm").serialize(),
                function (data) {
                    if (data.code != 200) {
                        layer.alert(data.msg);
                    }
                    layer.close(index);
                    var html = "";
                    var pageHtml = "";
                    var movieList = data.data.movieList;
                    for (var i = 0; i < movieList.length; i++) {
                        var movie = movieList[i];
                        html += "<tr>";
                        html += "<td>" + movie.movieName + "</td>";
                        html += "<td>" + movie.actorName + "</td>";
                        html += "<td>" + movie.baseName + "</td>";
                        html += "<td>" + movie.longTime +"分钟"+"</td>";
                        html += "<td>" + movie.topTime + "</td>";
                        html +=	"<td>";
                        html += "<input type = 'button' value = '电影详情' onclick = 'toComment("+movie.id +")'/>";
                        if (${user.level == 1}) {
                            html += "<input type = 'button' value = '修改' onclick = 'update("+movie.id +")'/>";
                            /*html += "<input type = 'button' value = '删除' onclick = 'del("+movie.id +")'/>";*/
                            if (movie.status==1) {
                                html += "<input type = 'button' value = '下架' onclick = 'statuss(0,"+movie.id +")'/>";
                            }else{
                                html += "<input type = 'button' value = '上架' onclick = 'statuss(1,"+movie.id +")'/>";
                            }
                        }
                        /*html += "<input type = 'button' value = '电影详情' onclick = 'toComment("+"'"+movie.movieId+"'"+")'/>";*/
                        html += "</td>";
                        html += "</tr>";
                    }
                    $("#tbd").html(html);
                    pageHtml += "<input type = 'button' value = '上一页' onclick = 'page(0," + data.data.pages + ")'/>";
                    pageHtml += "<input type = 'button' value = '下一页' onclick = 'page(1," + data.data.pages + ")'/>";
                    $("#pageDiv").html(pageHtml);
                });
            }

        //跳电影详情展示
        function toComment(id) {
            location.href = "<%=request.getContextPath()%>/movie/toMovieDetail/" + id;
        }

        /*//删除
        function del(id){
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/del",
                {"id":id,"isDel":0},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        layer.close(index);
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            window.location.href="<%=request.getContextPath()%>/movie/toMovieShow";
                            layer.close(index);
                        });
                });
        }*/

        //修改
        function update(id) {
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                shade: 0.8,
                area: ['500px', '53%'],
                content:"<%=request.getContextPath()%>/movie/toUpdate/"+id,
            });
        }
        //上下架
        function statuss(status, id){
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/updateMovie",
                {"id":id,"status":status},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        layer.close(index);
                        return;
                    }
                    var msg= "";
                    if (status == 0) {
                        msg="下架成功"
                    }else{
                        msg="上架成功"
                    }
                    layer.msg(msg, {icon: 6, time: 2000},
                        function(){
                            window.location.href="<%=request.getContextPath()%>/movie/toMovieShow";
                            layer.close(index);
                        });
                });
        }
        //添加
        function addMovie() {
            layer.open({
                type: 2,
                title: '添加',
                shadeClose: true,
                shade: 0.8,
                area: ['500px', '53%'],
                content:"<%=request.getContextPath()%>/movie/toAdd",
            });
        }
        /*//跳用户订单展示
        function myMovie(){
            location.href = "<%=request.getContextPath()%>/userOrder/toShow"
        }*/


        function selectMovie() {
            $("#pageNo").val(1);
            search();
        }

        //分页
        function page(temp, pages) {
            var page = $("#pageNo").val();
            if (temp == 0) {
                if (parseInt(page) - 1 < 1) {
                    layer.msg("已是首页",{icon : 7,time:2000});
                    return;
                }
                $("#pageNo").val(parseInt(page) - 1);
            }
            if (temp == 1) {
                if (parseInt(page) + 1 > pages) {
                    layer.msg("已经尾页了", {icon : 5,time:2000});
                    return;
                }
                $("#pageNo").val(parseInt(page) + 1);
            }
            search();
        }

    </script>
</head>
<%--<input type="button" value="我的影票" onclick="myMovie()"/>--%>
<body style="text-align:center;color: #BEE9F0">
<form id="fm">
    <c:if test="${user.level == 1}">
        <input type="button" value='增加电影' onclick='addMovie()'/><br/>
    </c:if>
    <input type="hidden" name="pageNo" value="1" id="pageNo"/>
    <input type="hidden" name="status" value="1" />
    电影名称：<input type="text" name="movieName"/><br/>
    类型：
    <c:forEach items="${list}" var="a">
        ${a.baseName} <input type="checkbox" name="movieType" value="${a.id}" />
    </c:forEach>
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
