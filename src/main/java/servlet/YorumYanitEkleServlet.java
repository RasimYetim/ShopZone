
package servlet;

import DAO.ConnectionManager;
import DAO.Yorum;
import DAO.YorumDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class YorumYanitEkleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int UrunId = Integer.parseInt(request.getParameter("urun_id"));
        String yanit = request.getParameter("yanit");
        String kullaniciAdi = request.getParameter("kullanici_adi");
        String tarihStr = request.getParameter("yorum_tarihi");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(tarihStr, formatter);
        Timestamp yorumTarihi = Timestamp.valueOf(localDateTime);


        // Yorum yanıtını veritabanına ekleyin
        YorumDAO yorumDAO = null;
        try {
            yorumDAO = new YorumDAO(ConnectionManager.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        boolean isUpdated = false;
        try {
            isUpdated = yorumDAO.yorumYanitEkle(UrunId, yanit,kullaniciAdi, yorumTarihi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isUpdated) {
            response.sendRedirect("urunDetay.jsp?urunId=" + request.getParameter("urun_id"));
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }
}
