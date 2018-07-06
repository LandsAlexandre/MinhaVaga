<%-- 
    Document   : solicitarReserva
    Created on : 13/06/2018, 15:49:26
    Author     : ISM
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="minhavagaweb.model.cdp.*"%>
<%@page import="minhavagaweb.model.cgd.VagaDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="minhavagaweb.model.cgd.EstacionamentoDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% EstacionamentoDAOImpl estacionamento = new EstacionamentoDAOImpl();
           List<Estacionamento> listaLocais = estacionamento.getAll();           
           List<String> tipos = new ArrayList();
           tipos.add("Comum");
           tipos.add("Deficiente");
           tipos.add("Idoso");
           tipos.add("Moto");
           %>
        <form name="formSelect" action="encontrarVaga" method="POST">
            <select name="selectLocal">
                <% for(Estacionamento local : listaLocais){ %>
                <option value="<%=local.getId()%>"><%=local.getNome()%></option>
                <%}%>
            </select>
            <br>
            <select name="selectTipo">
                <% for(int i=0; i<tipos.size();i++ ){ %>
                <option value="<%=i+1 %>"><%=tipos.get(i).toString()%></option>
                <%}%>
            </select>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
