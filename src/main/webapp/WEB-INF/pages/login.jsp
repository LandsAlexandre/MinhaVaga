<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- 
    Document   : login
    Created on : 08/06/2018, 15:31:22
    Author     : landerson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login-Cliente</title>
    </head>
    <body>
        <form action="login" method="post">
            Login: <input type="text" name="email" /> <br /> 
            Senha: <input type="password" name="senha" /> <br />
            <input type="submit" value="Entrar" /> 
        </form>
    </body>
</html>

