<%-- 
    Document   : cliente-adicionado
    Created on : 06/06/2018, 20:37:37
    Author     : ISM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <script>
            function show(){
                alert("Bem vindo, Fulano!");
                window.location.href="solicitarReserva";
            }
            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="show()"></body>
</html>
