<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 28.12.2024
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.UrunDAO" %>
<%@ page import="DAO.Urun" %>
<%@ page import="DAO.Kullanici" %>

<% Kullanici aktif = (Kullanici) session.getAttribute("aktif");
    String arama = request.getParameter("arama");
    List<Urun> urunler = new UrunDAO().urunAra(arama);
%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopZone</title>
    <link rel="stylesheet" href="deneme.css">
    <script>
        function digerislemler() {
            const islemListesi = document.getElementById("islemListesi");

            // Liste görünümünü değiştir (gizle/göster)
            if (islemListesi.style.display === "none" || islemListesi.style.display === "") {
                islemListesi.style.display = "block";
            } else {
                islemListesi.style.display = "none";
            }
        }
        document.addEventListener('click', function (event) {
            const islemListesi = document.getElementById("islemListesi");
            const islemButon = document.getElementById("islemButon");

            // Eğer tıklanan yer "islemListesi" veya "islemButon" değilse, listeyi gizle
            if (!islemListesi.contains(event.target) && !islemButon.contains(event.target)) {
                islemListesi.style.display = "none";
            }
        });
    </script>
</head>
<body>
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
        <span class="button" onclick="digerislemler()">Diğer işlemler</span>

        <div id="islemListesi" class="islem-listesi">
            <%if(aktif!=null){%>
            <a href="siparisler.jsp">Siparişlerim</a>
            <a href="ayarlar.jsp">Ayarlar</a><%}%>
            <a href="canlidestek.jsp">Canlı Destek</a>
            <a href="iletisim.jsp">İletişim</a>

        </div>
        <% if(aktif==null){%>
        <span class="login"> <button class="button" onclick="location.href='kayitKontrol.jsp'">👤 Giriş yap</button></span><%}%>
    </div>
</header>

<h2>Arama Sonuçları</h2>
<%
    if (urunler != null && !urunler.isEmpty()) {
        for (Urun urun : urunler) {
%>
<div>
    <a href="urunDetay.jsp?urunId=<%= urun.getUrunId() %>">
        <div class="product-card">
            <img src="data:image/jpeg;base64,<%= urun.getResimBase64() %>" alt="<%= urun.getUrunAdi() %>">
            <h3><%= urun.getUrunAdi() %></h3>
            <p>Kategori: <%= urun.getKategori() %></p>
            <span><%= urun.getFiyat() %> TL</span>
        </div>
    </a>
</div>
<%
    }
} else {
%>
<p>Sonuç bulunamadı.</p>
<%
    }
%>
</body>
