<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="DAO.*" %>

<%
  String urun1Id = request.getParameter("urun1");
  String urun2Id = request.getParameter("urun2");
  UrunDAO urunDAO = new UrunDAO();

  Urun urun1 = urunDAO.UrunBilgisiCek(Integer.parseInt(urun1Id));
  Urun urun2 = urunDAO.UrunBilgisiCek(Integer.parseInt(urun2Id));
%>

<!DOCTYPE html>
<html lang="tr">
<head>
  <meta charset="UTF-8">
  <title>Ürün Karşılaştırma</title>
  <style>
    .urun-compare {
      display: flex;
      justify-content: space-between;
    }
    .urun {
      width: 45%;
      border: 1px solid #ccc;
      padding: 10px;
    }
  </style>
</head>
<body>
<h1>Ürün Karşılaştırma</h1>
<div class="urun-compare">
  <div class="urun">
    <h2><%= urun1.getUrunAdi() %></h2>
    <p><b>Fiyat:</b> <%= urun1.getFiyat() %> TL</p>
    <p><b>Marka:</b> <%= urun1.getKategori() %></p>
    <p><b>Açıklama:</b> <%= urun1.getStok() %></p>
  </div>
  <div class="urun">
    <h2><%= urun2.getUrunAdi() %></h2>
    <p><b>Fiyat:</b> <%= urun2.getFiyat() %> TL</p>
    <p><b>Marka:</b> <%= urun2.getKategori() %></p>
    <p><b>Açıklama:</b> <%= urun2.getStok() %></p>
  </div>
</div>
</body>
</html>
