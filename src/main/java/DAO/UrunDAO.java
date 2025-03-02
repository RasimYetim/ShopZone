package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public class UrunDAO {
    private Connection connection;

    public UrunDAO() throws SQLException {
        this.connection = ConnectionManager.getConnection();
    }

    public int getNextUrunId() throws SQLException {
        String query = "SELECT MAX(urun_id) FROM urunler";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) + 1; // Son ürünün ID'sini al ve 1 ekle
            }
        }
        return 1; // Eğer hiç ürün yoksa 1 numaradan başla
    }

    public boolean urunEkle(Urun urun) throws SQLException {
        String query = "INSERT INTO urunler (urun_id, urun_adi, stok, fiyat, puan, kategori, resim) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int nextUrunId = getNextUrunId();
            preparedStatement.setInt(1, nextUrunId); // Yeni ürün ID'si
            preparedStatement.setString(2, urun.getUrunAdi());
            preparedStatement.setInt(3, urun.getStok());
            preparedStatement.setFloat(4, urun.getFiyat());
            preparedStatement.setFloat(5, urun.getPuan()); // Puan varsayılan olarak 0
            preparedStatement.setString(6, urun.getKategori());
            preparedStatement.setBytes(7, urun.getResim());

            return preparedStatement.executeUpdate() > 0; // Etkilenen satır varsa başarı
        }
    }
    public List<Urun> getTumUrunler() throws SQLException {
        List<Urun> urunler = new ArrayList<>();
        String query = "SELECT * FROM urunler";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int urunId = resultSet.getInt("urun_id");
                String urunAdi = resultSet.getString("urun_adi");
                int stok = resultSet.getInt("stok");
                float fiyat = resultSet.getFloat("fiyat");
                String kategori = resultSet.getString("kategori");
                byte[] resim = resultSet.getBytes("resim");

                Urun urun = new Urun(urunAdi, stok, fiyat, kategori, resim);
                urun.setUrunId(urunId);
                urunler.add(urun);
            }
        }
        return urunler;
    }
    public Urun UrunBilgisiCek(int urunId) throws SQLException {
        Urun urun = null; // Eğer ürün bulunamazsa null dönecek
        String query = "SELECT * FROM urunler WHERE urun_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, urunId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String urunAdi = resultSet.getString("urun_adi");
                int stok = resultSet.getInt("stok");
                float fiyat = resultSet.getFloat("fiyat");
                String kategori = resultSet.getString("kategori");
                byte[] resim = resultSet.getBytes("resim");

                urun = new Urun(urunAdi, stok, fiyat, kategori, resim);
                urun.setUrunId(resultSet.getInt("urun_id")); // ID'yi ayrıca set ediyoruz
            }
        }
        return urun;
    }
    public List<Urun> urunAra(String aramaTerimi) {
        List<Urun> urunler = new ArrayList<>();
        String sql = "SELECT * FROM urunler WHERE urun_adi LIKE ? OR kategori LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + aramaTerimi + "%");
            ps.setString(2, "%" + aramaTerimi + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Urun urun = new Urun();
                    urun.setUrunId(rs.getInt("urun_id"));
                    urun.setUrunAdi(rs.getString("urun_adi"));
                    urun.setStok(rs.getInt("stok"));
                    urun.setFiyat(rs.getFloat("fiyat"));
                    urun.setPuan(rs.getFloat("puan"));
                    urun.setKategori(rs.getString("kategori"));
                    urun.setResim(rs.getBytes("resim")); // Resim sütunu

                    urunler.add(urun);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return urunler;
    }
    public boolean urunGuncelle(Urun urun) throws SQLException {
        String query = "UPDATE urunler SET urun_adi = ?, stok = ?, fiyat = ?, resim = ? WHERE urun_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, urun.getUrunAdi());
            preparedStatement.setInt(2, urun.getStok());
            preparedStatement.setFloat(3, urun.getFiyat());
            preparedStatement.setBytes(4, urun.getResim());
            preparedStatement.setInt(5, urun.getUrunId());

            return preparedStatement.executeUpdate() > 0; // Etkilenen satır varsa başarı
        }
    }
    public boolean resimEkle(int urunId, byte[] resim) throws SQLException {
        String query = "INSERT INTO urun_resimleri (urun_id, resim) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, urunId);
            ps.setBytes(2, resim);
            return ps.executeUpdate() > 0;
        }
    }
    public List<byte[]> getUrunResimleri(int urunId) throws SQLException {
        List<byte[]> resimler = new ArrayList<>();
        String query = "SELECT resim FROM urun_resimleri WHERE urun_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, urunId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resimler.add(rs.getBytes("resim"));
                }
            }
        }
        return resimler;
    }

}
