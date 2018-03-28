<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
        <%@include file="bootstrap.jsp" %>
        <%
            User user = (User) session.getAttribute("user");
            String mark = (String) session.getAttribute("mark");
            session.setAttribute("mark", null);
        %>
    </head>
    <body>
        <div class="container container-fluid">
        <div class="well">
        <h1>Hello <%= user.getEmail() %> </h1>
        Logged in as Employee! You can't make orders, but only see and send them.
        </div>
        <div>
            <% if (mark != null) {%>
            <h2>Order was sent!</h2>
            <% }%>
            <form name="SeeOrders" action="FrontController" method="post">
                <input type="hidden" name="command" value="SeeOrders">
                <input type="submit" name="orders" class="btn btn-info" value="See All Orders">
            </form>
            <%@include file="logout.jsp" %>
        </div>
        </div>
    </body>
</html>
