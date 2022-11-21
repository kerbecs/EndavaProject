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
        <a href="aboutUs">About Us</a>
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
        <div class="regZone">
            <span><font>Register</font></span>
            <form>
                <input type="text" class="fname" placeholder="First Name"/>
                <input type="text" class="lname" placeholder="Last Name"/>
                <font class="required requiredfn">This is required</font>
                <font class="required requiredln">This is required</font>

                <input type="email" placeholder="E-mail Address" class="email"/>
                <font class="required requiredfn ">This is required</font>

                <br>
                <input type="text" placeholder="Username" class="username"/>
                <font class="required requiredfn ">This is required</font>
                <br>
                <input type="text" placeholder="Password" class="password"/>
                <font class="required requiredfn ">This is required</font>
                <br>
                <input type="text" placeholder="Repeat your password" class="password"/>
                <font class="required requiredfn ">This is required</font>
                <br>

                <input type="text" class="fname place" placeholder="City/Town/Village"/>
                <input type="text" class="lname place" placeholder="Address"/>
                <br>
                <input type="checkbox" class="term_button" style="width: 15px; height: 15px; margin-left: 0px;"/><span class="agree_term">I agree to the terms and conditions and the privacy policy</span>
                <br>
                <input type="button" class="create_acc" value="Create Account"/>

            </form>
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
