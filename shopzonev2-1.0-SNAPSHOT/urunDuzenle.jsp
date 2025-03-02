<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 30.12.2024
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.*" %>

<%
  int urunId = Integer.parseInt(request.getParameter("urunId"));
  UrunDAO urunDAO = new UrunDAO();
  Urun urun = urunDAO.UrunBilgisiCek(urunId);
%>

<!DOCTYPE html>
<html lang="tr">
<head>
  <meta charset="UTF-8">
  <title>Ürün Düzenle</title>
  <link rel="stylesheet" href="deneme.css">
</head>
<body>
<header class="header">
  <div class="logo"><a href="index.jsp">ShopZone</a></div>
</header>

<main class="main-content">
  <h2>Ürün Düzenle</h2>
  <button class="button" onclick="location.href='adminindex.jsp'">Önceki Menü</button><br><br>
  <form action="UrunDuzenleServlet" method="post" enctype="multipart/form-data" class="form">
    <input type="hidden" name="urunId" value="<%= urun.getUrunId() %>">
    <label>Ürün Adı:</label>
    <input type="text" name="urunAdi" value="<%= urun.getUrunAdi() %>" required><br><br>

    <label>Stok:</label>
    <input type="number" name="stok" value="<%= urun.getStok() %>" required><br><br>

    <label>Fiyat:</label>
    <input type="number" step="0.01" name="fiyat" value="<%= urun.getFiyat() %>" required><br><br>

    <label>Ürün Resmi (Opsiyonel):</label>
    <input type="file" name="resim" accept="image/*"><br><br>

    <button type="submit">Ürünü Güncelle</button>
  </form>
  <h4>Ürüne yeni resim ekle</h4>

  <form action="UrunResimEkleServlet" method="post" enctype="multipart/form-data">

    <input type="number" name="urunId" value="<%=urun.getUrunId()%>" readonly><br><br>

    <label>Ürün Resimleri:</label>
    <input type="file" name="resimler" accept="image/*" multiple required><br><br>

    <button type="submit">Resimleri Ekle</button>
  </form>

</main>

<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>

