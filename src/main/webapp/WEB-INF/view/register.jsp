<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.11.2022
  Time: 02:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>


<nav class>
    <div class="buttons">
        <a href="home">Home</a>
        <a href="about">About Us</a>
        <a href="order">Order</a><img src="<c:url value="/resources/Images/buy.png"/>" class="icon"/>
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
        <div class="regZone" style="overflow: auto">
            <span class="registerText">Register</span>
            <form:form action="registeredCheck" modelAttribute="registerTest">
                <form:input class="fname" placeholder="First Name" path="firstName"/>
                <form:input type="text" class="lname" placeholder="Last Name" path="lastName"/>
                <br>
                <form:errors path="firstName" cssClass="error"/>
                <form:errors path="lastName" cssClass="error" cssStyle="left:23%;"/>
                <br>
                <form:input type="email" placeholder="E-mail Address" class="email" path="email"/>
                <br>
                <form:errors path="email" cssClass="error"/>

                <br>
                <form:input type="text" placeholder="Username" class="username" path="username"/>
                <br>
                <form:errors path="username" cssClass="error"/><span class="error">${busy}</span>
                <br>
                <form:input type="password" placeholder="Password" class="password" path="password1"/>
                <br>
                <form:errors path="password1" cssClass="error"/><span class="error">${pass}</span>
                <br>
                <form:input type="password" placeholder="Repeat your password" class="password" path="password2"/>
                <br>
                <form:errors path="password2" cssClass="error"/><span class="error">${pass}</span>
                <br>

                <form:input type="text" class="lname place" placeholder="Address" path="address"/>
                        <form:input type="text" class="fname place" placeholder="City/Town/Village" path="location"/>
                    <form:errors path="address" cssClass="error" cssStyle="bottom:17px"/>
                    <form:errors path="location" cssClass="error" cssStyle="left: 27%;bottom:17px"/>
                <br>
                <br> <br>
                <input type="checkbox" class="term_button" style="width: 15px; height: 15px; margin-left: 0px;" required/><div class="agree_term">I agree to the terms, conditions and privacy policy</div>
                <input type="submit" class="create_acc" value="Create Account"/>

            </form:form>
            <br><br><br><br>
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
