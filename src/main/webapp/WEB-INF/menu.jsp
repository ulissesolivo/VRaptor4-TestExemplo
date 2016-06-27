<%-- 
    Document   : menu
    Created on : 20/04/2016, 22:05:19
    Author     : Ulisses Olivo
    E-mail     : ulissesolivo@gmail.com
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table style="width: 100%;">
  <thead>
    <tr>
      <th><a href="<c:url value="/"/>">Início</a></th>
      <th><a href="<c:url value="/usuario/listar"/>">Usuários</a></th>
    </tr>
  </thead>
</table>