    <%--
      Created by IntelliJ IDEA.
      User: rasim
      Date: 14.12.2024
      Time: 18:49
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="DAO.*" %>
    <%@ page import="DAO.SepetDAO" %>
    <%@ page import="DAO.SepetDAO.UrunSepet" %>
    <%@ page import="java.util.Base64" %>

    <%
        Kullanici aktif = (Kullanici) session.getAttribute("aktif");
        SepetDAO sepetDAO = new SepetDAO(ConnectionManager.getConnection());
        List<SepetDAO.UrunSepet> urunListesi = sepetDAO.SepetiGetir(aktif.getUsername());
        System.out.println(aktif.getUsername());
    %>

    <!DOCTYPE html>

    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ShopZone</title>
        <link rel="stylesheet" href="deneme.css">
        <script>
            function calculateTotal() {
                let total = 0;
                // Ürünlerin fiyat ve adet bilgilerini al
                document.querySelectorAll('.product-card').forEach(product => {
                    const priceElement = product.querySelector('.product-price');
                    const quantityInput = product.querySelector('input[type="text"]');

                    if (priceElement && quantityInput) {
                        const price = parseFloat(priceElement.textContent.replace(' TL', '').trim());
                        const quantity = parseInt(quantityInput.value);
                        total += price * quantity;  // Fiyat * Adet = Toplam fiyat
                    }
                });

                // Toplam tutarı DOM'da güncelle
                document.getElementById('total-price').textContent = total.toFixed(2) + ' TL';
            }

            // Sayfa yüklendikten sonra toplam tutarı hesapla
            window.onload = calculateTotal;
            function guncelleAdet(urunId, islem) {
                // Parametre kontrolü
                console.log("urunId:", urunId, "islem:", islem); // Debug için ekleme

                if (!urunId || isNaN(urunId)) {
                    console.error("Geçersiz urunId:", urunId);
                    alert("Bir hata oluştu. Lütfen sayfayı yenileyin.");
                    return;
                }

                const adetInput = document.getElementById('adet-' + urunId);
                if (!adetInput) {
                    console.error("Adet input elemanı bulunamadı: urunId=", urunId);
                    return;
                }

                let adet = parseInt(adetInput.value);
                if (isNaN(adet)) {
                    console.error("Adet değeri geçersiz:", adetInput.value);
                    alert("Adet bilgisi geçersiz. Lütfen sayfayı yenileyin.");
                    return;
                }

                // İşlem türüne göre adeti güncelle
                if (islem === 'artir') {
                    adet++;
                } else if (islem === 'azalt' && adet > 1) {
                    adet--;
                } else if (islem === 'azalt' && adet === 1) {
                    // Eğer adet 1 ve azalt düğmesine basıldıysa ürünü kaldırmayı teklif et
                    if (!confirm("Bu ürünü sepetinizden kaldırmak istiyor musunuz?")) {
                        return;
                    }
                    adet = 0;
                }

                // Sunucuya istek gönder
                fetch("adetGuncelle.jsp?urunId=" + urunId + "&yeniAdet=" + adet, {
                    method: 'GET',
                })
                    .then(response => {
                        if (response.ok) {
                            if (adet === 0) {
                                // Ürün kaldırıldıysa DOM'dan kaldır
                                const urunElement = document.getElementById('urun-' + urunId);
                                if (urunElement) {
                                    urunElement.remove();
                                }
                            } else {
                                // Adedi güncelle
                                adetInput.value = adet;
                            }
                            // Toplam tutarı yeniden hesapla
                            calculateTotal();
                        } else {
                            console.error("Sunucu hatası:", response.status, response.statusText);
                            alert('Adet güncellenirken bir hata oluştu.');
                        }
                    })
                    .catch(error => {
                        console.error("Fetch hatası:", error);
                        alert('Adet güncellenirken bir hata oluştu.');
                    });
            }

        </script>

        <script>
            function guncelleAdet(urunId, islem) {
                // Parametre kontrolü

                    console.log("urunId:", urunId, "islem:", islem); // Debug için ekleme


                if (!urunId || isNaN(urunId)) {
                    console.error("Geçersiz urunId:", urunId);
                    alert("Bir hata oluştu. Lütfen sayfayı yenileyin.");
                    return;
                }

                const adetInput = document.getElementById('adet-' + urunId);
                if (!adetInput) {
                    console.error("Adet input elemanı bulunamadı: urunId=", urunId);
                    return;
                }

                let adet = parseInt(adetInput.value);
                if (isNaN(adet)) {
                    console.error("Adet değeri geçersiz:", adetInput.value);
                    alert("Adet bilgisi geçersiz. Lütfen sayfayı yenileyin.");
                    return;
                }

                // İşlem türüne göre adeti güncelle
                if (islem === 'artir') {
                    adet++;
                } else if (islem === 'azalt' && adet > 1) {
                    adet--;
                } else if (islem === 'azalt' && adet === 1) {
                    // Eğer adet 1 ve azalt düğmesine basıldıysa ürünü kaldırmayı teklif et
                    if (!confirm("Bu ürünü sepetinizden kaldırmak istiyor musunuz?")) {
                        return;
                    }
                    adet = 0;
                }

                // Sunucuya istek gönder
                fetch("adetGuncelle.jsp?urunId=" + urunId + "&yeniAdet=" + adet, {
                    method: 'GET',
                })

                        .then(response => {
                        if (response.ok) {
                            if (adet === 0) {
                                // Ürün kaldırıldıysa DOM'dan kaldır
                                const urunElement = document.getElementById('urun-' + urunId);
                                if (urunElement) {
                                    urunElement.remove();
                                }
                            } else {
                                // Adedi güncelle
                                adetInput.value = adet;
                            }
                        } else {
                            console.error("Sunucu hatası:", response.status, response.statusText);
                            alert('Adet güncellenirken bir hata oluştu.');
                        }
                    })
                    .catch(error => {
                        console.error("Fetch hatası:", error);
                        alert('Adet güncellenirken bir hata oluştu.');
                    });
            }

        </script>


    </head>
    <body>
    <!-- Header -->
    <header class="header">
        <div class="logo" > <a href="index.jsp" class="logo"> ShopZone </a> </div>
        <div class="search-bar">
            <input type="text" placeholder="Ürün, kategori veya marka ara">
            <button>🔍</button>
        </div>
        <div class="user-actions">
            <%if(aktif!=null){%><h2 class="kullanici">Merhaba, <%= aktif.getUsername() %>! </h2>
            <span class="button">🛒 Sepetim</span> <span class="button"> Çıkış yap</span><%} else{%>
            <span class="login"> <button class="button" onclick="location.href='kayitKontrol.jsp'">👤 Giriş yap</button></span><%}%>
        </div>
    </header>


    <!-- Main Content -->
    <main class="main-content" >
        <h1 class="button">Sepetiniz:</h1>
        <div class="carousel">

        <div class="container">
            <% if (urunListesi.isEmpty()) { %>
            <p>Sepetinizde hiç ürün yok.</p>
            <% } else {
                for (UrunSepet urun : urunListesi) { %>
            <div class="product-card" id="urun-<%= urun.urunId %>">
                <img src="data:image/jpeg;base64,<%= urun.resimBase64 %>" alt="<%= urun.urunAdi %>">
                <h3><%= urun.urunAdi %></h3>
                <p>Kategori: <%= urun.kategori %></p>
                <div>
                    <button onclick="guncelleAdet(<%= urun.urunId %>, 'azalt')">-</button>
                    <input type="text" id="adet-<%= urun.urunId %>" value="<%= urun.adet %>" readonly>
                    <button onclick="guncelleAdet(<%= urun.urunId %>, 'artir')">+</button>
                </div>
                <span class="product-price"><%= urun.fiyat %> TL</span> <!-- Fiyat burada yer alacak -->
            </div>


            <!-- Sepet Tutarı Gösterimi -->
            <div class="cart-summary">
                <span>Toplam Tutar: <span id="total-price">0.00 TL</span></span>
            </div>

        </div>
            <% }
            } %>
        </div>
        <form action="siparisTamamla.jsp" method="POST">
            <button type="submit" class="button">Siparişi Tamamla</button>
        </form>
        </div>
    </main>

    <!-- Footer -->
    <footer class="footer">
        <p>© 2024 ShopZone - Tüm hakları saklıdır.</p>
    </footer>
    </body>
    </html>

