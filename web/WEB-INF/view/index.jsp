<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 02.04.16
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


    <script src="https://code.angularjs.org/1.5.3/angular.js"></script>
    <script src="https://code.angularjs.org/1.5.3/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

    <%--&lt;%&ndash;do suwaka&ndash;%&gt;--%>

    <%--<!-- Icons -->--%>
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">--%>

    <%--<!-- Themes -->--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-1to10.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-movie.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-square.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-pill.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-reversed.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bars-horizontal.css">--%>

    <%--<link rel="stylesheet" href="dist/themes/fontawesome-stars.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/css-stars.css">--%>
    <%--<link rel="stylesheet" href="dist/themes/bootstrap-stars.css">--%>

    <%--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script>window.jQuery || document.write('<script src="vendor/jquery-1.11.2.min.js"><\/script>')</script>--%>
    <%--<script src="barJS/examples.js"></script>--%>
    <%--<script src="barJS/jquery.barrating.js"></script>--%>




    <title>Questionnaire</title>
</head>

<body ng-app="hello">

<div class="logo navbar-fixed-top">
    <a href="#/"><img src="${pageContext.request.contextPath}/resources/images/whitelogo.png"></a>
    <li><a class="login-button-nav" href="#/login" ng-show="!authenticated">login</a></li>
</div>


        <div ng-view>

        </div>

<%--<footer>--%>
    <%--wszystko dziala wow wow <br/>--%>
    <%--<a href="hello">Say Hello</a> <br/>--%>
    <%--<a href="witaj">Przywitac Cie?</a> <br/>--%>

<%--</footer>--%>

</body>
</html>