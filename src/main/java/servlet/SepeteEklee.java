package servlet;


import DAO.SepetDAO;
import DAO.ConnectionManager;
import DAO.Kullanici;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class SepeteEklee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kullanıcı adını ve ürün ID'sini al
        String kullanici_adi = request.getParameter("kullanici_adi");
        int urun_id = Integer.parseInt(request.getParameter("urun_id"));
        try (Connection connection = ConnectionManager.getConnection()) {
            SepetDAO sepetDAO = new SepetDAO(connection);
            sepetDAO.SepeteEkle(urun_id, kullanici_adi);
            response.sendRedirect("sepet.jsp"); // Sepet sayfasına yönlendir
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Veritabanı hatası");
        }
    }
}


