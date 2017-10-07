<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/4
  Time: 0:44
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
    <title>角色管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
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
                <div class="panel-heading">角色管理
                    <c:if test="${sysfeature.contains('SYS_ROLE_SAVE')}">
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target="#myModal" onclick="addRole()">
                            <span class="glyphicon glyphicon-plus"></span>添加
                        </button>
                    </c:if>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <tr>
                            <th>职务编号</th>
                            <th>职务名称</th>
                            <th>职务操作</th>
                        </tr>
                        <c:forEach items="${roles}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>
                                    <c:if test="${sysfeature.contains('SYS_ROLE_ALLOC_RESOURCE')}">
                                        <a class="btn btn-primary btn-xs"
                                           href="/resource/role/${item.id}">分配资源</a>
                                    </c:if>
                                    <c:if test="${sysfeature.contains('SYS_ROLE_UPDATE')}">
                                        <button class="btn btn-warning btn-xs"
                                                data-toggle="modal" data-target="#myModal"
                                                data-id="${item.id}"
                                                onclick="updateRole('${item.id}','${item.dept.id}','${item.company.id}')">
                                            修改
                                        </button>
                                    </c:if>
                                    <c:if test="${sysfeature.contains('SYS_ROLE_DELETE')}">
                                        <button class="btn btn-danger btn-xs"
                                                onclick="delRole('${item.id}')">删除
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="delform" method="post">
    <input type="hidden" name="_method" value="delete">
</form>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="updateform" class="form-horizontal" action="" method="post">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改角色</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="_method" value="put">
                    <div class="form-group">
                        <label for="role_name" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="role_name"
                                   placeholder="角色名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="role_constant" class="col-sm-2 control-label">唯一标识符</label>
                        <div class="col-sm-10">
                            <input type="text" name="constant" class="form-control"
                                   id="role_constant"
                                   placeholder="唯一标识符">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司名称</label>
                        <div class="col-sm-10" id="company_container">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门名称</label>
                        <div class="col-sm-10" id="dept_container">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script>
    function updateRole(id, deptno, cid) {
        clearIntput();
        $.ajax({
            url: '/role/' + id,
            type: 'get',
            dataType: 'json',
            success: function (rs) {
                $('#role_name').val(rs.name);
                $('#role_constant').val(rs.roleConstant.name);
                $('#company_container p.form-control-static').text(rs.company.cname);
                $('#dept_container p.form-control-static').text(rs.dept.dname);
                $("#updateform").attr('action', '/role/' + id);
                $("#updateform input[type='hidden']").val("put")
            }
        });
    }

    function clearIntput() {
        $('.modal').find('input').val('');
        $('.modal').find('p.form-control-static').text();
    }

    function delRole(id) {
        $("#delform").attr("action", "/role/" + id);
        $("#delform").submit();
    }
</script>
</html>
