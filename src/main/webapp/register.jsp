<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="js/register.js"></script>
</head>
<body>

<div class="layer">

    <h2>Register</h2>
    <br/>

    <div class="form-group">
        <label for="inputLogin">Username</label>
        <input type="text" class="form-control" id="inputLogin" name="login" v-model="postBody" v-on:keyup="postPost()">
    </div>

    <div id="divLogin"></div>

    <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" class="form-control" id="inputPassword" name="password">
    </div>

    <div class="form-group">
        <label for="inputFirstName">First name</label>
        <input type="text" class="form-control" id="inputFirstName" name="firstName">
    </div>

    <div class="form-group">
        <label for="inputLastName">Last name</label>
        <input type="text" class="form-control" id="inputLastName" name="lastName">
    </div>

    <div class="form-group">
        <label for="inputAddress">Home address</label>
        <input type="text" class="form-control" id="inputAddress" name="address">
    </div>

    <input type="button" class="btn btn-primary" value="Register" id="buttonRegister">

</div>

</body>
</html>