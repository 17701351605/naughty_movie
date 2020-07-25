<%--
  Created by IntelliJ IDEA.
  User: Huangwk
  Date: 2020/7/23
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
   <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/layui.all.js"></script>--%>
    <script type="text/javascript">
        $(function(){
            show();
        });

        function show(){
            var index = layer.load(1, {shade: 0.2});

            $.post("<%=request.getContextPath()%>/userOrder/list",
                {"page":$("#page").val(),"a":1},
                function(data){
                    layer.close(index);
                    var html = "";
                    var pageHtml = "";
                    for ( var i=0;i < data.data.list.length; i++) {
                        var m = data.data.list[i];
                        html += "<tr>"
                        html += "<td>"+m.id+"</td>"
                        html += "<td>"+m.userName+"</td>"
                        html += "<td>"+m.movieName+"</td>"
                        html += "<td>"+m.prices+"</td>"
                        html += "<td>"+m.buyNumber+"</td>"
                        html += "<td>"+m.createTime+"</td>"
                        if (m.status == 1) {
                            html += "<td><input type = 'button' value = '退票' onclick='del("+m.id+")'/></td>"
                        } else {
                            if (m.status ==2) {
                                html += "<td>已退票</td>"
                            }
                        }
                        html += "</tr>"
                    }
                    $("#tbd").html(html);
                    pageHtml += "<input type = 'button' value = '上一页' onclick = page(0,"+data.data.pages+")>"
                    pageHtml += "<input type = 'button' value = '下一页' onclick = page(1,"+data.data.pages+")>"
                    $("#pageDiv").html(pageHtml);
                });
        }
        //分页
        function page(s,pages){
            var pa =$("#page").val();
            if(s == 0){
                if((parseInt(pa) - 1) < 1){
                    layer.msg('已到首页',{icon : 5,time:2000});
                    return;
                }
                $("#page").val(parseInt(pa) - 1);
            }
            if(s == 1){
                if(parseInt(pa) + 1 > pages){
                    layer.msg('没有下一页了',{icon : 5,time:2000});
                    return;
                }
                $("#page").val(parseInt(pa) + 1);
            }
            show();
        }
        //退票
        function del(id){
            var index = layer.load(2, {shade: 0.3});
            $.post("<%=request.getContextPath()%>/userorder/del",
                {"id":id},
                function(data){
                    if(data.code != 200){
                        layer.msg(data.msg, {icon: 5});
                        layer.close(index);
                        show();
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            layer.close(index);
                            show();
                        });
                });
        }
    </script>
</head>
<body style="text-align: center">
<input type = "hidden" value = "1" id = "page"/>
<table border="1px" cellpadding="20px" cellspacing="0px" align="center">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <%--<th>电影Id</th>--%>
        <th>电影名</th>
        <th>价格</th>
        <th>购买数量</th>
        <th>购买时间</th>
        <th>操作</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
<div id="pageDiv"></div>
</body>
</html>
