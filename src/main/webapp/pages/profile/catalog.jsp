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

    <%
//        try {
//            if (!(Boolean) request.getSession(false).getAttribute("isLoggedIn")) {
//                response.sendRedirect("../../index.jsp");
//                return;
//            }
//        } catch (NullPointerException ex) {
//            response.sendRedirect("../../index.jsp");
//            return;
//        }

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
        <input type="button" name="buttonCatalog" class="btn btn-primary" value="Catalog"/>
        <input type="submit" name="buttonOrders" class="btn btn-outline-info" value="Orders"/>
        <input type="submit" name="buttonCart" class="btn btn-outline-info" value="Cart"/>
        <input type="submit" name="buttonLogout" class="btn btn-outline-danger" value="Logout"/>
    </form>

    <br/>

    <%-- TODO: add message "not enough items" --%>

    <form action="/profile/catalog" method="post">
        <table class="table">
            <tr>
                <%--<th>ID</th>--%>
                <th>Name</th>
                <th>Price, RUB</th>
                <th>In stock, pcs.</th>
                <th>To order</th>
            </tr>
            <c:forEach items="${flowers}" var="item" varStatus="rowStatus">
                <tr>
                        <%--<td>${iter.id}</td>--%>
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