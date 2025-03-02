package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class YorumDAO {
    private Connection connection;

    public YorumDAO(Connection connection) {
        this.connection = connection;
    }

    // Yorum Ekle
    public void yorumEkle(Yorum yorum) throws SQLException {
        LocalDateTime yorumTarihi = LocalDateTime.now();

        String sql = "INSERT INTO yorumlar (urun_id, kullanici_adi, yorum, yildiz, yanit, yorum_tarihi) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, yorum.getUrunId());
            statement.setString(2, yorum.getKullaniciAdi());
            statement.setString(3, yorum.getYorum());
            statement.setFloat(4, yorum.getYildiz());
            statement.setString(5, yorum.getYanit());
            statement.setTimestamp(6, Timestamp.valueOf(yorum.getYorumTarihi()));
            statement.executeUpdate();
        }
    }

    // Yorumları Getir
    public List<Yorum> yorumlariGetir(int urunId) throws SQLException {
        List<Yorum> yorumlar = new ArrayList<>();
        String sql = "SELECT * FROM yorumlar WHERE urun_id = ? ORDER BY yorum_tarihi DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, urunId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Yorum yorum = new Yorum(
                        urunId,
                        resultSet.getString("kullanici_adi"),
                        resultSet.getString("yorum"),
                        resultSet.getFloat("yildiz"),
                        resultSet.getString("yanit"),
                        resultSet.getTimestamp("yorum_tarihi")
                );
                yorumlar.add(yorum);
            }
        }
        return yorumlar;
    }

    // Yorumlara Yanıt Ekle
    public boolean yorumYanitEkle(int UrunId, String yanit, String kullaniciAdi, Timestamp yorumTarihi) throws SQLException {
        String query = "UPDATE yorumlar SET yanit = ? WHERE urun_id = ? AND kullanici_adi = ? AND yorum_tarihi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, yanit);
            stmt.setInt(2, UrunId);
            stmt.setString(3, kullaniciAdi);
            stmt.setTimestamp(4, yorumTarihi);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Belirli bir yıldız sayısına göre yorumları getir
    public List<Yorum> yorumlariFiltrele(int urunId, int yildiz) throws SQLException {
        List<Yorum> yorumlar = new ArrayList<>();
        String sql = "SELECT * FROM yorumlar WHERE urun_id = ? AND yildiz = ? ORDER BY yorum_tarihi DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, urunId);
            statement.setInt(2, yildiz);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Yorum yorum = new Yorum(
                        urunId,
                        resultSet.getString("kullanici_adi"),
                        resultSet.getString("yorum"),
                        resultSet.getFloat("yildiz"),
                        resultSet.getString("yanit"),
                        resultSet.getTimestamp("yorum_tarihi")
                );
                yorumlar.add(yorum);
            }
        }
        return yorumlar;
    }

    // Yıldız Ortalamasını Hesapla (Veritabanından alınan verilerle)
    public double yildizOrtalamasiGetir(int urunId) throws SQLException {
        List<Float> yildizlar = new ArrayList<>();

        // Yıldızları veritabanından çek
        String sql = "SELECT yildiz FROM yorumlar WHERE urun_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, urunId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                yildizlar.add(resultSet.getFloat("yildiz"));
            }
        }

        // Eğer yorum yoksa, 0 döndür
        if (yildizlar.isEmpty()) {
            return 0.0;
        }

        // Ortalamayı hesapla
        float toplamYildiz = 0;
        for (Float yildiz : yildizlar) {
            toplamYildiz += yildiz;
        }

        // Ortalama hesapla ve döndür
        return toplamYildiz / yildizlar.size();
    }
}
