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
    <title>Sign up</title>

    <%--<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />--%>
    <%--<meta http-equiv="Pragma" content="no-cache" />--%>
    <%--<meta http-equiv="Expires" content="0" />--%>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Flower Shop</h2>
    <br/>

    <form method="post" action="login">
        <div class="form-group">
            <label for="inputLogin">Username</label>
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Login">
        </div>

        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
        </div>

        <c:if test="${isLoggedIn eq 'false'}">
            <div style="color:orangered">Incorrect login/password. Please try again or register.</div>
            <br/>
        </c:if>

        <input type="submit" class="btn btn-default" value="Sign In">

        <a href="register" class="btn btn-default">Register</a>

        <%
            request.getSession(false).removeAttribute("isLoggedIn");
        %>

    </form>

</div>

</body>
</html>