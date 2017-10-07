<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>系统管理</title>
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
           <jsp:param name="homepage" value="#"/>
           <jsp:param name="activeName" value="${activeName}"/>
       </jsp:include>
    </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">管理</div>
                <div class="panel-body">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
