package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.Kullanici;
import DAO.KullaniciDAO;
import DAO.ConnectionManager;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class kullaniciEkleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET isteği için işleme kodu (varsa)
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String ad = request.getParameter("ad");
        String soyad = request.getParameter("soyad");
        Boolean cinsiyet = Boolean.parseBoolean(request.getParameter("cinsiyet"));
        Integer tcNo = null;
        try {
            tcNo = Integer.parseInt(request.getParameter("tcNo"));
        } catch (NumberFormatException e) {
            response.getWriter().println("TC No geçersiz.");
            return;
        }
        String sifre = request.getParameter("sifre");
        String sehir = request.getParameter("sehir");
        java.sql.Date dogumTarihi = request.getParameter("dogumTarihi") != null && !request.getParameter("dogumTarihi").isEmpty()
                ? java.sql.Date.valueOf(request.getParameter("dogumTarihi"))
                : null;
        String email = request.getParameter("email");

        Kullanici kullanici = new Kullanici();
        kullanici.setUsername(username);
        kullanici.setAd(ad);
        kullanici.setSoyad(soyad);
        kullanici.setCinsiyet(cinsiyet);
        kullanici.setTcNo(tcNo);
        kullanici.setSifre(sifre);
        kullanici.setSehir(sehir);
        kullanici.setDogumTarihi(dogumTarihi);
        kullanici.setEmail(email);


        // Connection nesnesini al
        try (Connection connection = ConnectionManager.getConnection()) {
            if (connection == null) {
                response.getWriter().println("Veritabanı bağlantısı sağlanamadı.");
                return;
            }
            KullaniciDAO kullaniciDAO = new KullaniciDAO(connection);
            kullaniciDAO.insertKullanici(kullanici);
            response.getWriter().println("Kayit olundu. Ana sayfaya yonlendiriliyorsunuz. ");

            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            throw new ServletException("Kullanıcı eklenirken hata oluştu", e);
        }
    }}

