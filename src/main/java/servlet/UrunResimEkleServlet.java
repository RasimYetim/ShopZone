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
public class UrunResimEkleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int urunId = Integer.parseInt(request.getParameter("urunId"));
            Part[] resimParts = request.getParts().stream()
                    .filter(part -> "resimler".equals(part.getName()))
                    .toArray(Part[]::new);

            UrunDAO urunDAO = new UrunDAO();
            for (Part resimPart : resimParts) {
                try (InputStream is = resimPart.getInputStream()) {
                    byte[] resim = is.readAllBytes();
                    urunDAO.resimEkle(urunId, resim);
                }
            }
            response.getWriter().println("Resimler başarıyla eklendi.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Bir hata oluştu: " + e.getMessage());
        }
    }
}

