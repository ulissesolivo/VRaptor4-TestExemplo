<%-- 
    Document   : lista
    Created on : 20/04/2016, 21:58:04
    Author     : Ulisses Olivo
    E-mail     : ulissesolivo@gmail.com
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de usuários</title>
    <style type="text/css">
      .table {
        border-collapse: collapse;
        width: 100%;
      }
      .table th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
      }
      .table tbody tr:hover{
        background-color:#f5f5f5
      }
    </style>
  </head>
  <body>
    <%@include file="../../menu.jsp" %>
    <h2>Lista de usuários</h2>
    <c:if test="${not empty errors}">
      <ul class="error-messages">
        <c:forEach var="error" items="${errors}">
          <li class="${error.category}">${error.message}</li>
          </c:forEach>
      </ul>
    </c:if>
    <form action="<c:url value="/usuario/listar" />" method="post" enctype="application/x-www-form-urlencoded">
      <table>
        <tbody>
          <tr>
            <td><input type="text" name="texto" value="${texto}" /></td>
            <td><button type="submit">Pesquisar</button></td>
          </tr>
        </tbody>
      </table>
    </form>
    <table class="table">
      <thead>
        <tr>
          <th colspan="2"><a href="<c:url value="/usuario/editar" />">Novo</a></th>
          <th>Nome</th>
          <th>Login</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${usuarios}" var="usuario">
          <tr>
            <td><a href="<c:url value="/usuario/editar/${usuario.id}" />">Editar</a></td>
            <td><a href="<c:url value="/usuario/excluir/${usuario.id}" />">Excluir</a></td>
            <td>${usuario.nome}</td>
            <td>${usuario.login}</td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="3">${usuarios.size() == 0 ? 'Nenhum' : usuarios.size()} usuário${usuarios.size() > 1 ? 's' : ''}</td>
        </tr>
      </tfoot>
    </table>
  </body>
</html>
