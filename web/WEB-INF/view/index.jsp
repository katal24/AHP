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
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


    <%--<link rel="stylesheet" href="resources/css/main.css">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <script src="https://code.angularjs.org/1.5.3/angular.js"></script>
    <script src="https://code.angularjs.org/1.5.3/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


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