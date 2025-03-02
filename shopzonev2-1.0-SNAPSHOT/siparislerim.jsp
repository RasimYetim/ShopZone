<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 30.12.2024
  Time: 03:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.SiparisDAO" %>
<%@ page import="DAO.Siparis" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.ConnectionManager" %>

<%@ page import="java.sql.Timestamp" %>
<%@ page session="true" %>
<%
    // Kullanıcıyı kontrol et
    Kullanici aktif = (Kullanici) session.getAttribute("aktif");
    if (aktif == null) {
        response.sendRedirect("login.jsp"); // Eğer kullanıcı giriş yapmamışsa login sayfasına yönlendir
        return;
    }

    // Sipariş DAO'yu oluştur ve siparişleri getir
    SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection());
    List<Siparis> siparisler = siparisDAO.siparisleriGetirKullanici(aktif.getUsername());
%>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Siparişlerim</title>
    <link rel="stylesheet" href="deneme.css">
</head>
<body>
<h2>Hoşgeldiniz, <%= aktif.getUsername() %>!</h2>
<h3>Siparişlerim</h3>

<table border="1">
    <thead>
    <tr>
        <th>Sipariş No</th>
        <th>Toplam Fiyat</th>
        <th>Sipariş Tarihi</th>
        <th>Sipariş Durumu</th>
        <th>Detaylar</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (siparisler != null && !siparisler.isEmpty()) {
            for (Siparis siparis : siparisler) {
    %>
    <tr>
        <td><%= siparis.getSiparisNo() %></td>
        <td><%= siparis.getToplamFiyat() %> TL</td>
        <td><%= siparis.getSipariszaman() %></td>
        <td><%= siparis.getSiparisdurum() %></td>
        <td>
            <a href="siparisDetay.jsp?siparisNo=<%= siparis.getSiparisNo() %>">Detaylar</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Henüz siparişiniz bulunmamaktadır.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>

