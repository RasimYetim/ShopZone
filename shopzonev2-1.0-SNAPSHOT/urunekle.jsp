<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 16.12.2024
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 15.12.2024
  Time: 02:33
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 14.12.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
  <div class="logo" > <a href="index.jsp"> ShopZone </a> </div>

</header>



<!-- Main Content -->
<main class="main-content" >

  <h2>Admin panel</h2>
  <button class="button" onclick="location.href='adminindex.jsp'">Önceki menü</button> <br>
  <form action="UrunEkleServlet" method="post" enctype="multipart/form-data" class="form">
    <label>Ürün Adı:</label>
    <input type="text" name="urunAdi" required><br><br>

    <label>Stok:</label>
    <input type="number" name="stok" required><br><br>

    <label>Fiyat:</label>
    <input type="number" step="0.01" name="fiyat" required><br><br>

    <label>Kategori:</label>
    <select name="kategori">
      <option value="Elektronik">Elektronik</option>
      <option value="Moda">Moda</option>
      <option value="Ev-Yaşam">Ev-Yaşam</option>
      <option value="Oto-Bahçe">Oto-Bahçe</option>
      <option value="Anne-Bebek">Anne-Bebek</option>
      <option value="Spor-Outdoor">Spor-Outdoor</option>
      <option value="Kozmetik">Kozmetik</option>
      <option value="Süpermarket">Süpermarket</option>
      <option value="Kitap-Müzik">Kitap-Müzik</option>
    </select><br><br>

    <label>Ürün Resmi:</label>
    <input type="file" name="resim" accept="image/*" required><br><br>

    <button type="submit">Ürünü Ekle</button>
  </form>



</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>


