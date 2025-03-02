<%--
  Created by IntelliJ IDEA.
  User: ShopZone
  Date: 12/25/2024
  Time: 3:19 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.ConnectionManager" %>
<%@ page import="java.sql.Timestamp" %>
<%
    String isim = request.getParameter("isim");
    String email = request.getParameter("email");
    String konu = request.getParameter("konu");
    String mesaj = request.getParameter("mesaj");

    Connection connection = null;
    PreparedStatement statement = null;
    Timestamp tarih=new Timestamp(System.currentTimeMillis());
    try {
        // Veritabanı bağlantısı oluştur
        connection= ConnectionManager.getConnection();

        // Veriyi kaydet
        String sql = "INSERT INTO iletisim (email,isim, konu, mesaj, tarih) VALUES (?, ?, ?, ?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, isim);
        statement.setString(3, konu);
        statement.setString(4, mesaj);
        statement.setTimestamp(5, tarih);
        statement.executeUpdate();

        response.getWriter().println("<h3>Mesajınız başarıyla gönderildi. Teşekkür ederiz!</h3>");



    } catch (SQLException e) {
        e.printStackTrace();
        response.getWriter().println("<h3>Bir hata oluştu. Lütfen tekrar deneyin.</h3>");


    } finally {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }

%>

