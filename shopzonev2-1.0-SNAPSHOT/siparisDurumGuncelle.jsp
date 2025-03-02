<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="DAO.*" %>
<%@ page import="java.sql.*" %>

<%
    int siparisNo = Integer.parseInt(request.getParameter("siparisNo"));
    String yeniDurum = request.getParameter("siparisDurum");

    SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection());

    try {
        siparisDAO.siparisDurumGuncelle(siparisNo, yeniDurum); // Durumu güncelle
        response.sendRedirect("siparislistele.jsp"); // Listeleme sayfasına yönlendir
    } catch (SQLException e) {
        e.printStackTrace();

    }
%>

