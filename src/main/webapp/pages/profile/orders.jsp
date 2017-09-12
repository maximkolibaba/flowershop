<%@ page import="com.accenture.flowershop.be.entity.order.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ page import="com.accenture.flowershop.be.entity.order.OrderStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Orders</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Orders</h2>
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
        <input type="button" name="buttonOrders" class="btn btn-primary" value="Orders"/>
        <input type="submit" name="buttonCart" class="btn btn-outline-info" value="Cart"/>
        <input type="submit" name="buttonLogout" class="btn btn-outline-danger" value="Logout"/>
    </form>

    <br/>

    <%
        List<Order> orders = (List<Order>) request.getSession(false).getAttribute("orders");
        User user = (User) request.getSession(false).getAttribute("user");
        if (orders == null || orders.isEmpty()) {
    %>

    You have no orders :(

    <% } else { %>

    <form action="/profile/orders" method="post">
        <table class="table">
            <tr>
                <th>Order ID</th>
                <th>Total price, RUB</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>

            <%
                for (Order order : orders) {
                    boolean haveMoney = order.getTotal().compareTo(user.getBalance()) != 1;
            %>

            <tr>
                <td>
                    <%= order.getId() %>
                </td>

                <td>
                    <div style='<%= (!haveMoney && order.getOrderStatus() == OrderStatus.PENDING_PAYMENT) ? "color:#ff4500" : "color:#292b2c" %>'>
                        <%= order.getTotal() %>
                    </div>
                </td>

                <td>
                    <%= order.getOrderStatus().getValue() %>
                </td>
                <td>
                    <%
                        if (order.getOrderStatus() == OrderStatus.PENDING_PAYMENT) {
                            if (haveMoney) {
                    %>

                    <input type="submit" class="btn btn-outline-success btn-sm" value="Pay" name='<%= "p" + order.getId() %>'/>

                    <% } else {%>

                    <input type="button" class="btn btn-outline-secondary btn-sm" value="Pay" name='<%= "p" + order.getId() %>' disabled/>

                    <% } %>

                    <input type="submit" class="btn btn-outline-danger btn-sm" value="Cancel" name='<%= "c" + order.getId() %>'/>

                    <% } else if (order.getOrderStatus() == OrderStatus.SHIPPED) { %>

                    <input type="submit" class="btn btn-outline-success btn-sm" value="Delivered" name='<%= "d" + order.getId() %>'/>

                    <% }} %>

                </td>
            </tr>
        </table>
        <form>
    <% } %>

</div>

</body>
</html>