<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 02.04.16
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html ng-app>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <%--<link rel="stylesheet" href="resources/css/main.css">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">

    <spring:url value="/resources/js/main.js" var="mainJs" />

    <title>Spring 3.0 MVC Series: Index - ViralPatel.net</title>
</head>
<body>
<div class="logo">
    <img src="${pageContext.request.contextPath}/resources/images/background.png" alt="hhh">
</div>

<h1 class="home-heading">
    <!-- Build free online questionnaires & decide! -->
    A simple tool to help you decide!
</h1>

<p class="home-text">
    Sign up now for FREE unlimited surveys, questions & responses. <br/>
<button type="submit" class="btn btn-danger">Sign Up</button>
</p>



<input type="text" ng-model="name">
<h2>Hello {{name}}!</h2>
wszystko dziala wow wow <br/>
<a href="hello">Say Hello</a> <br/>
<a href="witaj">Przywitac Cie?</a> <br/>

<%--<script src="resources/scripts/main.js"></script>--%>
</body>
</html>