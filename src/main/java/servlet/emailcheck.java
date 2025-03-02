package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.ConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class emailcheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Formdan gelen e-posta adresini al
        String email = request.getParameter("email");

        // SQL sorgusu
        String query = "SELECT COUNT(*) FROM kullanicilar WHERE email = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // E-posta kayıtlıysa giriş sayfasına yönlendir
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("giris.jsp");
            } else {
                // E-posta kayıtlı değilse kayıt sayfasına yönlendir
                response.sendRedirect("YeniKullanici.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Veritabanı bağlantısında hata.");
            return;
        }
    }
}

