<%--
  Created by IntelliJ IDEA.
  User: Paosin Von Scarlet
  Date: 2017/9/29
  Time: 9:51
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
    <title>登录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/js/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default" style="margin-top: 150px;">
                <div class="panel-heading">用户登录</div>
                <div class="panel-body">
                    <c:if test="${!((message eq null) or (message eq ''))}">
                        <div class="alert alert-danger alert-sm alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                                ${message }
                        </div>
                    </c:if>
                    <form class="form-horizontal" action="/login" method="post">
                        <div class="form-group">
                            <label class="control-label sr-only">Username</label>
                            <div class="col-sm-12">
                                <div class="input-group">
							        		<span class="input-group-addon">
							        			<span class="glyphicon glyphicon-user"></span>
							        		</span>
                                    <input type="text" name="username" value="${username }"
                                           class="form-control" placeholder="用户名">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label sr-only">Password</label>
                            <div class="col-sm-12">
                                <div class="input-group">
							        		<span class="input-group-addon">
							        			<span class="glyphicon glyphicon-lock"></span>
							        		</span>
                                    <input type="password" name="password" value="${password }"
                                           class="form-control" placeholder="密码">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary btn-block">登录</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
