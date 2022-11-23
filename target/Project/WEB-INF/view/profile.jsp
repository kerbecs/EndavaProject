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
    <title>About Us</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>


<nav class>
    <div class="buttons">
        <a href="home">Home</a>
        <a href="aboutUs">About Us</a>
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
    <article>
        <div class="aboutUs">
            <p class="welcome">Your profile</p>
            <hr>
            <div class="infoProfile">
                <p class="info">First Name <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">Last Name <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">Email <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">City/Town/Village <span class="personal"><input type="text" class="personalInfo"/></span> </p>
                <p class="info">Address <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">Job <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">Orders <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <p class="info">New Password <span class="personal"><input type="text" class="personalInfo"/></span></p>
                <div class="save"><a href="">Save</a></div>
                <br><br>
            </div>
        </div>
    </article>
    <div class="main">
        <img src="<c:url value="/resources/Images/main.jpg"/>" alt="Img"/>
    </div>
</section>

<footer>
</footer>


</body>
</html>