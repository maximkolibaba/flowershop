<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Sign up</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/login.js"></script>
</head>
<body>

<div class="layer">

    <h2>Flower Shop</h2>
    <br/>

    <form>

        <div class="form-group">
            <label for="inputLogin">Username</label>
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Login">
        </div>

        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
        </div>

        <div id="incorrect-login" style="color:#ff4500"></div>

        <%--<input type="submit" class="btn btn-primary" >--%>
        <input type="button" id="signInButton" class="btn btn-primary" value="Sign In">

        <input type="button" class="btn btn-secondary" value="Register"
               name="buttonRegister" onclick="window.location = '/register'">

        <%
            request.getSession(false).removeAttribute("correctLogIn");
        %>

    </form>

</div>

</body>
</html>