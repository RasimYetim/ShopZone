package servlet;

import DAO.ConnectionManager;
import DAO.Yorum;
import DAO.YorumDAO;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class YorumEkleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Formdan gelen verileri al
            int urunId = Integer.parseInt(request.getParameter("urunId"));
            String kullaniciAdi = request.getParameter("kullaniciAdi");
            String yorumMetni = request.getParameter("yorum");
            float yildiz = Integer.parseInt(request.getParameter("yildiz")); // Kullanıcının verdiği yıldız

            LocalDateTime yorumTarihi = LocalDateTime.now();
            Timestamp yorumTarih = Timestamp.valueOf(yorumTarihi);
            // Yorum nesnesi oluştur
            Yorum yeniYorum = new Yorum(urunId, kullaniciAdi, yorumMetni, yildiz, " ",yorumTarih);

            // Veritabanı bağlantısını al
            Connection connection = ConnectionManager.getConnection();

            // DAO kullanarak yorumu kaydet
            YorumDAO yorumDAO = new YorumDAO(connection);
            yorumDAO.yorumEkle(yeniYorum);

            // Kullanıcıya başarı mesajı göster
            response.sendRedirect("urunDetay.jsp?urunId="
            + urunId);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Bir hata olustu: " + e.getMessage());
        }
    }
}
