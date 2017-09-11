<%@ page import="com.accenture.flowershop.fe.Cart" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ page import="com.accenture.flowershop.fe.CartItem" %>
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
        //Boolean flag = (Boolean) request.getSession(false).getAttribute("isLoggedIn");
        //if (flag == null || !flag) {
        //    response.sendRedirect("../../index.jsp");
        //    return;
        //}
        //Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
        //if (isAdmin == null) {
        //    request.getRequestDispatcher("login.jsp").forward(req, resp);
        //} else if (isAdmin) {
        //    request.sendRedirect("admin");
        //} else {
        //    request.sendRedirect("profile");
        //}
        Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
        if (isAdmin == null) {
            response.sendRedirect("../../index.jsp");
            return;
        } else if (isAdmin) {
            response.sendRedirect("admin");
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
            <%--<c:forEach var="item" items="${cart.items}" varStatus="rowStatus">--%>
            <% for (CartItem item : cart) { %>
                <tr>
                    <td><%= item.getFlowerName() %></td>
                    <td><%= item.getAmount() %></td>
                    <td><%= item.getTotal() %></td>
                    <td>
                        <input type="submit" class="btn btn-outline-danger btn-sm" value="Remove"
                               name='<%= "remove" + item.getFlowerName() %>'/>
                    </td>
                    <%--<td>${item.flower.name}</td>--%>
                    <%--<td>${item.amount}</td>--%>
                    <%--<td>${item.total}</td>--%>
                    <%--<td>--%>
                        <%--<input type="submit" class="btn btn-outline-danger btn-sm" value="Remove"--%>
                               <%--name="remove${item.flower.name}"/>--%>
                    <%--</td>--%>
                </tr>
            <% } %>
            <%--</c:forEach>--%>
        </table>

        <%-- TODO: check money in order --%>
        <%-- TODO: check amount in catalog and cart --%>

        <%--<% if (!enoughMoney) { %>--%>
        <%--<div style="color:orangered">You do not have enough money to order this.</div>--%>
        <%--<% } %>--%>

        <br/>Total price without discount: <%= total %> RUB
        <br/>Your discount: <%= discount %>%
        <br/>Total price with discount: <%= priceWDisc %> RUB

        <br/><br/>

        <input type="submit" class="btn btn-primary" value="Order" name="buttonOrder"/>

        <%--<% if (enoughMoney) { %>--%>
            <%--<input type="submit" class="btn btn-primary" value="Order" name="buttonOrder"/>--%>
        <%--<% } else { %>--%>
            <%--<input class="btn btn-primary" value="Order" disabled/>--%>
        <%--<% } %>--%>

    </form>

    <% } else { %>
    There is no items in your cart :(
    <% } %>

</div>

</body>
</html>