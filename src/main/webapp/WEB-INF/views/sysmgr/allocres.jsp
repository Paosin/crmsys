<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/4
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>资源分配</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/plugins/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

    <script>
        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            check: {
                enable: true,
                chkDisabledInherit: true
            },
            data: {
                simpleData:{
                    enable:true,
                    idKey:'id',
                    pIdKey:'parent',
                    rootPid:0
                }
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes = JSON.parse('${resources}');
        $(document).ready(function () {
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            zTreeObj.expandAll(true);
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/comm/navbar.jsp">
    <jsp:param value="" name="active"/>
</jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="/WEB-INF/comm/leftpanel.jsp">
                <jsp:param name="homepage" value="/dept"/>
                <jsp:param name="activeName" value="ROLE_MANAGE"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">分配资源：${_role.name}
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div class="panel-footer">
                    <button type="button" class="btn btn-default pull-right" onclick="submitUpdate()">提交更改</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function submitUpdate(){
        var nodes = zTreeObj.getCheckedNodes(true);
        //将节点数据提交到后台
        var checked = [];
        $.each(nodes,function(i,o){
            checked.push(o.id);
        });
        // 序列化参数
        // 第二个参数就是  去掉序列化后的参数中的中括号
        $.ajax({
            url: '/resource/role/${_role.id}',
            type: 'POST',
            data: {
                _method: "PUT",
                ids:checked
            },
            traditional: true,//这里设置为true
            success: function (rs) {
                if(rs==='success')
                    window.location.href="/resource/role/${_role.id}"
            }
        });
    }
</script>
</html>
