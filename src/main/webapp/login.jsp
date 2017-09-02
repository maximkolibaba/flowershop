<!DOCTYPE html>

<html lang="en">
<head>
    <title>Sign up</title>
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
            <label for="inputLogin">Email address</label>
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Login">
        </div>

        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
        </div>

        <%--todo return true if user is logged correctly--%>
        <%--<c:if test="${}"--%>

        <%--<button type="submit" class="btn btn-default">Login</button>--%>
        <input type="submit" class="btn btn-default" value="Login">

        <button type="submit" class="btn btn-default">Register</button>

        <br/><br/><br/>

        <a href="flowers" type="submit" class="btn btn-default">View all flowers</a>

    </form>

</div>

</body>
</html>