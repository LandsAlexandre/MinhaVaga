<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 
    Document   : home
    Created on : 10/06/2018, 10:48:57
    Author     : landerson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body >
        <p>Bem vindo, ${nome}!<br /></p>
        
        
        <p>Uma nova reserva? Clique <a href="solicitarReserva">aqui</a>.<br />
        Visualizar seu histórico de reservas? Clique <a href="reservas">aqui</a>.<br />
        </p>
        
        
    </body>       
</html>
