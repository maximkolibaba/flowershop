<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@page session='false' %>--%>

<%
    //    response.setHeader("Pragma", "No-cache");
//    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//    response.setDateHeader("Expires", -1);
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Register</title>

    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap/bootstrap4.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
</head>
<body>

<div class="layer">

    <h2>Register</h2>
    <br/>

    <form method="post" action="register">

        <c:choose>

            <c:when test="${success eq 'true'}">

                <div style="color:#2b542c">Registration is completed.</div>
                <br/>

                <input type="submit" class="btn btn-outline-success" value="Back to login page">

            </c:when>

            <c:otherwise>

                <%-- Username --%>

                <div class="form-group">
                    <label for="inputLogin">Username</label>
                    <input type="text" class="form-control" id="inputLogin" name="login">
                </div>

                <c:if test="${noLogin eq 'true'}">
                    <div style="color:orangered">Username field is empty. Please enter your username.</div>
                    <br/>
                </c:if>

                <c:if test="${loginIsUsed eq 'true'}">
                    <div style="color:orangered">This username is already in use. Please enter another username.</div>
                    <br/>
                </c:if>

                <%-- Password --%>

                <div class="form-group">
                    <label for="inputPassword">Password</label>
                    <input type="password" class="form-control" id="inputPassword" name="password">
                </div>

                <c:if test="${noPassword eq 'true'}">
                    <div style="color:orangered">Password field is empty. Please enter your password.</div>
                    <br/>
                </c:if>

                <%-- First name --%>

                <div class="form-group">
                    <label for="inputFirstName">First name</label>
                    <input type="text" class="form-control" id="inputFirstName" name="firstName">
                </div>

                <%-- Last name --%>

                <div class="form-group">
                    <label for="inputLastName">Last name</label>
                    <input type="text" class="form-control" id="inputLastName" name="lastName">
                </div>

                <%-- Address --%>

                <div class="form-group">
                    <label for="inputAddress">Home address</label>
                    <input type="text" class="form-control" id="inputAddress" name="address">
                </div>

                <c:if test="${notFullInfo eq 'true'}">
                    <div style="color:orangered">Your personal information is incomplete. Please complete it.</div>
                    <br/>
                </c:if>

                <input type="submit" class="btn btn-primary" value="Register">

                <%
                    HttpSession s = request.getSession(false);
                    s.removeAttribute("noLogin");
                    s.removeAttribute("loginIsUsed");
                    s.removeAttribute("noPassword");
                    s.removeAttribute("notFullInfo");
                %>

            </c:otherwise>

        </c:choose>

    </form>

</div>

</body>
</html>