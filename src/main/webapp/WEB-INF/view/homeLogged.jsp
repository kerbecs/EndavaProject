<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.11.2022
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>


<nav class style="margin-top: 3%;">
    <div class="buttons">
        <a href="home">Home</a>
        <a href="about">About Us</a>
        <a href="order">Order</a><img src="<c:url value="/resources/Images/buy.png"/>" class="icon" alt="Img"/>
        <a href="profile">Profile</a>
        <a href="logout">Logout</a>
        <a href=""><img src="<c:url value="/resources/Images/facebook.png"/>" class="facebook facebookLogged" alt="Img"/></a>
        <a href=""><img src="<c:url value="/resources/Images/instagram.png"/>" class="instagram instagramLogged" alt="Img"/></a>
    </div>

</nav>


<header>
    <a href="home">
        <font>Gustoso</font>
        <img src="<c:url value="/resources/Images/logo.png"/>" alt="Img"/>
    </a>
</header>


<section>
    <div class="mainText">
        Juicy & Hoot
        <br>Food
        <br>
        <span>Best gastronomic expirience!</span>
        <form action="" name="">
            <a href="order"> <input type="button" value="Order Food" class="order"/> </a>
        </form>
    </div>
    <div class="main">
        <img src="<c:url value="/resources/Images/main.jpg"/>" alt="Img"/>
    </div>


</section>


<footer>
</footer>


</body>
</html>