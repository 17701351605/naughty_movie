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
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
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
                        html += "<td>" + m.movieName + "</td>";
                        if (m.playHall ==10 ){
                            html += "<td>一号播放厅</td>";
                        } else if (m.playHall ==11){
                            html += "<td>二号播放厅</td>";
                        } else {
                            html += "<td>三号播放厅</td>";
                        }
                        html += "<td>" + m.price + "</td>";
                        html += "<td>" + m.seating + "</td>";
                        html += "<td>" + m.startTime + "</td>";
                        html += "<td>";
                        //管理员 登录
                       if(${user.level == 1}) {
                            html += "<input type = 'button' value = '修改' onclick = 'update("+m.id+")'/>";
                            html += "<input type = 'button' value = '删除' onclick = 'del("+m.id+")'/>";
                        }
                        /**登录人为用户显示购买和团购*/
                        if (${user.level ==0}){
                            html +="<input type='button' onclick='buyTicket(" +m.id+ ","+m.seating+")' value='购票' />";
                            /** 剩余票数和总票数相等  显示团购按钮 */
                            if (m.seating == 50){
                                html += "<input type='button' onclick='tuanGou(" +m.id+ ")' value='团购' />";
                            }
                        }
                        html += "</td>";
                        html += "</tr>";
                    }
                    $("#tb").html(html);
                });
        }

        /** id 播放厅的主键id */
        function tuanGou(id) {
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/userOrder/tuanGou/"+id,
                {},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        layer.close(index);
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            window.location.href="<%=request.getContextPath()%>/movie/toMovieOffice?id="+${movieId};
                            layer.close(index);
                        });
                });
        }

        /** id 播放厅的主键id */
        function buyTicket(id, seating){
            if (seating == 0){
                layer.msg("票以售罄", {icon: 5, time: 2000});
                return;
            }
            window.location.href="<%=request.getContextPath()%>/userOrder/toByTicket/"+id;
        }

        //删除
        function del(id){
            var index = layer.load(1, {shade: 0.2});
            $.post("<%=request.getContextPath()%>/movie/delOffice",
                {"id":id,"isDel":0},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        layer.close(index);
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            window.location.href="<%=request.getContextPath()%>/movie/toMovieOffice?id="+${movieId};
                            layer.close(index);
                        });
                });
        }
        //修改
        function update(id) {
            var index = layer.load(0,{shade:0.3});
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                shade: 0.4,
                area: ['480px', '90%'],
                content:"<%=request.getContextPath()%>/movie/toUpdateById/"+id ,
            });
            layer.close(index);
        }
        /** 添加播放场次*/
        function add() {
            var index = layer.load(0,{shade:0.3});
            layer.open({
                type: 2,
                title: '添加',
                shadeClose: true,
                shade: 0.4,
                area: ['500px', '80%'],
                content: '<%=request.getContextPath()%>/movie/toMovieOfficeAdd?id='+${movieId}
            });
            layer.close(index);
        }
    </script>
</head>
<body style="text-align: center">
<form id="fm">
    <input type="hidden" name="movieId" value="${movieId}"/>
    <c:if test="${user.level == 1}">
        <input type="button" value="增加" onclick="add()"/><br/><br/>
    </c:if>
    场次查询：
    开始时间：
    <input class="Wdate" type="text" name="startingTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" >
~
    <input class="Wdate" type="text" name="endTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d+1}'})" ><br>

    <input type="button" value="查询" onclick="search()"/>
</form>
<table border="1px" cellpadding=20px" cellspacing="0px" align="center">
    <tr>
        <td>电影名称</td>
        <td>影院播放厅</td>
        <td>电影票单价</td>
        <td>电影剩余座位</td>
        <td>电影开始时间</td>
        <td>操作</td>
    </tr>
    <tbody id="tb"></tbody>
</table>
</body>
</html>
