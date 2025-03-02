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

<!-- Navigation -->
<nav class="navbar">
    <ul>
        <li><a href="#">Elektronik</a></li>
        <li><a href="#">Moda</a></li>
        <li><a href="#">Ev, Yaşam</a></li>
        <li><a href="#">Oto, Bahçe</a></li>
        <li><a href="#">Anne, Bebek</a></li>
        <li><a href="#">Spor, Outdoor</a></li>
        <li><a href="#">Kozmetik</a></li>
        <li><a href="#">Süpermarket</a></li>
        <li><a href="#">Kitap, Müzik</a></li>
    </ul>
</nav>




<!-- Main Content -->
<main class="main-content">

    <form action="KullaniciEkleServlet" method="post" class="form" >
        <h5>Alısverişin merkezine hoşgeldiniz. Lütfen kayıt olmak için tüm alanları doldurunuz.</h5>
        <label for="username">Kullanıcı Adı:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="ad">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ad:</label>
        <input type="text" id="ad" name="ad" required><br><br>

        <label for="soyad">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Soyad:</label>
        <input type="text" id="soyad" name="soyad" required><br><br>

        <label for="email">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;email:</label>
        <input type="text" id="email" name="email" required><br><br>

        <label for="tcNo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TC No:</label>
        <input type="number" id="tcNo" name="tcNo" required><br><br>

        <label for="sifre">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Şifre:</label>
        <input type="password" id="sifre" name="sifre" required><br><br>

        <label for="sehir">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Şehir:</label>
        <input type="text" id="sehir" name="sehir" required><br><br>

        <label for="dogumTarihi">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Doğum Tarihi:</label>
        <input type="date" id="dogumTarihi" name="dogumTarihi"><br><br>

        <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cinsiyet:</label><br>
        <input type="radio" id="erkek" name="cinsiyet" value="true">
        <label for="erkek">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Erkek</label><br>
        <input type="radio" id="kadin" name="cinsiyet" value="false">
        <label for="kadin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kadın</label><br><br>

        <input type="submit" value="KAYIT OL">
    </form>


</main>

<!-- Footer -->
<footer class="footer">
    <p>© 2024 MyShop - All Rights Reserved</p>
</footer>
</body>
</html>
