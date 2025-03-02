<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.*" %>
<%@ page import="DAO.SepetDAO" %>
<%@ page import="DAO.SiparisDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>

<%
    // Aktif kullanıcıyı session'dan al
    Kullanici aktif = (Kullanici) session.getAttribute("aktif");
    if (aktif == null) {
        response.sendRedirect("login.jsp"); // Kullanıcı giriş yapmamışsa login sayfasına yönlendir
        return; // Bu noktadan sonra işlem yapılmaz
    }

    // Sepet verilerini al
    SepetDAO sepetDAO = new SepetDAO(ConnectionManager.getConnection());
    List<SepetDAO.UrunSepet> urunListesi = sepetDAO.SepetiGetir(aktif.getUsername());
    if (urunListesi == null || urunListesi.isEmpty()) {
        response.getWriter().println("<h3>Sepetiniz boş. Lütfen ürün ekleyin.</h3>");
        return;
    }

    Timestamp zaman = new Timestamp(System.currentTimeMillis()); // Sipariş zamanı

    // Sipariş DAO
    SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection());

    // Toplam fiyat hesapla
    double toplamFiyat = 0;
    for (SepetDAO.UrunSepet urun : urunListesi) {
        toplamFiyat += urun.fiyat * urun.adet;
    }

    // Sipariş numarasını oluştur (Basit bir rastgele sipariş numarası)
    int siparisNo = (int) (Math.random() * 10000);

    // Siparişi eklemek için hazırlık
    if (request.getParameter("siparisTamamla") != null) {
        String teslimatTuru = request.getParameter("teslimatTuru");
        String odemeTuru = request.getParameter("odemeTuru");

        // Sipariş nesnesini oluştur
        Siparis siparis = new Siparis(siparisNo, toplamFiyat, aktif.getUsername(), zaman, teslimatTuru, odemeTuru);

        // Sepet ürünlerini siparişe ekle
        for (SepetDAO.UrunSepet urun : urunListesi) {
            siparis.getUrunIds().add(urun.urunId); // Ürün ID'lerini ekle
            siparis.getUrunAdets().add(urun.adet); // Ürün adetlerini ekle
        }

        try {
            // Siparişi veritabanına ekle
            siparisDAO.siparisEkle(siparis);

            // Sepetteki ürünleri sıfırla
            for (SepetDAO.UrunSepet urun : urunListesi) {
                sepetDAO.UpdateAdet(aktif.getUsername(), urun.urunId, 0);
            }
            response.getWriter().println("<h3>Siparişiniz başarıyla tamamlandı!</h3>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Bir hata oluştu: " + e.getMessage() + "</h3>");
        }
    }
%>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopZone - Sipariş Tamamla</title>
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
        <span class="button">🛒 Sepetim</span>
        <span class="button"><a href="logout.jsp">Çıkış yap</a></span>
    </div>
</header>

<!-- Main Content -->
<main class="main-content">
    <h1>Siparişinizi Tamamlayın</h1>
    <div class="carousel">
        <div class="container">
            <% if (urunListesi.isEmpty()) { %>
            <p>Sepetinizde hiç ürün yok.</p>
            <% } else { %>
            <h3>Sepetinizdeki Ürünler:</h3>
            <table class="cart-table">
                <thead>
                <tr>
                    <th>Ürün Adı</th>
                    <th>Adet</th>
                    <th>Fiyat</th>
                    <th>Toplam</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (SepetDAO.UrunSepet urun : urunListesi) {
                        double urunToplamFiyat = urun.fiyat * urun.adet;
                %>
                <tr>
                    <td><%= urun.urunAdi %></td>
                    <td><%= urun.adet %></td>
                    <td><%= urun.fiyat %> TL</td>
                    <td><%= urunToplamFiyat %> TL</td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <!-- Sepet Toplamı -->
            <div class="cart-summary">
                <h3>Toplam Fiyat: <%= toplamFiyat %> TL</h3>
            </div>

            <!-- Teslimat ve Ödeme Seçenekleri -->
            <h3>Teslimat ve Ödeme Seçeneklerini Seçin</h3>
            <form method="post">
                <label for="teslimatTuru">Teslimat Türü:</label>
                <select name="teslimatTuru" id="teslimatTuru">
                    <option value="Standart">Standart</option>
                    <option value="Express">Express</option>
                </select>
                <br><br>

                <label for="odemeTuru">Ödeme Türü:</label>
                <select name="odemeTuru" id="odemeTuru">
                    <option value="Kapıda Kredi">Kapıda Kredi</option>
                    <option value="Kapıda Kart">Kapıda Kart</option>
                </select>
                <br><br>

                <button type="submit" name="siparisTamamla" class="button">Siparişi Tamamla</button>
            </form>
            <% } %>
        </div>
    </div>
</main>

<!-- Footer -->
<footer class="footer">
    <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>

</body>
</html>
