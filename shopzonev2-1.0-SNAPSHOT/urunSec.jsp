<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 30.12.2024
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="DAO.*" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ürün Seç</title>
  <link rel="stylesheet" href="deneme.css">
</head>
<body>
<header>
  <h1>Ürün Düzenleme</h1>
</header>
<main>
  <h2>Düzenlemek İstediğiniz Ürünü Seçin</h2>
  <table border="1">
    <thead>
    <tr>
      <th>Ürün ID</th>
      <th>Ürün Adı</th>
      <th>Stok</th>
      <th>Fiyat</th>
      <th>Kategori</th>
      <th>Resim</th>
      <th>İşlem</th>
    </tr>
    </thead>
    <tbody>
    <%
      // Ürünleri veritabanından çek
      UrunDAO urunDAO = new UrunDAO();
      List<Urun> urunler = urunDAO.getTumUrunler();

      // Ürünleri listele
      for (Urun urun : urunler) {
    %>
    <tr>
      <td><%= urun.getUrunId() %></td>
      <td><%= urun.getUrunAdi() %></td>
      <td><%= urun.getStok() %></td>
      <td><%= urun.getFiyat() %> ₺</td>
      <td><%= urun.getKategori() %></td>
      <td>
        <%
          if (urun.getResim() != null) {
            String base64Image = Base64.getEncoder().encodeToString(urun.getResim());
        %>
        <img src="data:image/png;base64,<%= base64Image %>" alt="Ürün Resmi" width="50">
        <% } else { %>
        <span>Resim Yok</span>
        <% } %>
      </td>
      <td>
        <a href="urunDuzenle.jsp?urunId=<%= urun.getUrunId() %>">Düzenle</a>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</main>
<footer>
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>

