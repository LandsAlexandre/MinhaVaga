<%-- 
    Document   : loginIncorreto
    Created on : 13/06/2018, 16:25:14
    Author     : ISM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <script>
            function show(){
                alert("Login incorreto!\n\nPor favor, tente novamente.");
                window.location.href="login";
            }
            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="show()">
    </body>
</html>
