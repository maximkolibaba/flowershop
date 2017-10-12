<%@ page import="com.accenture.flowershop.be.entity.order.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.be.entity.order.OrderStatus" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.accenture.flowershop.fe.Redirect" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);

    if (Redirect.fromAdminPage(request, response)) {
        return;
    }
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Admin</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <script src="../../js/admin.js"></script>
    <script src="../../js/vue.js"></script>
    <script src="../../js/axios.js"></script>
</head>
<body>

<div class="layer">

    <h2>Admin page</h2>
    <br/>

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

                <td><%= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault()).format(order.getCreateDate()) %> </td>

                <td><%= order.getCompleteDate() == null ? " " : new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"
                        , Locale.getDefault()).format(order.getCompleteDate()) %> </td>

                <td><%= order.getOrderStatus().getValue() %></td>

                <td>
                    <% if (order.getOrderStatus() == OrderStatus.PROCESSING) { %>
                    <input type="button" class="btn btn-outline-primary btn-sm .admin-btn" value="Ship" name='<%= "s" + order.getId() %>'/>
                    <% } else if (order.getOrderStatus() == OrderStatus.DELIVERED) { %>
                    <input type="button" class="btn btn-outline-primary btn-sm .admin-btn" value="Complete" name='<%= "c" + order.getId() %>'/>
                    <% } else {%>
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