<%-- 
    Document   : jsp_company_profile
    Created on : Jun 17, 2018, 5:31:19 PM
    Author     : jiahao pan 
--%>

<%@page import="goOffer.entities.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Company c = (Company) request.getAttribute("currentCompany");
        %>
        <form action="servlet_company_adjust_profile" method="POST">
            Username: <input type="text" value="<%=c.getUsername()%>" readonly /><br>
            Password: <input type="password" value="<%=c.getPassword()%>"/><br>
            Name: <input type="text" value="<%=c.getCompanyName()%>"/><br>
            Address: <input type="text" value="<%=c.getAddress()%>"/><br>
            Population <input type="text" value="<%=c.getPopulation()%>"/><br>
            <input type="submit" value="Update Profile">
        </form>
    </body>
</html>
