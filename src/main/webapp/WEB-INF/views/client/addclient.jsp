<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/10/6
  Time: 19:39
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
    <title>添加客户</title>
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
                </div>
                <div class="panel-body">
                    <div class="col-md-8 col-md-offset-2">
                        <form action="/client" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_name">客户名称</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="name"
                                           id="client_name">
                                </div>
                            </div>
                            <%--法人代表--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="client_legal">法人代表</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="legal"
                                           id="client_legal">
                                </div>
                            </div>
                            <%--省市县--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_name">地区</label>
                                <div class="col-sm-10">
                                    <div class="form-inline">
                                        <label for="client_province">省</label>
                                        <select class="form-control" style="width: auto;"
                                                name="cities"
                                                id="client_province"
                                                onchange="getCityChildrenByParent(this)">
                                            <c:forEach items="${province}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="client_city">市</label>
                                        <select class="form-control" style="width: auto;"
                                                name="cities"
                                                id="client_city"
                                                onchange="getCityChildrenByParent(this)">
                                            <option value="0">--请选择--</option>
                                        </select>
                                        <label for="client_county">县</label>
                                        <select class="form-control" style="width: auto;"
                                                name="cities"
                                                id="client_county">
                                            <option value="0">--请选择--</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <%--邮编--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="client_postcode">邮编</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="postcode"
                                           id="client_postcode">
                                </div>
                            </div>
                            <%--传真--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="client_fax">传真</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="fax"
                                           id="client_fax">
                                </div>
                            </div>
                            <%--座机--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_telphone">座机</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="telphone"
                                           id="client_telphone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_phone">电话</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="phone"
                                           id="client_phone">
                                </div>
                            </div>
                            <%--邮箱--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_email">邮箱</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="email"
                                           id="client_email">
                                </div>
                            </div>
                            <%--网址--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_url">网址</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="url"
                                           id="client_url">
                                </div>
                            </div>
                            <%--下次联系时间--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"
                                       for="client_next">下次联系时间</label>
                                <div class="col-sm-10">
                                    <input class="form_datetime form-control" type="text"
                                           name="nextTime"
                                           id="client_next">
                                </div>
                            </div>
                            <%--等级--%>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="client_level">等级</label>
                                <div class="col-sm-10">
                                    <select class="form-control" style="width: auto;" name="level"
                                            id="client_level">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
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
        $("#client_register").datetimepicker({minView: "month", format: 'yyyy-mm-dd'});
        $("#client_next").datetimepicker({minView: "month", format: 'yyyy-mm-dd'})
    });

    function getCityChildrenByParent(obj) {
        var $this = $(obj);
        var parentId = $this.val();
        if (parentId === 0) {
            return;
        }
        getCityChildren(parentId, $this)
    }

    function getCityChildren(pid, $parent, val) {
        $.ajax({
            url: "/cities/" + pid,
            type: "get",
            dataType: "json",
            success: function (rs) {
                console.log(rs);
                if (!rs)
                    return;
                var $child = $parent.nextAll("select").eq(0);
                $child.html('');
                $child.append('<option value="0">--请选择--</option>');
                $.each(rs, function (i, item) {
                    $child.append('<option value="' + item.id + '">' + item.name + '</option>')
                });
                $child.val(0);
                if (val)
                    val();
            }
        });
    }
</script>
</html>
