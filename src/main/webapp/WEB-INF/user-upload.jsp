<%-- 
    Document   : user-upload
    Created on : Feb 12, 2020, 6:43:22 AM
    Author     : permadi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        String nama=request.getParameter("user");
        String pass=request.getParameter("pswd");
        
        if(nama.equals("Jsmith"))&&(pass.equals("P@ssw0rd"))
            {
        
               session.SetAttribute("username", nama); |
        
            }
     %>
    
                
    </body>
</html>
