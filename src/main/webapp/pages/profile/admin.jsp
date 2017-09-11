<%@ page import="com.accenture.flowershop.be.entity.order.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.be.entity.user.User" %>
<%@ page import="com.accenture.flowershop.be.entity.order.OrderStatus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Admin</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Admin page</h2>
    <br/>

    <%
        try {
            Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
            if (!isAdmin) {
                response.sendRedirect("profile");
                return;
            }
        } catch (NullPointerException ex) {
            response.sendRedirect("../../index.jsp");
            return;
        }
// TODO : fix redirect on all pages. /profile/info throwing to /profile/admin not to /admin
    %>

    <form action="/profile" method="post">
        <input type="button" name="buttonOrders" class="btn btn-primary" value="Orders"/>
        <input type="submit" name="buttonLogout" class="btn btn-outline-danger" value="Logout"/>
    </form>

    <br/>

    <%
        List<Order> orders = (List<Order>) request.getAttribute("allOrders");
        Collections.sort(orders);
        if (orders == null || orders.isEmpty()) {
    %>

    There is no orders :(

    <% } else { %>

    <form action="/admin" method="post">
        <table class="table">
            <tr>
                <th>Order ID</th>
                <th>Username</th>
                <th>Total price, RUB</th>
                <th>Create date</th>
                <th>Complete date</th>
                <th>Status</th>
                <th></th>
            </tr>

            <%
                for (Order order : orders) {
            %>

            <tr>
                <td><%= order.getId() %></td>

                <td><%= order.getUser().getLogin() %></td>

                <td><%= order.getTotal() %> </td>

                <td><%= order.getCreateDate() %> </td>

                <td><%= order.getCompleteDate() == null ? " " : order.getCompleteDate() %> </td>

                <td><%= order.getOrderStatus().getValue() %></td>

                <td>
                    <% if (order.getOrderStatus() == OrderStatus.PROCESSING) { %>
                    <input type="submit" class="btn btn-outline-primary btn-sm" value="Ship" name='<%= "s" + order.getId() %>'/>
                    <% } else if (order.getOrderStatus() == OrderStatus.DELIVERED) { %>
                    <input type="submit" class="btn btn-outline-primary btn-sm" value="Complete" name='<%= "c" + order.getId() %>'/>
                    <% } %>
                </td>
            </tr>

            <% } %>

        </table>
        <form>
         <% } %>

</div>

</body>
</html>