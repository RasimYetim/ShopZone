
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>

<%



    // Siparişleri getirme işlemi
    SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection());
    List<Siparis> siparisler = siparisDAO.siparisleriGetirTümü(); // Tüm siparişleri getir
%>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopZone - Tüm Siparişler</title>
    <link rel="stylesheet" href="deneme.css">
</head>
<body>

<!-- Header -->
<header class="header">
    <div class="logo">
        <a href="index.jsp">ShopZone</a>
    </div>

</header>

<!-- Main Content -->
<main class="main-content">
    <h1>Tüm Siparişler</h1>
    <table border="1">
        <thead>
        <tr>
            <th>Sipariş No</th>
            <th>Alan Kişi</th>
            <th>Sipariş Durumu</th>
            <th>Durumu Güncelle</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Siparis siparis : siparisler) {
        %>
        <tr>
            <td><%= siparis.getSiparisNo() %></td>
            <td><%= siparis.getAlanKisi() %></td>
            <td><%= siparis.getSiparisdurum() %></td>
            <td>
                <form action="siparisDurumGuncelle.jsp" method="post">
                    <input type="hidden" name="siparisNo" value="<%= siparis.getSiparisNo() %>">
                    <select name="siparisDurum">
                        <option value="Siparis alindi." <%= "Siparis alindi.".equals(siparis.getSiparisdurum()) ? "selected" : "" %>>Sipariş alındı</option>
                        <option value="Siparis hazirlaniyor." <%= "Siparis hazirlaniyor.".equals(siparis.getSiparisdurum()) ? "selected" : "" %>>Sipariş hazırlanıyor</option>
                        <option value="Siparis Kargoda." <%= "Siparis Kargoda.".equals(siparis.getSiparisdurum()) ? "selected" : "" %>>Sipariş Kargoda</option>
                        <option value="Siparis teslim edildi." <%= "Siparis teslim edildi.".equals(siparis.getSiparisdurum()) ? "selected" : "" %>>Sipariş teslim edildi</option>
                    </select>
                    <button type="submit">Güncelle</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</main>

<!-- Footer -->
<footer class="footer">
    <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
</footer>

</body>
</html>

