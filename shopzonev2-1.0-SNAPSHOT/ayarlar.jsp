<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.*" %>
<%@ page import="java.util.*" %>

<%
  // Kullanıcı oturum kontrolü
  Kullanici aktif = (Kullanici) session.getAttribute("aktif");
  if (aktif == null) {
    response.sendRedirect("login.jsp"); // Eğer kullanıcı giriş yapmamışsa login sayfasına yönlendir
    return;
  }

  // Kullanıcı bilgilerini al
  KullaniciDAO kullaniciDAO = new KullaniciDAO(ConnectionManager.getConnection());
  Kullanici kullanici = null;
  try {
    List<Kullanici> kullanicilar = kullaniciDAO.tumKullanicilariAl();
    for (Kullanici k : kullanicilar) {
      if (k.getUsername().equals(aktif.getUsername())) {
        kullanici = k;
        break;
      }
    }
  } catch (SQLException e) {
    e.printStackTrace();
  }

  // Güncelleme işlemi
  if (request.getParameter("guncelle") != null) {
    String ad = request.getParameter("ad");
    String soyad = request.getParameter("soyad");
    String sifre = request.getParameter("sifre");

    // Kullanıcıyı güncelle
    kullanici.setAd(ad);
    kullanici.setSoyad(soyad);
    kullanici.setSifre(sifre);

    try {
      kullaniciDAO.updateKullanici(kullanici); // Güncellenen bilgileri veritabanına kaydet
      out.println("<script>alert('Bilgileriniz başarıyla güncellendi!'); window.location='ayarlar.jsp';</script>");
    } catch (SQLException e) {
      e.printStackTrace();
      out.println("<script>alert('Güncellenirken bir hata oluştu!');</script>");
    }
  }
%>

<!DOCTYPE html>
<html lang="tr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ShopZone - Hesap Ayarları</title>
  <link rel="stylesheet" href="deneme.css">
</head>
<body>

<!-- Header -->
<header class="header">
  <div class="logo">
    <a href="index.jsp">ShopZone</a>
  </div>
  <div class="user-actions">
    <h2 class="kullanici">Merhaba, <%= aktif.getUsername() %>!</h2>
    <span class="button"><a href="siparisler.jsp">Siparişlerim</a></span>
    <span class="button"><a href="logout.jsp">Çıkış yap</a></span>
  </div>
</header>

<!-- Main Content -->
<main class="main-content">
  <h1>Hesap Ayarları</h1>

  <% if (kullanici != null) { %>
  <form method="post">
    <div class="form-group">
      <label for="ad">Ad</label>
      <input type="text" id="ad" name="ad" value="<%= kullanici.getAd() %>" required>
    </div>

    <div class="form-group">
      <label for="soyad">Soyad</label>
      <input type="text" id="soyad" name="soyad" value="<%= kullanici.getSoyad() %>" required>
    </div>

    <div class="form-group">
      <label for="sifre">Şifre</label>
      <input type="password" id="sifre" name="sifre" value="<%= kullanici.getSifre() %>" required>
    </div>

    <button type="submit" name="guncelle" class="button">Bilgileri Güncelle</button>
  </form>
  <% } else { %>
  <p>Hesap bilgileri yüklenemedi.</p>
  <% } %>
</main>

<!-- Footer -->
<footer class="footer">
  <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>

</body>
</html>
