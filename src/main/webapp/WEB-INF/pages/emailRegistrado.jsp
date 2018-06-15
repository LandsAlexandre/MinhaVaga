<%-- 
    Document   : emailRegistrado
    Created on : 13/06/2018, 14:43:52
    Author     : ISM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function show(){
                alert("Email já registrado!\n\nPor favor, insira novamente.");
                window.location.href="cliente";
            }
            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="show()">
    </body>
</html>
