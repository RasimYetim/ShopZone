package servlet;

import java.io.IOException;
import java.util.List;
import DAO.ConnectionManager;
import DAO.Siparis;
import DAO.SiparisDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.* ;

public class SiparislerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SiparisDAO siparisDAO = new SiparisDAO(ConnectionManager.getConnection()); // DAO sınıfınız
            List<Siparis> siparisler = siparisDAO.siparisleriGetirTümü(); // Siparişleri çek
            request.setAttribute("siparisler", siparisler); // Siparişleri JSP'ye gönder
            request.getRequestDispatcher("/siparisler.jsp").forward(request, response); // JSP'yi yönlendir
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Veri tabanı hatası");
        }
    }
}
