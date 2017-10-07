<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/9/29
  Time: 9:48
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
    <title>客户</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/lodash.js"></script>
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
                    <div class="form-inline pull-right">
                        <div class="form-group">
                            <select class="form-control" style="width: auto;" name="condition"
                                    id="condition">
                                <option value="0">客户名</option>
                                <option value="1">城市</option>
                                <option value="2">法人代表</option>
                            </select>
                            <div class="input-group">
                                <div class="input-group-addon"><span
                                        class="glyphicon glyphicon-search"></span></div>
                                <input type="text" class="form-control" id="search"
                                       placeholder="搜索">
                            </div>
                        </div>
                    </div>
                    <a href="/client/add" class="btn btn-primary pull-right"
                       style="margin-right: 8px;">
                        <span class="glyphicon glyphicon-plus"></span>添加
                    </a>
                    <button class="btn btn-danger pull-right" style="margin-right: 8px;"
                            onclick="delAll()">删除全部
                    </button>
                    <button class="btn btn-warning pull-right" style="margin-right: 8px;">导出excel
                    </button>

                </div>
                <div class="panel-body">
                    <form id="delAllForm" action="/clients" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <table class="table table-bordered">
                            <tr>
                                <th><label>
                                    <input type="checkbox" id="all" onclick="checkAll(this)">
                                </label></th>
                                <th>客户编码</th>
                                <th>客户名称</th>
                                <th>法人代表</th>
                                <th>城市</th>
                                <th>电话</th>
                                <th>登记时间</th>
                                <th>下次联系时间</th>
                                <th>等级</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${clients}" var="item">
                                <tr>
                                    <td><label>
                                        <input type="checkbox" name="ids" value="${item.id}">
                                    </label></td>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.legal}</td>
                                    <td>${item.city.name}</td>
                                    <td>${item.telphone}</td>
                                    <td>${item.registerDate}</td>
                                    <td>${item.nextTime}</td>
                                    <td>${item.level}</td>
                                    <td>
                                        <button class="btn btn-warning btn-xs"
                                                data-toggle="modal" data-target="#myModal"
                                                type="button"
                                                onclick="update('${item.id}')">修改
                                        </button>
                                        <button class="btn btn-danger btn-xs"
                                                onclick="del('${item.id}')">
                                            type="button"
                                            删除
                                        </button>
                                        <a href="/linkmen/client/${item.id}"
                                           class="btn btn-primary btn-xs">联系人</a>
                                        <a href="javascript:void(0)"
                                           class="btn btn-primary btn-xs">资料</a>
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
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改客户</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="_method" value="put">

                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_name">客户名称</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="name" id="client_name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_legal">法人代表</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="legal"
                                   id="client_legal">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_name">地区</label>
                        <div class="col-sm-10">
                            <div class="form-inline">
                                <label for="client_province">省</label>
                                <select class="form-control" style="width: auto;" name="cities"
                                        id="client_province"
                                        onchange="getCityChildrenByParent(this)">
                                    <c:forEach items="${province}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                                <label for="client_city">市</label>
                                <select class="form-control" style="width: auto;" name="cities"
                                        id="client_city" onchange="getCityChildrenByParent(this)">
                                    <option value="0">--请选择--</option>
                                </select>
                                <label for="client_county">县</label>
                                <select class="form-control" style="width: auto;" name="cities"
                                        id="client_county">
                                    <option value="0">--请选择--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_phone">电话</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="phone" id="client_phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_register">登记时间</label>
                        <div class="col-sm-10">
                            <input class="form_datetime form-control" type="text"
                                   name="registerDate"
                                   id="client_register">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="client_next">下次联系时间</label>
                        <div class="col-sm-10">
                            <input class="form_datetime form-control" type="text" name="nextTime"
                                   id="client_next">
                        </div>
                    </div>
                </div>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function searchClient(val, condition) {
        $.ajax({
            url: "/clients/json",
            type: 'get',
            data: {
                condition: condition,
                param: val
            },
            dataType: 'json',
            success: function (rs) {
                var $table = $(".table.table-bordered");
                $table.html('<tr><th><label><input type="checkbox" id="all" onclick="checkAll(this)"></label></th><th>客户编码</th><th>客户名称</th><th>法人代表</th><th>城市</th><th>电话</th><th>登记时间</th><th>下次联系时间</th><th>等级</th><th>操作</th></tr>')
                $.each(rs, function (i, item) {
                    $table.append('<tr>' +
                        '<td><label>' +
                        '<input type="checkbox" name="ids" value="' + item.id + '">' +
                        '</label></td>' +
                        '<td>' + item.id + '</td>' +
                        '<td>' + item.name + '</td>' +
                        '<td>' + item.legal + '</td>' +
                        '<td>' + item.city.name + '</td>' +
                        '<td>' + item.telphone + '</td>' +
                        '<td>' + item.registerDate + '</td>' +
                        '<td>' + item.nextTime + '</td>' +
                        '<td>' + item.level + '</td>' +
                        '<td>' +
                        '<button class="btn btn-warning btn-xs"' +
                        'data-toggle="modal" data-target="#myModal"' +
                        'onclick="update(\'' + item.id + '\'")">修改' +
                        '</button>' +
                        '<button class="btn btn-danger btn-xs"' +
                        'onclick="del(\'' + item.id + '\')">' +
                        '删除' +
                        '</button>' +
                        '<a href="javascript:void(0)"' +
                        'class="btn btn-primary btn-xs">联系人</a>' +
                        '<a href="javascript:void(0)"' +
                        'class="btn btn-primary btn-xs">资料</a>' +
                        '</td>' +
                        '</tr>')
                });
            }
        })
    }

    function checkAll(obj) {
        $(".table.table-bordered :checkbox").prop("checked", obj.checked);
    }

    $(function () {
        $("#client_register").datetimepicker({minView: "month", format: 'yyyy-mm-dd'});
        $("#client_next").datetimepicker({minView: "month", format: 'yyyy-mm-dd'})
        $("#search").on("input", _.debounce(function () {
            var $this = $(this);
            var condition = $("#condition").val();
            searchClient($this.val(), condition);
        }, 500));
    });
    function del(id) {
        $("#delform").attr("action", "/client/" + id);
        $("#delform").submit();
    }

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

    function update(id) {
        $.ajax({
            url: "/client/" + id,
            type: "get",
            dataType: "json",
            success: function (rs) {
                if (!rs)
                    return;
                clearAll();
                $("#client_name").val(rs.name);
                $("#client_legal").val(rs.legal);
                $("#client_phone").val(rs.phone);
                $("#client_register").val(rs.registerDate);
                $("#client_next").val(rs.nextTime);
                $("#client_level").val(rs.level);
                switch (rs.city.level) {
                    case 0:
                        $("#client_province").val(rs.city.id);
                        getCityChildren(rs.city.id, $("#client_province"));
                        break;
                    case 1:
                        $("#client_province").val(rs.city.parent.id);
                        getCityChildren(rs.city.parent.id, $("#client_province"), function () {
                            $("#client_city").val(rs.city.id);
                        });
                        break;
                    case 2:
                        $("#client_province").val(rs.city.parent.parent.id);
                        getCityChildren(rs.city.parent.parent.id, $("#client_province"), function () {
                            $("#client_city").val(rs.city.parent.id);
                            getCityChildren(rs.city.parent.id, $("#client_city"), function () {
                                $("#client_county").val(rs.city.id);
                            });
                        });
                        break;
                }
                $("#updateform").attr("action", "/client/" + id);
                $("#updateform input[type='hidden']").val("PUT")
            }
        })
    }
    function clearAll() {
        $("#client_name").val('');
        $("#client_legal").val('');
        $("#client_phone").val('');
        $("#client_register").val('');
        $("#client_next").val('');
        $("#client_level").val(1);
        $("#client_city").html('<option value="0">--请选择--</option>');
        $("#client_county").html('<option value="0">--请选择--</option>');

    }

    function delAll() {
        $("#delAllForm").submit();
    }
</script>
</html>
