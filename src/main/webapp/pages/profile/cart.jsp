<%@ page import="com.accenture.flowershop.fe.Cart" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
//    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Cart</title>

    <meta http-equiv="content-type" content="text/html" charset="UTF-32">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Cart</h2>
    <br/>

    <%
        Boolean flag = (Boolean) request.getSession(false).getAttribute("isLoggedIn");
        if (flag == null || !flag) {
            response.sendRedirect("../../index.jsp");
            return;
        }
    %>

    <form action="/profile" method="post">
        <input type="submit" name="buttonInfo" class="btn btn-outline-info" value="Personal Information"/>
        <input type="submit" name="buttonCatalog" class="btn btn-outline-info" value="Catalog"/>
        <input type="submit" name="buttonOrders" class="btn btn-outline-info" value="Orders"/>
        <input type="button" name="buttonCart" class="btn btn-primary" value="Cart"/>
        <input type="submit" name="buttonLogout" class="btn btn-outline-danger" value="Logout"/>
    </form>

    <br/>

    <%
        Cart cart = ((Cart) request.getSession(false).getAttribute("cart"));
        if (cart != null && !cart.isEmpty()) {
            BigDecimal total = cart.getTotal();
            User user = (User) request.getSession(false).getAttribute("user");
            int discount = user.getDiscount();
            BigDecimal priceWDisc = total.multiply(new BigDecimal(1 - (double) (discount) / 100));
            priceWDisc = priceWDisc.setScale(2, BigDecimal.ROUND_HALF_UP);
            boolean enoughMoney = user.getBalance().compareTo(priceWDisc) != -1;
    %>
    <form action="/profile/cart" method="post">
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Amount, pcs.</th>
                <th>Price, RUB</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cart.items}" varStatus="rowStatus">
                <tr>
                    <td>${item.flower.name}</td>
                    <td>${item.amount}</td>
                    <td>${item.total}</td>
                    <td>
                        <input type="submit" class="btn btn-outline-danger btn-sm" value="Remove"
                               name="remove${item.flower.name}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <% if (!enoughMoney) { %>
        <div style="color:orangered">You do not have enough money to order this.</div>
        <% } %>

        <br/>Total price without discount: <%= total %> RUB
        <br/>Your discount: <%= discount %>%
        <br/>Total price with discount: <%= priceWDisc %> RUB

        <br/><br/>

        <% if (enoughMoney) { %>
            <input type="submit" class="btn btn-primary" value="Order" name="buttonOrder"/>
        <% } else { %>
            <input type="submit" class="btn btn-primary" value="Order" name="buttonOrder" disabled/>
        <% } %>

    </form>

    <% } else { %>
    There is no items in your cart :(
    <% } %>

</div>

</body>
</html>