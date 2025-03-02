<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 14.12.2024
  Time: 01:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
<main class="main-content">

    <p style="color:red;">Tekrar hoşgeldiniz ! Lütfen şifrenizi girin.</p>
 <br>

  <h1>Kullanıcı Girişi</h1>
  <form action="giris" method="POST" class="form">
    <label for="email">E-Posta:</label>
    <input type="email" id="email" name="email"
           value="<%= (String) request.getSession().getAttribute("email") %>" readonly><br><br>


    <label for="sifre">Şifre:</label>
    <input type="password" id="sifre" name="sifre" required><br><br>

    <button type="submit" class="button">Giriş Yap</button> </form>
</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>

