package servlet;

import DAO.Yorum;
import DAO.YorumDAO;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class YorumGosterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int urunId = Integer.parseInt(request.getParameter("urunId")); // URL'den ürün ID'sini al

            // Veritabanı bağlantısını al
            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

            // Yorumları al
            YorumDAO yorumDAO = new YorumDAO(connection);
            List<Yorum> yorumlar = yorumDAO.yorumlariGetir(urunId);

            // Yorumları request attribute olarak gönder
            request.setAttribute("yorumlar", yorumlar);

            // Yorumları görüntüleyecek JSP sayfasına yönlendir
            request.getRequestDispatcher("/yorumlar.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Bir hata oluştu: " + e.getMessage());
        }
    }
}
