<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Sign up</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/my.css"/>
</head>
<body>

<div class="layer">

    <h2>Flower Shop</h2>
    <br/>

    <form>
        <div class="form-group">
            <label for="inputLogin">Email address</label>
            <input type="text" class="form-control" id="inputLogin" placeholder="Login">
        </div>

        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
        </div>

        <button type="submit" class="btn btn-default">Login</button>

        <button type="submit" class="btn btn-default">Register</button>

        <br/>

        <a href="/flowers" type="submit" class="btn btn-default">View all flowers</a>

    </form>

</div>

</body>
</html>