<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adres Ekle</title>
</head>
<body>
<h1>Adres Ekle</h1>
<form action="AdresEkleServlet" method="post">
    <label for="adres">Adres:</label>
    <textarea name="adres" id="adres" rows="4" cols="50" required></textarea><br><br>
    <button type="submit">Adres Ekle</button>
</form>

<!-- Mesaj Gösterimi -->
<%
    String mesaj = (String) request.getAttribute("mesaj");
    if (mesaj != null) {
%>
<p><%= mesaj %></p>
<%
    }
%>
</body>
</html>
