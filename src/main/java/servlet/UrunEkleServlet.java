package servlet;

import DAO.Urun;
import DAO.UrunDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig // Resim dosyalarını almak için gerekli
public class UrunEkleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Formdan gelen verileri al
            String urunAdi = request.getParameter("urunAdi");
            int stok = Integer.parseInt(request.getParameter("stok"));
            float fiyat = Float.parseFloat(request.getParameter("fiyat"));
            String kategori = request.getParameter("kategori");
            Part resimPart = request.getPart("resim");

            // Resim dosyasını byte dizisine çevir
            byte[] resim = null;
            if (resimPart != null) {
                try (InputStream inputStream = resimPart.getInputStream()) {
                    resim = inputStream.readAllBytes();
                }
            }

            // Yeni ürün oluştur ve DAO ile kaydet
            Urun urun = new Urun(urunAdi, stok, fiyat, kategori, resim);
            UrunDAO urunDAO = new UrunDAO();

            if (urunDAO.urunEkle(urun)) {
                response.getWriter().println("Ürün başarıyla eklendi.");
            } else {
                response.getWriter().println("Ürün eklenirken bir hata oluştu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Bir hata oluştu: " + e.getMessage());
        }
    }
}

