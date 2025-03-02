
<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 14.12.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%@ page import="DAO.*" %>
<%@ page import="DAO.UrunDAO" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.Sepet" %>
<%
  Kullanici aktif = (Kullanici) session.getAttribute("aktif");

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
  <form action="iletisimislem.jsp" method="POST" class="form">
    <label for="isim">Adınız:</label>
    <%if(aktif!=null){%>
    <input type="text" id="isim"  name="isim" value="<%aktif.getAd();%>" readonly><br>
    <%}else{%>
    <input type="text" id="isim" name="isim" required><br>
    <%}%>

    <label for="email">Mail adresiniz:</label><br>
    <%if(aktif!=null){%>
    <input type="email" id="email"  name="email" value="<%aktif.getEmail();%>" readonly ><br>
    <%}else{%>
    <input type="email" id="email" name="email" required><br>
    <%}%>

    <label for="konu">Konu:</label><br>
    <input type="text" id="konu" name="konu" required><br>

    <label for="mesaj">Mesajınız:</label><br>
    <textarea id="mesaj" name="mesaj" rows="5" required></textarea><br>

    <button type="submit">Gönder</button>
  </form>

</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>
