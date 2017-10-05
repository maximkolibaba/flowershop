<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="../../css/index.css"/>
    <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

<div class="layer">
    <input type="button" name="buttonInfo" class="btn btn-primary" value="Personal Information"/>
    <input type="button" name="buttonCatalog" class="btn btn-outline-info"
           value="Catalog" onclick="window.location = '/profile/catalog'"/>
    <input type="button" name="buttonOrders" class="btn btn-outline-info"
           value="Orders" onclick="window.location = '/profile/orders'"/>
    <input type="button" name="buttonCart" class="btn btn-outline-info"
           value="Cart" onclick="window.location = '/profile/cart'"/>
    <input type="button" name="buttonLogout" class="btn btn-outline-danger"
           value="Logout" onclick="window.location = '/rest/acc/logout'">
</div>

</body>
</html>