<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Cliente</title>
    </head>
    <body>
        <form action="cliente" method="POST">
            Nome: <input type="text" name="nome" /><br />
            E-mail: <input type="text" name="email" /><br />
            CPF: <input type="text" name="cpf" /><br />
            Data de Nascimento: <input type="text" name="datanascimento" /><br />
            Senha: <input type="password" name="senha" size="20" maxlength="15" /><br />
            <input type="checkbox" name="cadastrarCartao" />Desejo Cadastrar Cartão <br />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>