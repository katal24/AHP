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

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="view/main.css">

    <title>Spring 3.0 MVC Series: Index - ViralPatel.net</title>
</head>
<body>
<div class="logo">
    <img src="images/background.png">
</div>

<h1 class="home-heading">
    <!-- Build free online questionnaires & decide! -->
    A simple tool to help you decide!
</h1>

<p class="home-text">
    Sign up now for FREE unlimited surveys, questions & responses.
</p>

<h2><button type="submit" class="btn btn-danger">Sign Up</button></h2>

<input type="text" ng-model="name">
<h2>Hello {{name}}!</h2>
wszystko dziala wow wow <br/>
<a href="hello">Say Hello</a> <br/>
<a href="witaj">Przywitac Cie?</a> <br/>

<script src="scripts/main.js"></script>
</body>
</html>