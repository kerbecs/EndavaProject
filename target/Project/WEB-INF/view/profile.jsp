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
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<html>

<head>
    <title>About Us</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>


<nav class style="margin-top:3%;">
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
    <article>
        <div class="aboutUs">
            <p class="welcome">Your profile</p>
            <hr>
            <br>
            <font style="color: red;font-size:14px;font-family:Verdana;position:relative;left:0%;">
                ${error}</font>
            <form:form modelAttribute="infoUser" action="updateProfile">
            <div class="infoProfile">
                <p class="info">First Name <span class="personal"><form:input class="personalInfo" path="firstName"/></span></p>
                <p class="info">Last Name <span class="personal"><form:input type="text" class="personalInfo" path="lastName"/></span></p>
                <p class="info">Email <span class="personal"><form:input type="text" class="personalInfo" path="email"/></span></p>
                <p class="info">City/Town/Village <span class="personal"><form:input type="text" class="personalInfo" path="location"/></span> </p>
                <p class="info">Address <span class="personal"><form:input type="text" class="personalInfo" path="address"/></span></p>
                <p class="info">Job <span class="personal"><form:input type="text" class="personalInfo" path="job"/></span></p>
                <p class="info">Orders <span class="personal"><form:input type="text" class="personalInfo" path="orders" disabled="true"/></span></p>
                <p class="info">New Password <span class="personal"><form:password class="personalInfo" path="password" value="${infoUser.password}"/></span></p>
                <input type="submit" value="Submit" style="    position: relative;right: 20%;font-size: 18px;font-weight: bold;font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;background-color: #ECBF07;cursor:pointer;"/>
                </form:form>
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