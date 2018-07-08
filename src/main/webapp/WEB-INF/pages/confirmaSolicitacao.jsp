<%-- 
    Document   : confirma-solicitacao
    Created on : 16/06/2018, 15:15:13
    Author     : ISM
--%>

<%@page import="minhavagaweb.model.cdp.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function confirma() {
                if (confirm("Confirmar solicitação?"))
                {
                     window.location.href = "solicitacaoConfirmada";
                } else {
                    window.location.href = "home";
                }
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="confirma()" >
    </body>
</html>
