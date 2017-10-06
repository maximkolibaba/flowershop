<%@ page import="com.accenture.flowershop.fe.Redirect" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);

    if (Redirect.fromUserPage(request, response)) {
        return;
    }
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Catalog</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Catalog</h2>
    <br/>

    <input type="button" name="buttonInfo" class="btn btn-outline-info"
           value="Personal Information" onclick="window.location = '/profile/info'"/>
    <input type="button" name="buttonCatalog" class="btn btn-primary"
           value="Catalog"/>
    <input type="button" name="buttonOrders" class="btn btn-outline-info"
           value="Orders" onclick="window.location = '/profile/orders'"/>
    <input type="button" name="buttonCart" class="btn btn-outline-info"
           value="Cart" onclick="window.location = '/profile/cart'"/>
    <input type="button" name="buttonLogout" class="btn btn-outline-danger"
           value="Logout" onclick="window.location = '/rest/acc/logout'"/>

    <br/><br/>

    <form action="/profile/catalog" method="post">
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Price, RUB</th>
                <th>In stock, pcs.</th>
                <th>To order</th>
            </tr>
            <c:forEach items="${flowers}" var="item" varStatus="rowStatus">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.amount}</td>
                    <td>
                        <input type="number" class="form-control" name="${item.id}" min="0" max="${item.amount}"
                               value="0">
                    </td>
                </tr>
            </c:forEach>
        </table>

        <input type="submit" class="btn btn-primary" value="Add to cart">
    </form>

</div>

</body>
</html>