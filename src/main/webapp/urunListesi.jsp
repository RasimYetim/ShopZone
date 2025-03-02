<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 30.12.2024
  Time: 05:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="DAO.*" %>
<%@ page import="DAO.UrunDAO" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.Sepet" %>
<%
  Kullanici aktif = (Kullanici) session.getAttribute("aktif");

%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ShopZone</title>
  <link rel="stylesheet" href="deneme.css">
  <script>
    function toggleIslemListesi() {
      const islemListesi = document.getElementById("islemListesi");

      // Listeyi gizle/göster
      if (islemListesi.style.display === "none" || islemListesi.style.display === "") {
        islemListesi.style.display = "block";
      } else {
        islemListesi.style.display = "none";
      }
    }

    // Listeyi otomatik kapatmak için sayfanın herhangi bir yerine tıklanınca kontrol
    document.addEventListener('click', function (event) {
      const islemListesi = document.getElementById("islemListesi");
      const islemButon = document.getElementById("islemButon");

      if (!islemListesi.contains(event.target) && !islemButon.contains(event.target)) {
        islemListesi.style.display = "none";
      }
    });
  </script>
</head>
<body>
<!-- Header -->
<header class="header">
  <div class="logo" > <a href="index.jsp" class="logo"> ShopZone </a> </div>
  <div class="search-bar">
    <form action="aramaSonuc.jsp" method="get">
      <input type="text" name="arama" placeholder="Arama yap..." />
      <button type="submit">Ara</button>
    </form>

  </div>
  <div class="user-actions">
    <%if(aktif!=null){ %><h2 class="kullanici">Merhaba, <%= aktif.getUsername() %>! </h2>
    <span class="login"><button class="button" onclick="location.href='sepet.jsp'">🛒 Sepetim</button></span> <span class="button" onclick="location.href='LogoutServlet'">Çıkış yap</span>
    <%}%>
    <button id="islemButon" onclick="toggleIslemListesi()" class="button">Diğer İşlemler</button>

    <div id="islemListesi" class="islem-listesi">
      <%if(aktif!=null){%>
      <a href="siparislerim.jsp">Siparişlerim</a>
      <a href="ayarlar.jsp">Ayarlar</a><%}%>
      <a href="canlidestek.jsp">Canlı Destek</a>
      <a href="iletisim.jsp">İletişim</a>

    </div>
    <% if(aktif==null){%>
    <span class="login"> <button class="button" onclick="location.href='kayitKontrol.jsp'">👤 Giriş yap</button></span><%}%>

  </div>

</header>

<!-- Navigation -->
<nav class="navbar">
  <ul>
    <li><a href="kategoriurun.jsp?kategori=Elektronik" >Elektronik</a></li>
    <li><a href="kategoriurun.jsp?kategori=Moda">Moda</a></li>
    <li><a href="kategoriurun.jsp?kategori=Ev-Yaşam">Ev, Yaşam</a></li>
    <li><a href="kategoriurun.jsp?kategori=Oto-Bahçe">Oto, Bahçe</a></li>
    <li><a href="kategoriurun.jsp?kategori=Anne-Bebek">Anne, Bebek</a></li>
    <li><a href="kategoriurun.jsp?kategori=Spor-Outdoor">Spor, Outdoor</a></li>
    <li><a href="kategoriurun.jsp?kategori=Kozmetik">Kozmetik</a></li>
    <li><a href="kategoriurun.jsp?kategori=Süpermarket">Süpermarket</a></li>
    <li><a href="kategoriurun.jsp?kategori=Kitap-Müzik">Kitap, Müzik</a></li>
  </ul>
</nav>



<!-- Main Content -->
<main class="main-content" >
<%UrunDAO urunDAO = new UrunDAO();
  List<Urun> urunListesi = urunDAO.getTumUrunler();
  String mevcutUrunId = request.getParameter("urunId");%>
  <div class="carousel">
  <div class="container">
    <h1>Ürün Listesi</h1>
    <ul>
      <% for (Urun urun : urunListesi) {
        if (urun.getUrunId()!=Integer.parseInt(mevcutUrunId)) { %>
      <li>
        <%= urun.getUrunAdi() %> - <%= urun.getFiyat() %> TL
        <a href="urunKarsilastir.jsp?urun1=<%= mevcutUrunId %>&urun2=<%= urun.getUrunId() %>" target="_parent">Seç</a>
      </li>
      <% } } %>
    </ul>

  </div>
  </div>
</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>
