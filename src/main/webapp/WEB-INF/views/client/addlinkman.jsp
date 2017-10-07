<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/6
  Time: 23:25
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
    <title>添加联系人</title>
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
                <div class="panel-heading"><span class="btn">添加联系人</span>
                </div>
                <div class="panel-body">
                    <div class="col-md-8 col-md-offset-2">
                        <form id="addform" class="form-horizontal" action="/linkman" method="post">
                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="linkman_name">联系人名称</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="name"
                                           id="linkman_name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-10">
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="linkman_man"
                                               value="1" checked> 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="gender" id="linkman_woman"
                                               value="0">
                                        女
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="linkman_birthday">生日</label>
                                <div class="col-sm-10">
                                    <input class="form_datetime form-control" type="text"
                                           name="birthday"
                                           id="linkman_birthday">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="linkman_job">职务</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="job"
                                           id="linkman_job">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否为主动联系人</label>
                                <div class="col-sm-10">
                                    <label class="radio-inline">
                                        <input type="radio" name="active" id="linkman_active"
                                               value="1" checked>
                                        是
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="active" id="linkman_no_active"
                                               value="0">
                                        否
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="linkman_phone">电话</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="phone"
                                           id="linkman_phone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="linkman_email">邮箱</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="email"
                                           id="linkman_email">
                                </div>
                            </div>
                            <%--所属公司--%>
                            <%--如果当前不是从客户页选择而来，则显示可选择的下拉列表--%>
                            <c:choose>
                                <c:when test="${empty client}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"
                                               for="client_level">所属客户</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" style="width: auto;"
                                                    name="clientId"
                                                    id="client_level">
                                                <c:forEach items="${clients}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:when>
                                <%--否则增加一个隐藏域，用于传递当前作用域的客户 id--%>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label" for="linkman_clientId">所属客户</label>
                                        <div class="col-sm-10">
                                            <input class="form-control" type="text"
                                                   id="linkman_clientId" value="${client.name}" disabled>
                                        </div>
                                    </div>
                                    <input type="hidden" name="clientId" value="${client.id}">
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label for="user_content" class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                            <textarea name="content" class="form-control"
                                      id="user_content"
                                      placeholder="部门描述"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#linkman_birthday").datetimepicker({minView: "month", format: 'yyyy-mm-dd'});
    });
</script>
</html>
