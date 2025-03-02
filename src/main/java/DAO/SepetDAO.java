package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;





public class SepetDAO {
    private Connection connection;

    public SepetDAO(Connection connection) {
        this.connection = connection;
    }

    public void SepeteEkle(int urun_id, String kullanici_adi) throws SQLException {
        String checkSql = "SELECT adet FROM sepet WHERE urun_id = ? AND kullanici_adi = ?";
        String updateSql = "UPDATE sepet SET adet = adet + 1 WHERE urun_id = ? AND kullanici_adi = ?";
        String insertSql = "INSERT INTO sepet (urun_id, kullanici_adi, adet) VALUES (?, ?, 1)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setInt(1, urun_id);
            checkStatement.setString(2, kullanici_adi);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Ürün zaten sepette, adet artır
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setInt(1, urun_id);
                        updateStatement.setString(2, kullanici_adi);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // Ürün sepette yok, yeni ürün ekle
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setInt(1, urun_id);
                        insertStatement.setString(2, kullanici_adi);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static class UrunSepet {
        public int urunId;
        public String urunAdi;
        public int stok;
        public float fiyat;
        public String kategori;
        public String resimBase64;
        public float puan;
        public int adet;

        public UrunSepet(int urunId, String urunAdi, int stok, float fiyat, String kategori, String resimBase64, float puan, int adet) {
            this.urunId = urunId;
            this.urunAdi = urunAdi;
            this.stok = stok;
            this.fiyat = fiyat;
            this.kategori = kategori;
            this.resimBase64 = resimBase64;
            this.puan = puan;
            this.adet = adet;
        }
    }
    public List<UrunSepet> SepetiGetir(String kullaniciAdi) {
        String sql = "SELECT s.urun_id, s.adet, u.urun_adi, u.stok, u.fiyat, u.kategori, u.resim, u.puan " +
                "FROM sepet s JOIN urunler u ON s.urun_id = u.urun_id WHERE s.kullanici_adi = ?";
        Map<Integer, UrunSepet> urunMap = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kullaniciAdi);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int urunId = resultSet.getInt("urun_id");
                    String urunAdi = resultSet.getString("urun_adi");
                    int stok = resultSet.getInt("stok");
                    float fiyat = resultSet.getFloat("fiyat");
                    String kategori = resultSet.getString("kategori");
                    byte[] resim = resultSet.getBytes("resim");
                    float puan = resultSet.getFloat("puan");
                    int adet = resultSet.getInt("adet");

                    // Base64 encode the image
                    String resimBase64 = (resim != null) ? java.util.Base64.getEncoder().encodeToString(resim) : null;

                    if (urunMap.containsKey(urunId)) {
                        // Adet ekle
                        UrunSepet mevcutUrun = urunMap.get(urunId);
                        mevcutUrun.adet += adet;
                    } else {
                        urunMap.put(urunId, new UrunSepet(urunId, urunAdi, stok, fiyat, kategori, resimBase64, puan, adet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(urunMap.values());
    }


    public void UpdateAdet(String kullaniciAdi, int urunId, int yeniAdet) {
        if (yeniAdet <= 0) {
            // Adet 0 veya daha küçükse, ürünü sepetten sil
            String deleteSql = "DELETE FROM sepet WHERE kullanici_adi = ? AND urun_id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setString(1, kullaniciAdi);
                deleteStatement.setInt(2, urunId);
                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Adet 1 veya daha büyükse güncelle
            String updateSql = "UPDATE sepet SET adet = ? WHERE kullanici_adi = ? AND urun_id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setInt(1, yeniAdet);
                updateStatement.setString(2, kullaniciAdi);
                updateStatement.setInt(3, urunId);
                updateStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
