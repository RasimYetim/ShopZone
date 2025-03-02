package servlet;

import DAO.CanliDestek;
import DAO.CanliDestekDAO;
import DAO.ConnectionManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.sql.Timestamp;


public class CanliDestekServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mesaj = request.getParameter("mesaj");
        String kullaniciAdi = request.getParameter("kullanici");
        Timestamp zaman = new Timestamp(System.currentTimeMillis());

        try (Connection conn = ConnectionManager.getConnection()) {
            CanliDestekDAO canliDestekDAO = new CanliDestekDAO(conn);

            // Yeni mesajı kaydet
            CanliDestek yeniMesaj = new CanliDestek();
            yeniMesaj.setKullaniciId(kullaniciAdi);
            yeniMesaj.setGonderen(kullaniciAdi);
            yeniMesaj.setMesaj(mesaj);
            yeniMesaj.setZaman(zaman);
            canliDestekDAO.mesajEkle(yeniMesaj);

            // Tüm mesajları getir
            List<CanliDestek> mesajlar = canliDestekDAO.mesajlariGetir();
            request.setAttribute("mesajlar", mesajlar);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // JSP'ye yönlendir
        RequestDispatcher dispatcher = request.getRequestDispatcher("canlidestek.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Mesajları görüntülemek için GET isteği de desteklenir
        try (Connection conn = ConnectionManager.getConnection()) {
            CanliDestekDAO canliDestekDAO = new CanliDestekDAO(conn);

            // Tüm mesajları getir
            List<CanliDestek> mesajlar = canliDestekDAO.mesajlariGetir();
            request.setAttribute("mesajlar", mesajlar);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // JSP'ye yönlendir
        RequestDispatcher dispatcher = request.getRequestDispatcher("canlidestek.jsp");
        dispatcher.forward(request, response);
    }
}
