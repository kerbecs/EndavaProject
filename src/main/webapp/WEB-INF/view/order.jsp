<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.11.2022
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Gustoso</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>">
</head>
<body>
<nav style="margin-top:0">
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
        <div class="orderProduct">
            <p class="welcome">Order Food <br>Online</p>
            <form:form modelAttribute="products" action="notLogged">
                <c:forEach var="var" items="${products}">
                    <p class="product">
                        <span class="img"><img src="<c:url value="/resources/Images/pizza.jpg"/> " alt="Img"/></span>
                        <span class="description">
                    <b style="font-size: 20px;">${var.title}</b>
                    <br>
                    <br>
                    <b>Ingredients: </b>${var.ingredients}
                    <br><br><b>Weight: </b>${var.weight}gr
                    <br><b style="font-size: 16px">Price: </b>${var.price}
                </span>
                        <span class="addButton">
                    <b>Add to list</b>
                    <br>
                    <input type="checkbox" class="add"/>
                </span>
                        <br><br>
                    </p>
                </c:forEach>
                <input type="submit" value="Order Now" class="orderNow"/>
            </form:form>
            <br><br>
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
