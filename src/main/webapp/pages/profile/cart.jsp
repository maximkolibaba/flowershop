<%@ page import="com.accenture.flowershop.fe.Cart" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ page import="com.accenture.flowershop.fe.CartItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
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
        Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
        if (isAdmin == null) {
            response.sendRedirect("../../index.jsp");
            return;
        } else if (isAdmin) {
            response.sendRedirect("/admin");
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
    %>
    <form action="/profile/cart" method="post">
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Amount, pcs.</th>
                <th>Price, RUB</th>
                <th></th>
            </tr>
            <% for (CartItem item : cart) { %>
                <tr>
                    <td><%= item.getFlowerName() %></td>
                    <td><%= item.getAmount() %></td>
                    <td><%= item.getTotal() %></td>
                    <td>
                        <input type="submit" class="btn btn-outline-danger btn-sm" value="Remove"
                               name='<%= "remove" + item.getFlowerName() %>'/>
                    </td>
                </tr>
            <% } %>
        </table>

        <br/>Total price without discount: <%= total %> RUB
        <br/>Your discount: <%= discount %>%
        <br/>Total price with discount: <%= priceWDisc %> RUB

        <br/><br/>

        <input type="submit" class="btn btn-primary" value="Order" name="buttonOrder"/>

    </form>

    <% } else { %>
    There is no items in your cart :(
    <% } %>

</div>

</body>
</html>