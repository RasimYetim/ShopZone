package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class CanliDestekDAO {

    private Connection connection;

    // Constructor: Veritabanı bağlantısı
    public CanliDestekDAO(Connection connection) {
        this.connection = connection;
    }

    // Yeni bir mesaj ekler (hem admin hem kullanıcı mesajı)
    public boolean mesajEkle(CanliDestek canliDestek) {
        String sql = "INSERT INTO canlidestek (kullaniciId, mesaj, gonderen, zaman, aktif) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, canliDestek.getKullaniciId()); // Kullanıcı ID'si (kayıtlı kullanıcı için)
            stmt.setString(2, canliDestek.getMesaj()); // Mesaj
            stmt.setString(3, canliDestek.getGonderen()); // Mesajı gönderen (admin veya kullanıcı)
            stmt.setTimestamp(4, canliDestek.getZaman()); // Zaman
            stmt.setBoolean(5, canliDestek.isAktif()); // Konuşma durumu

            int result = stmt.executeUpdate(); // Mesajı ekle
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Tüm mesajları alır
    public List<CanliDestek> mesajlariGetir() {
        List<CanliDestek> mesajlar = new ArrayList<>();
        String sql = "SELECT * FROM canlidestek WHERE aktif = 1 ORDER BY zaman DESC"; // Sadece aktif mesajlar
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CanliDestek canliDestek = new CanliDestek();
                canliDestek.setId(rs.getInt("id"));
                canliDestek.setKullaniciId(rs.getString("kullaniciId"));
                canliDestek.setMesaj(rs.getString("mesaj"));
                canliDestek.setGonderen(rs.getString("gonderen"));
                canliDestek.setZaman(rs.getTimestamp("zaman"));
                canliDestek.setAktif(rs.getBoolean("aktif"));
                mesajlar.add(canliDestek);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    // Kullanıcıya ait mesajları getir
    public List<CanliDestek> mesajlariGetirByKullaniciId(String kullaniciId) {
        List<CanliDestek> mesajlar = new ArrayList<>();
        String sql = "SELECT * FROM canlidestek WHERE kullaniciId = ? AND aktif = 1 ORDER BY zaman DESC"; // Belirli bir kullanıcının aktif mesajları
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, kullaniciId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CanliDestek canliDestek = new CanliDestek();
                canliDestek.setId(rs.getInt("id"));
                canliDestek.setKullaniciId(rs.getString("kullaniciId"));
                canliDestek.setMesaj(rs.getString("mesaj"));
                canliDestek.setGonderen(rs.getString("gonderen"));
                canliDestek.setZaman(rs.getTimestamp("zaman"));
                canliDestek.setAktif(rs.getBoolean("aktif"));
                mesajlar.add(canliDestek);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    // Admin'e ait mesajları alır
    public List<CanliDestek> mesajlariGetirByAdmin() {
        List<CanliDestek> mesajlar = new ArrayList<>();
        String sql = "SELECT * FROM canlidestek WHERE gonderen = 'admin' AND aktif = 1 ORDER BY zaman DESC"; // Admin mesajları
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CanliDestek canliDestek = new CanliDestek();
                canliDestek.setId(rs.getInt("id"));
                canliDestek.setKullaniciId(rs.getString("kullaniciId"));
                canliDestek.setMesaj(rs.getString("mesaj"));
                canliDestek.setGonderen(rs.getString("gonderen"));
                canliDestek.setZaman(rs.getTimestamp("zaman"));
                canliDestek.setAktif(rs.getBoolean("aktif"));
                mesajlar.add(canliDestek);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesajlar;
    }

    // Konuşmayı bitir (aktif durumu false yapar)
    public boolean konusmayiBitir(int id) {
        String sql = "UPDATE canlidestek SET aktif = false WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}