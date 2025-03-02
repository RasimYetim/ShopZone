package servlet;


import DAO.Urun;
import DAO.UrunDAO;

import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class UrunDuzenleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Formdan gelen verileri al
            int urunId = Integer.parseInt(request.getParameter("urunId"));
            String urunAdi = request.getParameter("urunAdi");
            int stok = Integer.parseInt(request.getParameter("stok"));
            float fiyat = Float.parseFloat(request.getParameter("fiyat"));
            Part resimPart = request.getPart("resim");

            // Resim dosyasını byte dizisine çevir (resim yüklenmişse)
            byte[] resim = null;
            if (resimPart != null && resimPart.getSize() > 0) {
                try (InputStream inputStream = resimPart.getInputStream()) {
                    resim = inputStream.readAllBytes();
                }
            }

            // DAO ile ürün güncelle
            UrunDAO urunDAO = new UrunDAO();
            Urun urun = urunDAO.UrunBilgisiCek(urunId);
            urun.setUrunAdi(urunAdi);
            urun.setStok(stok);
            urun.setFiyat(fiyat);
            if (resim != null) { // Yeni resim yüklenmişse güncelle
                urun.setResim(resim);
            }

            if (urunDAO.urunGuncelle(urun)) {
                response.getWriter().println("Ürün başarıyla güncellendi.");
            } else {
                response.getWriter().println("Ürün güncellenirken bir hata oluştu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Bir hata oluştu: " + e.getMessage());
        }
    }
}

