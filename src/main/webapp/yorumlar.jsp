<%@ page import="java.util.List" %>
<%@ page import="DAO.Yorum" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Yorumlar</title>
</head>
<body>
<h2>Yorumlar</h2>
<%
    List<Yorum> yorumlar = (List<Yorum>) request.getAttribute("yorumlar");
    if (yorumlar != null && !yorumlar.isEmpty()) {
        for (Yorum yorum : yorumlar) {
%>
<div>
    <p><strong><%= yorum.getKullaniciAdi() %>:</strong> <%= yorum.getYorum() %></p>
    <p><strong>Yıldız:</strong> <%= yorum.getYildiz() %></p>
    <p><strong>Yanıt:</strong> <%= yorum.getYanit() == null ? "Yanıt yok" : yorum.getYanit() %></p>
    <p><strong>Yorum Tarihi:</strong> <%= yorum.getYorumTarihi() %></p>
    <hr />
</div>
<%
    }
} else {
%>
<p>Henüz yorum yapılmamış.</p>
<%
    }
%>
</body>
</html>
