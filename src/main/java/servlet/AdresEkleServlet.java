package servlet;

import DAO.Adres;
import DAO.AdresDAO;
import DAO.ConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdresEkleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adres = request.getParameter("adres");
        HttpSession session = request.getSession(false);
        String kullaniciAdi = (String) session.getAttribute("kullanici_adi");

        // Örnek bir DAO nesnesi ile işlem
        try (Connection conn = ConnectionManager.getConnection()) {
            AdresDAO adresDAO = new AdresDAO(conn);

            Adres yeniAdres = new Adres();
            yeniAdres.setAdres(adres);
            yeniAdres.setKullanici_adi(kullaniciAdi);

            boolean basarili = adresDAO.adresEkle(yeniAdres);
            if (basarili) {
                request.setAttribute("mesaj", "Adres başarıyla eklendi!");
            } else {
                request.setAttribute("mesaj", "Adres eklenirken bir hata oluştu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mesaj", "Veritabanı bağlantı hatası.");
        }

        // JSP'ye yönlendirme
        RequestDispatcher dispatcher = request.getRequestDispatcher("adresEkle.jsp");
        dispatcher.forward(request, response);
    }
}


