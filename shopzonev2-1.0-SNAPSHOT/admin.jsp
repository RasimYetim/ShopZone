<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 15.12.2024
  Time: 02:33
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

        <form action="admin" method="post" class="form">
            <h2 class="yonetici">Merhaba sayın yönetici. Lütfen bilgilerinizi girin.</h2>
            <label for="username">Kullanici Adiniz:</label>
            <input type="text" id="username" name="username" required> <br><br>
            <label for="password">Sifreniz</label>
            <input type="password" id="password" name="password" required> <br><br>
            <button type="submit" class="button"> Giriş</button>
            </form>

</main>

<!-- Footer -->
<footer class="footer">
    <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>

