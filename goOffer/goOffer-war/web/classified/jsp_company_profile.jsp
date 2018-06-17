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
        <form action="../servlet_company_adjust_profile" method="POST">
            Username: <input type="text" value="<%=c.getUsername()%>" name="companyUsername" readonly /><br>
            Password: <input type="password" value="<%=c.getPassword()%>" name="companyPassword" /><br>
            Name: <input type="text" value="<%=c.getCompanyName()%>" name="companyName"/><br>
            Address: <input type="text" value="<%=c.getAddress()%>" name="companyAddress"/><br>
            Population <input type="text" value="<%=c.getPopulation()%>" name="companyPopulation"/><br>
            <input type="submit" value="Update Profile">
        </form>
    </body>
</html>
