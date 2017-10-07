<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>角色分配</title>
    <meta http-equiv="pragma" content="no-cache">
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
                key: {
                    name: 'name',
                    children: 'children'
                }
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes = JSON.parse('${tree}');
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
                <div class="panel-heading">分配角色：${_user.username}
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div class="panel-footer">
                    <%--<form action="/roles/user/${_user.id}" method="post">--%>
                        <%--<input type="hidden" name="_method" value="PUT">--%>
                        <%--<input type="hidden" name="ids">--%>
                        <button type="button" class="btn btn-default pull-right" onclick="submitUpdate()">提交更改</button>
                    <%--</form>--%>
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
//        var params = $.param({"ids":checked,"_method":"PUT"},true);
//        var params = JSON.stringify(checked);
//        console.log(params);
        $.ajax({
            url: '/roles/user/${_user.id}',
            type: 'POST',
            data: {
                _method: "PUT",
                ids:checked
            },
            traditional: true,//这里设置为true
            success: function (rs) {
                if(rs==='success')
                    window.location.href="/users"
            }
        });
    }

</script>
</html>
