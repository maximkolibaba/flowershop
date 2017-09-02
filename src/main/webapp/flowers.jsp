<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Flowers</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/flowers.css"/>
</head>

<body>

<div class="layer">
    <form>
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
            <c:forEach items="${flowers}" var="iter" varStatus="rowStatus">
                <tr>
                    <td>${iter.id}</td>
                    <td>${iter.name}</td>
                    <td>${iter.price}</td>
                    <td>${iter.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

</body>

</html>