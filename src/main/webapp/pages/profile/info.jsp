<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<%
    Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
    if (isAdmin == null) {
        response.sendRedirect("../../index.jsp");
        return;
    } else if (isAdmin) {
        response.sendRedirect("/admin");
        return;
    }
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Personal info</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
</head>
<body>

<div class="layer">

    <%
        User user = (User) request.getSession(false).getAttribute("user");
    %>

    <h2>Welcome, <%= user.getFirstName() %>!</h2>
    <br/>

    <form action="/profile" method="post">
        <input type="button" name="buttonInfo" class="btn btn-primary" value="Personal Information"/>
        <input type="submit" name="buttonCatalog" class="btn btn-outline-info" value="Catalog"/>
        <input type="submit" name="buttonOrders" class="btn btn-outline-info" value="Orders"/>
        <input type="submit" name="buttonCart" class="btn btn-outline-info" value="Cart"/>
        <input type="submit" name="buttonLogout" class="btn btn-outline-danger" value="Logout"/>
    </form>

    <br/>
    Username: <%= user.getLogin() %><br/>
    Full name: <%= user.getFirstName() + " " + user.getLastName() %><br/>
    Balance: <%= user.getBalance() %> RUB<br/>
    Discount: <%= user.getDiscount() %>%

</div>

</body>
</html>