<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>部门管理</title>
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
                <jsp:param name="activeName" value="DEPT_MANAGE"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">部门管理
                    <c:if test="${sysfeature.contains('SYS_DEPT_SAVE')}">
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target="#myModal" onclick="addDept()">
                            <span class="glyphicon glyphicon-plus"></span>添加
                        </button>
                    </c:if>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>部门编号</th>
                            <th>部门名称</th>
                            <th>部门表述</th>
                            <th>所属公司</th>
                            <%--<c:if test="${depts}">--%>
                            <th>操作</th>
                            <%--</c:if>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${depts}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td><a href="/users/dept/${item.id}">${item.dname}</a></td>
                                <td>${item.description}</td>
                                <td>${company.cname}</td>
                                <td>
                                    <c:if test="${sysfeature.contains('SYS_DEPT_UPDATE')}">
                                        <button class="btn btn-warning btn-xs" id="update_btn"
                                                data-toggle="modal" data-target="#myModal"
                                                data-id="${item.id}" data-name="${company.cname}"
                                                onclick="getDeptMsg(this)">修改
                                        </button>
                                    </c:if>
                                    <c:if test="${sysfeature.contains('SYS_DEPT_DELETE')}">
                                        <button class="btn btn-danger btn-xs" id="del_btn"
                                                onclick="delDept('${item.id}')">删除
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
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
                    <h4 class="modal-title" id="myModalLabel">修改部门</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="_method" value="put">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司名称</label>
                        <div class="col-sm-10" id="company_container">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_name" class="col-sm-2 control-label">部门名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="dname" class="form-control" id="dept_name"
                                   placeholder="部门名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept_description" class="col-sm-2 control-label">部门描述</label>
                        <div class="col-sm-10">
                            <textarea name="description" class="form-control"
                                      id="dept_description"
                                      placeholder="部门描述"></textarea>
                            <%--<input type="text" name="description" class="form-control"--%>
                            <%--id="dept_description"--%>
                            <%--placeholder="部门描述">--%>
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
    function delDept(id) {
        $("#delform").attr("action", "/dept/${sessionScope.user.id}/" + id);
        $("#delform").submit();
    }

    function addDept() {
        $("#company_container").prev().text("");
        $("#company_container").children().eq(0).text("");
        $("#dept_name").val("");
        $("#dept_description").val("");
        $("#company_container").prev().text("公司名称");
        $("#company_container").children().eq(0).text("康师傅");
        $("#updateform").attr("action", "/dept/${sessionScope.user.id}");
        $("#updateform input[type='hidden']").val("")
    }

    function getDeptMsg(obj) {
        $("#company_container").prev().text("");
        $("#company_container").children().eq(0).text("");
        var $deptno = $(obj).attr("data-id");
        var url = "/dept/${sessionScope.user.id}/" + $deptno;
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            success: function (rs) {
                $("#company_container").prev().text("部门编号");
                $("#company_container").children().eq(0).text($deptno);
                $("#dept_name").val(rs.dname);
                $("#dept_description").val(rs.description);
                $("#updateform").attr("action", "/dept/${sessionScope.user.id}/" + $deptno);
                $("#updateform input[type='hidden']").val("PUT")
            }
        });
    }
</script>
</html>
