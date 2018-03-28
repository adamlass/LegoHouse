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
            Configuration conf = (Configuration) session.getAttribute("configurationview");
        %>
    </head>
    <body>
        <%
            String role = user.getRole();
            if (role.equals("customer")) {%>
        <h1><%= user.getEmail()%>'s orders</h1>
        <% } else if (role.equals("employee")) { %>
        <h1>All Orders</h1>
        <% } %>
        <div>
            <table>
                <tr>
                    <th>Order</th>
                    <th>Email</th>
                    <th>Order Status</th>
                        <% if (role.equals("employee")) { %>
                    <th>Send Order</th>
                        <%}%>
                </tr>
                <% if (orders != null) {
                    for (Order order : orders) {%>
                <tr>
                    <td>
                        <form name="vieworder" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="vieworder">
                            <input type="hidden" name="id" value="<%= order.getId()%>">
                            <input type="submit" value="Order <%= order.getId()%>">
                        </form>


                    </td>
                    <td><%= order.getOwner().getEmail()%></td>
                    <td><%
                        if (order.isSent()) {
                            out.print("Sent!");
                        } else {
                            out.print("Not Sent Yet!");
                        }
                        %></td>
                        <% if (role.equals("employee")) {%>
                    <td>
                        <% if (!order.isSent()) {%>
                        <form name="SendOrder" action="FrontController" method="POST">
                            <input type="hidden" name="command" value="SendOrder">
                            <input type="submit" value="Mark as sent">
                        </form>
                        <%}%>
                    </td>
                    <%}%>
                </tr>
                <%}
            } else {%>
                <a>No orders to show</a>
                <%}%>
            </table>
        </div>
        <% if (conf != null) {%>
        <div>
            <table>
                <tr>
                    <th></th>
                    <th>4x2 blocks</th>
                    <th>2x2 blocks</th>
                    <th>1x2 blocks</th>
                    <th>Door</th>
                    <th>Window</th>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><%= conf.getFourTwo()%></td>
                    <td><%= conf.getTwoTwo()%></td>
                    <td><%= conf.getOneTwo()%></td>
                    <td><%= conf.isDoor()%></td>
                    <td><%= "" + conf.isWindow()%></td>
                </tr>
            </table>
        </div>
        <%}%>
    </body>
</html>
