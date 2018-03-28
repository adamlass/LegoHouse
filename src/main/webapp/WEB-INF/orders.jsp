<%-- 
    Document   : orders
    Created on : 28-03-2018, 15:39:45
    Author     : adamlass
--%>

<%@page import="FunctionLayer.Order"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.User"%>
<%@page import="PresentationLayer.Configuration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% User user = (User) session.getAttribute("user");
            List<Order> orders = (List<Order>) session.getAttribute("orders");
        %>
    </head>
    <body>
        <% 
            String role = user.getRole();
            if(role.equals("customer")){ %>
        <h1><%= user.getEmail() %>'s orders</h1>
        <% } else if(role.equals("employee")){ %>
        <h1>All Orders</h1>
        <% } %>
        <div>
        <table>
            <tr>
                <th>Order ID</th>
                <th>Email</th>
                <th>Order Status</th>
                <% if(role.equals("employee")){ %>
                <th>Change Order Status</th>
                <%}%>
            </tr>
            <% if(orders != null){
                for(Order order : orders){ %>
            <tr>
                <td><%= order.getId() %></td>
                <td><%= order.getOwner().getEmail() %></td>
                <td><%
                    if(order.isSent()){
                        out.print("Sent!");
                    } else{
                        out.print("Not Sent Yet!");
                    }
                     %></td>
                <% if(role.equals("employee")) {%>
                <td>
<!--                    insert form for changing status-->
                </td>
                <%}%>
            </tr>
            <%}} else {%>
            <a>No orders to show</a>
            <%}%>
        </table>
        </div>
        
        
    </body>
</html>
