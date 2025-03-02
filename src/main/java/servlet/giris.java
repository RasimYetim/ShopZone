package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.ConnectionManager;
import DAO.Kullanici;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class giris extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Formdan gelen e-posta ve şifreyi al

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String sifre = request.getParameter("sifre");
        if (email == null || email.isEmpty()) {
            response.sendRedirect("index.jsp"); // Veya başka bir uygun sayfaya yönlendirin
            return;
        }


        // SQL sorgusu
        String query = "SELECT * FROM kullanicilar WHERE email = ? AND sifre = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, sifre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Kullanıcı bilgileri doğru, oturum aç
                // kullanici bilgilerinden kullanici objesini oluştur ve sonraki sayfaya dondur
                Kullanici aktif = new Kullanici(rs.getString("username"),rs.getString("ad"), rs.getString("soyad"),rs.getBoolean("cinsiyet"), rs.getInt("tcNo"),rs.getString("sifre"), rs.getString("email") );

                session.setAttribute("aktif", aktif);

                response.sendRedirect("index.jsp");
            } else {
                // Hatalı giriş
                response.sendRedirect("giris.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
