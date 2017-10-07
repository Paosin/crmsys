<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/6
  Time: 23:02
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
    <title>联系人列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-datetimepicker.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/comm/navbar.jsp">
    <jsp:param value="" name="active"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="btn">客户管理</span>
                    <form style="display: inline;" action="/linkman/add" method="get">
                        <c:if test="${not empty client}">
                            <input type="hidden" name="clientId" value="${client.id}">
                        </c:if>
                        <button type="submit" class="btn btn-primary pull-right"
                                style="margin-right: 8px;">
                            <span class="glyphicon glyphicon-plus"></span>添加联系人
                        </button>
                    </form>
                    <button class="btn btn-danger pull-right" style="margin-right: 8px;"
                            onclick="delAll()">删除全部
                    </button>
                    <button class="btn btn-warning pull-right" style="margin-right: 8px;">导出excel
                    </button>
                </div>
                <div class="panel-body">
                    <form id="delAllForm" action="/linkmen" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <table class="table table-bordered">
                            <tr>
                                <th><label>
                                    <input type="checkbox" id="all" onclick="checkAll(this)">
                                </label></th>
                                <th>联系人编码</th>
                                <th>联系人名称</th>
                                <th>性别</th>
                                <th>生日</th>
                                <th>职务</th>
                                <th>是否主动</th>
                                <th>电话</th>
                                <th>邮箱</th>
                                <th>备注</th>
                                <th>对应客户</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${linkmen}" var="item">
                                <tr>
                                    <td><label>
                                        <input type="checkbox" name="ids" value="${item.id}">
                                    </label></td>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.gender==1?'男':'女'}</td>
                                    <td>${item.birthday}</td>
                                    <td>${item.job}</td>
                                    <td>${item.active==1?'是':'否'}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.email}</td>
                                    <td>${item.content}</td>
                                    <td>${item.client.name}</td>
                                    <td>
                                        <button class="btn btn-warning btn-xs"
                                                type="button"
                                                data-toggle="modal" data-target="#myModal"
                                                onclick="update('${item.id}')">修改
                                        </button>
                                        <button class="btn btn-danger btn-xs"
                                                type="button"
                                                onclick="del('${item.id}')">
                                            删除
                                        </button>
                                        <a href="javascript:void(0)"
                                           class="btn btn-primary btn-xs">联系记录</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
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
                <input type="hidden" name="_method" value="put">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改联系人</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="linkman_name">联系人名称</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="name" id="linkman_name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="linkman_man" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="linkman_woman" value="0"> 女
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="linkman_birthday">生日</label>
                        <div class="col-sm-10">
                            <input class="form_datetime form-control" type="text"
                                   name="birthday"
                                   id="linkman_birthday">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="linkman_job">职务</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="job" id="linkman_job">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否为主动联系人</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="active" id="linkman_active" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="active" id="linkman_no_active" value="0">
                                否
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="linkman_phone">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="phone" id="linkman_phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="linkman_email">邮箱</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="email" id="linkman_email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="user_content" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea name="content" class="form-control"
                                      id="user_content"
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
</body>

<script>

    function del(id) {
        $("#delform").attr("action", "/linkman/" + id);
        $("#delform").submit();
    }

    function update(id) {
        $.ajax({
            url: "/linkman/json/" + id
        })
    }

    function checkAll(obj) {
        $(".table.table-bordered :checkbox").prop("checked", obj.checked);
    }

    function delAll() {
        $("#delAllForm").submit();
    }
</script>
</html>
