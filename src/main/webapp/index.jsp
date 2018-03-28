<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adams LegoHouse</title>
        <%@include file="bootstrap.jsp" %>
    </head>
    <body>
        <h1>Adams LegoHouse</h1>
        <img src="Pictures/LegoHouse2.png" width="20%" alt="LegoHouse" />
        <table>
            <tr><td>Login</td>
                <td>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="login">
                        Email:<br>
                        <input type="text" name="email" placeholder="admin@LegoHouse.com">
                        <br>
                        Password:<br>
                        <input type="password" name="password" placeholder="1234">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
                <td>Or Register</td>
                <td>
                    <form name="register" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="register">
                        Email:<br>
                        <input type="text" name="email" placeholder="customer@LegoHouse.com">
                        <br>
                        Password:<br>
                        <input type="password" name="password1" placeholder="********">
                        <br>
                        Retype Password:<br>
                        <input type="password" name="password2" placeholder="********">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
            </tr>
        </table>
        <% String error = (String) request.getAttribute("error");
            if (error != null) {%>
        <H2>Error!!</h2>
        <p><%= error%>
            <% }
            %>
    </body>
</html>
