<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 30.12.2024
  Time: 03:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.SiparisDAO" %>
<%@ page import="DAO.Siparis" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.ConnectionManager" %>
<%@ page session="true" %>
<%
  // Kullanıcıyı kontrol et
  Kullanici aktif = (Kullanici) session.getAttribute("aktif");
  if (aktif == null) {
    response.sendRedirect("login.jsp"); // Eğer kullanıcı giriş yapmamışsa login sayfasına yönlendir
    return;
  }

  String siparisNoStr = request.getParameter("siparisNo");
  if (siparisNoStr == null) {
    response.sendRedirect("siparislerim.jsp"); // Sipariş numarası yoksa geri yönlendir
    return;
  }

  int siparisNo = Integer.parseInt(siparisNoStr);
  SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection());
  Siparis siparis = siparisDAO.siparisGetirById(siparisNo);
%>

<!DOCTYPE html>
<html lang="tr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sipariş Detayları</title>
</head>
<body class="main-content">
<h2>Sipariş No: <%= siparis.getSiparisNo() %></h2>
<p><strong>Toplam Fiyat:</strong> <%= siparis.getToplamFiyat() %> TL</p>
<p><strong>Sipariş Tarihi:</strong> <%= siparis.getSipariszaman() %></p>
<p><strong>Sipariş Durumu:</strong> <%= siparis.getSiparisdurum() %></p>

<h3>Ürünler:</h3>
<table border="1" >
  <thead>
  <tr>
    <th>Ürün ID</th>
    <th>Adet</th>
  </tr>
  </thead>
  <tbody>
  <%
    List<Integer> urunIds = siparis.getUrunIds();
    List<Integer> urunAdets = siparis.getUrunAdets();

    for (int i = 0; i < urunIds.size(); i++) {
  %>
  <tr>
    <td><%= urunIds.get(i) %></td>
    <td><%= urunAdets.get(i) %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<a href="siparislerim.jsp">Geri</a>
</body>
</html>
