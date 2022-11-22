<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.11.2022
  Time: 02:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false" %>

<html>

<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>


<nav class>
    <div class="buttons">
        <a href="home">Home</a>
        <a href="aboutUs">About Us</a>
        <a href="order">Order</a><img src="<c:url value="/resources/Images/buy.png"/>" class="icon" alt="Img"/>
        <a href="login">Log in</a>
        <a href=""><img src="<c:url value="/resources/Images/facebook.png"/>" class="facebook" alt="Img"/></a>
        <a href=""><img src="<c:url value="/resources/Images/instagram.png"/>" class="instagram" alt="Img"/></a>
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
        <div class="logZone">
            <span><font>LogIn</font></span>
            <form:form action="loginCheck" modelAttribute="userTest">
                <br><br><br>
                <p class="invalid">${invalid}</p>
                <br><br>
                <font class="loginFont">Username</font><br>
                <form:input type="text" class="fname user" placeholder="Enter your username" path="username"/>
                <br>
                <font class="required">&nbsp &nbsp &nbsp &nbsp &nbsp <form:errors path="username"/></font>
                <br>
                <font class="loginFont">Password</font><br>
                <form:input type="password" class="lname pass" placeholder="Enter your password" path="password"/>
                <br>
                <font class="required"> &nbsp &nbsp &nbsp &nbsp &nbsp <form:errors path="password" /></font>


                <br>
                <input type="submit" class="create_acc login" value="Login"/>
                <br>
                <br>
                <input type="button" class="create_acc login forgot" value="Reset Password "/>
                <br>
                <div class="notRegistered">
                    Don't have an account? <a href="register">Register now</a>
                </div>

            </form:form>
        </div>
    </article>
    <div class="main">

        <img src="<c:url value="/resources/Images/main.jpg"/>" alt="Img"/>
    </div>
    <article>

    </article>

</section>


<footer>
</footer>


</body>
</html>
