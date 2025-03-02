<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="DAO.Yorum" %>
<html>
<head>
  <title>Yorum Ekle</title>
</head>
<body>
<h2>Yorum Ekle</h2>
<form action="YorumEkleServlet" method="post">
  <label for="urunId">Ürün ID:</label>
  <input type="text" id="urunId" name="urunId" required /><br/><br/>

  <label for="kullaniciAdi">Kullanıcı Adı:</label>
  <input type="text" id="kullaniciAdi" name="kullaniciAdi" required /><br/><br/>

  <label for="yorum">Yorum:</label><br/>
  <textarea id="yorum" name="yorum" rows="4" cols="50" required></textarea><br/><br/>

  <label for="yildiz">Yıldız (1-5):</label>
  <input type="number" id="yildiz" name="yildiz" min="1" max="5" required /><br/><br/>

  <label for="yanit">Yanıt (Opsiyonel):</label>
  <textarea id="yanit" name="yanit" rows="2" cols="50"></textarea><br/><br/>

  <input type="submit" value="Yorum Ekle" />
</form>
</body>
</html>
