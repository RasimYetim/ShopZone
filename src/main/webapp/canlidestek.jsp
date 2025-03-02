<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 14.12.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.CanliDestekDAO" %>
<%@ page import="DAO.CanliDestek" %>

<%@ page import="DAO.*" %>
<%@ page import="DAO.UrunDAO" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.Sepet" %>
<%

  Kullanici aktif = (Kullanici) session.getAttribute("aktif");

  List<CanliDestek> mesajlar;
  CanliDestekDAO destekDAO = new CanliDestekDAO(ConnectionManager.getConnection());
String admin=(String) session.getAttribute("admin");

  if (aktif != null && aktif.getUsername() != null) {
    // Eğer kullanıcı giriş yaptıysa, onun mesajlarını getir
    mesajlar = destekDAO.mesajlariGetirByKullaniciId(aktif.getUsername());
  } else {
    // Eğer kullanıcı giriş yapmadıysa, anonim kullanıcı için mesajları getir
    mesajlar = destekDAO.mesajlariGetirByKullaniciId("misafir");
  }
%>



<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ShopZone</title>
  <link rel="stylesheet" href="deneme.css">

</head>
<body>
<!-- Header -->
<header class="header">
  <div class="logo" > <a href="index.jsp" class="logo"> ShopZone </a> </div>
  <div class="search-bar">
    <input type="text" placeholder="Ürün, kategori veya marka ara">
    <button>🔍</button>
  </div>
  <div class="user-actions">
    <%if(aktif!=null){ %><h2 class="kullanici">Merhaba, <%= aktif.getUsername() %>! </h2>
    <span class="login"><button class="button" onclick="location.href='sepet.jsp'">🛒 Sepetim</button></span> <span class="button" onclick="location.href='LogoutServlet'">Çıkış yap</span>
    <%} else{%>
    <span class="login"> <button class="button" onclick="location.href='kayitKontrol.jsp'">👤 Giriş yap</button></span><%}%>
  </div>
</header>



<!-- Main Content -->
<main class="main-content" >

  <h1>Canlı Destek</h1>

  <!-- Mesaj Gönderme Formu -->
  <form action="CanliDestekServlet" method="post">
    <label for="mesaj">Mesajınız:</label><br>
    <textarea name="mesaj" id="mesaj" rows="4" cols="50" required></textarea><br><br>
    <%if(aktif==null && admin!=null ){%>
    <input type="text" readonly id="kullanici" name="admin" value="admin">
    <%}else{%>
    <%if(aktif==null){%>
    <input type="text" readonly id="kullanici" name="kullanici" value="misafir">
    <%}else{%>
    <input type="text" readonly id="kullanici" name="kullanici2" value="<%=aktif.getUsername()%>" ><%}}%>
    <button type="submit">Gönder</button>
  </form>

  <!-- Gelen Mesajlar -->
  <h2>Gelen Mesajlar:</h2>
  <ul>
    <%
      mesajlar = (List<CanliDestek>) request.getAttribute("mesajlar");
      if (mesajlar != null && !mesajlar.isEmpty()) {
        for (CanliDestek mesaj : mesajlar) {
    %>
    <li><strong><%= mesaj.getKullaniciId() %>:</strong> <%= mesaj.getMesaj()%><%=mesaj.getZaman()%></li>
    <%
      }
    } else {
    %>
    <li>Henüz mesaj yok.</li>
    <%
      }
    %>
  </ul>



</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>
