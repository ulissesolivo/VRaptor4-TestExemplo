<%-- 
    Document   : edita
    Created on : 20/04/2016, 23:30:40
    Author     : Ulisses Olivo
    E-mail     : ulissesolivo@gmail.com
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${empty usuario.id ? 'Inserindo' : 'Editando'} usuario</title>
  </head>
  <body>
    <%@include file="../../menu.jsp" %>
    <form action="<c:url value="/usuario/salvar" />" enctype="application/x-www-form-urlencoded" method="post">
      <h2>${empty usuario.id ? 'Inserindo' : 'Editando'} usuário</h2>
      <c:if test="${not empty errors}">
        <ul class="error-messages">
          <c:forEach var="error" items="${errors}">
            <li class="${error.category}">${error.message}</li>
            </c:forEach>
        </ul>
      </c:if>
      <table>
        <tbody>
          <tr>
            <td>Id</td>
            <td>${usuario.id}<input type="hidden" name="usuario.id" value="${usuario.id}" readonly="readonly" /></td>
          </tr>
          <tr>
            <td>Nome</td>
            <td><input type="text" name="usuario.nome" value="${usuario.nome}" style="width: 250px;" /></td>
          </tr>
          <tr>
            <td>E-mail de login</td>
            <td><input type="email" name="usuario.login" value="${usuario.login}" style="width: 250px;" /></td>
          </tr>
          <tr>
            <td>Senha de acesso</td>
            <td><input type="password" name="usuario.senha" /></td>
          </tr>
          <tr>
            <td style="vertical-align: top;">Senha confirmação</td>
            <td><input type="password" name="usuario.senhaConfirmacao" /><div>A senha deve conter no mínimo 10 caracteres</div></td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <td>&nbsp;</td>
            <td><button type="button" onclick="window.history.back();">Cancelar</button></td>
            <td><button type="submit">Salvar</button></td>
          </tr>
        </tfoot>
      </table>
    </form>
  </body>
</html>
