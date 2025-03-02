<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="DAO.*" %>
<%@ page import="java.util.Base64" %>

<%
  HttpSession sessionObj = request.getSession();
  Kullanici aktif = (Kullanici) sessionObj.getAttribute("aktif");
  String admin = (String) sessionObj.getAttribute("admin"); // Admin bilgisi
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
  <div class="logo"> <a href="index.jsp"> ShopZone </a> </div>
  <div class="search-bar">
    <input type="text" placeholder="Ürün, kategori veya marka ara">
    <button>🔍</button>
  </div>
  <div class="user-actions">
    <%if(aktif!=null){%>
    <h2 class="kullanici">Merhaba, <%= aktif.getUsername() %>! </h2>
    <span class="button">🛒 Sepetim</span> <span class="button"> Çıkış yap</span>
    <%} else{%>
    <span class="login">
        <button class="button" onclick="location.href='kayitKontrol.jsp'">👤 Giriş yap</button>
      </span>
    <%}%>
  </div>
</header>

<!-- Main Content -->
<main class="urundetay">
  <div class="urunbilgi">
    <%
      int urunId = Integer.parseInt(request.getParameter("urunId"));
      UrunDAO urunDAO = new UrunDAO();
      Urun urun = urunDAO.UrunBilgisiCek(urunId);
      request.setAttribute("urun", urun);
    %>

    <h1><%= urun.getUrunAdi() %></h1>
    <img src="data:image/jpeg;base64,<%= urun.getResimBase64() %>" alt="<%= urun.getUrunAdi() %>" style="width:300px;">
    <%
      List<byte[]> resimler = urunDAO.getUrunResimleri(urun.getUrunId());
    %>

    <h2>Ürün Resimleri</h2>
    <%
      for (byte[] resim : resimler) {
        String base64Image = Base64.getEncoder().encodeToString(resim);
    %>
    <img src="data:image/png;base64,<%= base64Image %>" alt="Ürün Resmi" width="100">
    <%
      }
    %>
    <a  href="urunKarsilastir.jsp">karsılastır</a>>
    <p><strong>Fiyat:</strong> <%= urun.getFiyat() %> TL</p>
    <p><strong>Kategori:</strong> <%= urun.getKategori() %></p>
    <p><strong>Stok:</strong> <%= urun.getStok() %></p>

    <% if(aktif!=null){%>
    <form action="SepeteEklee" method="post">
      <input type="hidden" name="urun_id" value="<%= urun.getUrunId() %>">
      <input type="hidden" name="kullanici_adi" value="<%= aktif.getUsername() %>">
      <button class="button" type="submit"> 🛒Sepete Ekle </button>
    </form>
    <%}%>

    <!-- Yıldız Ortalamasını Göster -->
    <%
      // Yorumlar üzerinden yıldız ortalamasını hesaplama
      List<Yorum> yorumlar = new YorumDAO(ConnectionManager.getConnection()).yorumlariGetir(urunId);
      double ortalamaYildiz = 0;
      int toplamYildiz = 0;
      int yorumSayisi = yorumlar.size();

      if (yorumSayisi > 0) {
        for (Yorum yorum : yorumlar) {
          toplamYildiz += yorum.getYildiz();
        }
        ortalamaYildiz = (double) toplamYildiz / yorumSayisi;
      }
    %>
    <p><strong>Yıldız Ortalaması:</strong> <%= String.format("%.1f", ortalamaYildiz) %> / 5</p>

    <% if (aktif != null) { %>
    <div class="yorum-ekle">
      <h3>Yorum Yapın</h3>
      <form action="YorumEkleServlet" method="post">
        <input type="hidden" name="urunId" value="<%= urun.getUrunId() %>">
        <input type="hidden" name="kullaniciAdi" value="<%= aktif.getUsername() %>">
        <label for="yorum">Yorum:</label>
        <textarea name="yorum" id="yorum" rows="4" required></textarea>
        <br>
        <label for="yildiz">Yıldız Verin:</label>
        <select name="yildiz" id="yildiz">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        <br>
        <button type="submit" class="button">Yorum Gönder</button>
      </form>
    </div>
    <% } %>

    <!-- Yorumları Filtreleme -->
    <div class="yorum-filtre">
      <h3>Yorumları Filtrele</h3>
      <form action="urunDetay.jsp" method="get">
        <input type="hidden" name="urunId" value="<%= urunId %>">
        <label for="yildiz-filtre">Yıldız:</label>
        <select name="yildiz-filtre" id="yildiz-filtre">
          <option value="0">Tüm Yorumlar</option>
          <option value="1">1 Yıldız</option>
          <option value="2">2 Yıldız</option>
          <option value="3">3 Yıldız</option>
          <option value="4">4 Yıldız</option>
          <option value="5">5 Yıldız</option>
        </select>
        <button type="submit" class="button">Filtrele</button>
      </form>
    </div>

    <!-- Yorumları Gösterme -->
    <%
      String yildizFiltreStr = request.getParameter("yildiz-filtre");
      int yildizFiltre = (yildizFiltreStr != null) ? Integer.parseInt(yildizFiltreStr) : 0;

      List<Yorum> yorumlarFiltreli;
      if (yildizFiltre > 0) {
        yorumlarFiltreli = new YorumDAO(ConnectionManager.getConnection()).yorumlariFiltrele(urunId, yildizFiltre);
      } else {
        yorumlarFiltreli = new YorumDAO(ConnectionManager.getConnection()).yorumlariGetir(urunId);
      }

      if (yorumlarFiltreli != null && !yorumlarFiltreli.isEmpty()) {
    %>
    <div class="yorumlar">
      <h3>Yorumlar</h3>
      <% for (Yorum yorum : yorumlarFiltreli) { %>
      <div class="yorum">
        <p><strong><%= yorum.getKullaniciAdi() %></strong> - <%= yorum.getYorumTarihi() %></p>
        <p><%= yorum.getYorum() %></p>
        <p><strong>Yıldız: </strong><%= yorum.getYildiz() %> / 5</p>

        <% if (yorum.getYanit() != null && !yorum.getYanit().isEmpty()) { %>
        <p><strong>Yanıt:</strong> <%= yorum.getYanit() %></p>
        <% } %>

        <!-- Admin'e özel yanıt ekleme formu -->
        <% if ("admin".equals(admin)) { %>
        <form action="YorumYanitEkleServlet" method="post">
          <input type="hidden" name="urun_id" value="<%= yorum.getUrunId() %>">
          <input type="hidden" name="kullanici_adi" value="<%= yorum.getKullaniciAdi() %>">
          <input type="hidden" name="yorum_tarihi" value="<%= yorum.getYorumTarihi() %>">
          <textarea name="yanit" required placeholder="Admin yanıtı yazın..."></textarea>
          <button type="submit">Yanıtla</button>
        </form>
        <% } %>
      </div>
      <% } %>
    </div>
    <% } else { %>
    <p>Filtreye uygun yorum bulunamadı.</p>
    <% } %>
  </div>
</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>
</body>
</html>
