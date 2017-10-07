<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/2
  Time: 20:33
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
    <title>用户管理</title>
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
<%-- 导航栏 --%>
<jsp:include page="/WEB-INF/comm/navbar.jsp">
    <jsp:param value="" name="active"/>
</jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <%-- 侧边栏 --%>
            <jsp:include page="/WEB-INF/comm/leftpanel.jsp">
                <jsp:param name="homepage" value="/dept"/>
                <jsp:param name="activeName" value="USER_MANAGE"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">部门管理
                    <c:if test="${sysfeature.contains('SYS_USER_SAVE')}">
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target="#myModal" onclick="addUser()">
                            <span class="glyphicon glyphicon-plus"></span>添加
                        </button>
                    </c:if>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>员工编号</th>
                            <th>员工姓名</th>
                            <th>员工职务</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.username}</td>
                                <td>${item.dept.dname}</td>
                                <td>
                                    <c:if test="${sysfeature.contains('SYS_USER_ALLOC_ROLE')}">
                                        <a href="/roles/user/${item.id}"
                                           class="btn btn-primary btn-xs"
                                           id="alloc_btn">分配角色</a>
                                    </c:if>
                                    <c:if test="${sysfeature.contains('SYS_USER_UPDATE')}">
                                        <button class="btn btn-warning btn-xs" id="update_btn"
                                                data-toggle="modal" data-target="#myModal"
                                                onclick="getUserMsg('${item.id}')">修改
                                        </button>
                                    </c:if>
                                    <c:if test="${sysfeature.contains('SYS_USER_DELETE')}">
                                        <button class="btn btn-danger btn-xs" id="del_btn"
                                                onclick="delUser('${item.id}')">删除
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
    <input type="hidden" name="_method" value="DELETE">
</form>
<%--模态框--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="updateform" class="form-horizontal" action="" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改员工</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="_method" value="put">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工编号</label>
                        <div class="col-sm-10" id="user_container">
                            <p class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">员工名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="user_name"
                                   placeholder="员工名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="man" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="sex" id="woman" value="0"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_email" class="col-sm-2 control-label">员工邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="user_email"
                                   placeholder="员工邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_company" class="col-sm-2 control-label">公司选择</label>
                        <div class="col-sm-10">
                            <select id="user_company" class="form-control" name="company_id"
                                    onchange="changeDept(this)">
                                <option value="0">无</option>
                                <c:forEach items="${companies}" var="item">
                                    <option value="${item.id}">${item.cname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_dept" class="col-sm-2 control-label">部门选择</label>
                        <div class="col-sm-10">
                            <select id="user_dept" class="form-control" name="deptno">
                                <option value="0"></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否可用</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="enabled" id="enabled_true" value="1"> 可用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="enabled" id="enabled_false" value="0"> 禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否锁定</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="locked" id="locked_true" value="1"> 锁定
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="locked" id="locked_false" value="0"> 未锁定
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_description" class="col-sm-2 control-label">员工描述</label>
                        <div class="col-sm-10">
                            <textarea name="description" class="form-control"
                                      id="user_description"
                                      placeholder="部门描述"></textarea>
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
<script>
    function delUser(id) {
        $("#delform").attr("action", "/user/" + id);
        $("#delform").submit();
    }

    function changeDept(obj) {
        getDeptByComId(0, $(obj).val());
    }

    function addUser() {
        $('#user_container').children('p').text('');
        $('#user_name').val('');
        $('sex').eq(0).attr('checked', true);
        $('#user_email').val('');
        $('#user_company').val('');
        getDeptByComId(rs.dept.id, rs.company.id);
        $('enabled').eq(0).attr('checked', true);
        $('locked').eq(0).attr('checked', true);
        $("#user_description").val('');
    }

    function getUserMsg(uid) {
        $.ajax({
            url: '/user/' + uid,
            type: 'get',
            dataType: 'json',
            success: function (rs) {
                if (rs) {
                    $('#user_container').children('p').text(rs.id);
                    $('#user_name').val(rs.username);
                    $('input:radio[name="sex"][value="' + rs.sex + '"]').attr('checked', true);
                    $('#user_email').val(rs.email);
                    $('#user_company').val(rs.company.id);
                    getDeptByComId(rs.dept.id, rs.company.id);
                    $('input:radio[name="enabled"][value="' + rs.enabled + '"]').attr('checked', true);
                    $('input:radio[name="locked"][value="' + rs.locked + '"]').attr('checked', true);
                    $("#user_description").val(rs.description);

                    $("#updateform").attr("action", "/user/" + uid);
                    $("#updateform input[type='hidden']").val("PUT")
                }
            }
        });
    }
    function getDeptByComId(deptno, comId) {
        $.ajax({
            url: '/dept/company/' + comId,
            type: 'get',
            dataType: 'json',
            success: function (rs) {
                $("#user_dept").html('');
                $("#user_dept").append('<option value="0">无</option>');
                $.each(rs, function (i, item) {
                    $("#user_dept").append('<option value="' + item.id + '">' + item.dname + '</option>')
                });
                $("#user_dept").val(deptno);
            }
        });
    }
</script>
</body>
</html>
