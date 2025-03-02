<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.ConnectionManager" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>İletişim Formları</title>
    <link rel="stylesheet" href="deneme.css">
</head>
<body>
<header class="header">
    <div class="logo" > <a href="index.jsp"> ShopZone </a> </div>

</header>
<main class="main-content">
    <button class="button" onclick="location.href='adminindex.jsp'">Önceki menü</button> <br>
<h1>İletişim Formları</h1>
<table>
    <thead>
    <tr>

        <th>İsim</th>
        <th>Email</th>
        <th>Konu</th>
        <th>Mesaj</th>
        <th>Zaman</th>
    </tr>
    </thead>
    <tbody>
    <%
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Veritabanı bağlantısını oluştur
            connection = ConnectionManager.getConnection();
            String sql = "SELECT  isim, email, konu, mesaj, tarih FROM iletisim ORDER BY tarih DESC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // Sonuçları tablo satırları olarak yazdır
            while (resultSet.next()) {

                String isim = resultSet.getString("isim");
                String email = resultSet.getString("email");
                String konu = resultSet.getString("konu");
                String mesaj = resultSet.getString("mesaj");
                Timestamp zaman = resultSet.getTimestamp("tarih");
    %>
    <tr>

        <td><%= isim %></td>
        <td><%= email %></td>
        <td><%= konu %></td>
        <td><%= mesaj %></td>
        <td><%= zaman %></td>
    </tr>
    <%
        }
    } catch (SQLException e) {
        e.printStackTrace();
    %>
    <tr>
        <td colspan="6">Veritabanından veri alınırken bir hata oluştu.</td>
    </tr>
    <%
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    %>
    </tbody>
</table>
</main>>
</body>
</html>
