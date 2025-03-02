package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class SiparisDAO {
    private Connection connection;

    public SiparisDAO(Connection connection) {
        this.connection = connection;
    }

    // Siparişi veritabanına ekler
    public void siparisEkle(Siparis siparis) throws SQLException {
        String sql = "INSERT INTO siparisler (siparis_no, toplam_fiyat, alan_kisi, siparis_tarihi, teslimat_turu, odeme_turu, siparis_durumu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siparis.getSiparisNo());
            statement.setDouble(2, siparis.getToplamFiyat());
            statement.setString(3, siparis.getAlanKisi());
            statement.setTimestamp(4, siparis.getSipariszaman());
            statement.setString(5, siparis.getTeslimatTuru()); // Teslimat türünü ekliyoruz
            statement.setString(6, siparis.getOdemeTuru()); // Ödeme türünü ekliyoruz
            statement.setString(7,"Siparis alindi.");
            statement.executeUpdate();

            // Sipariş detaylarını ekleyin
            for (int i = 0; i < siparis.getUrunIds().size(); i++) {
                String detaySql = "INSERT INTO siparis_detay (siparis_no, urun_id, adet) VALUES (?, ?, ?)";
                try (PreparedStatement detayStatement = connection.prepareStatement(detaySql)) {
                    detayStatement.setInt(1, siparis.getSiparisNo());
                    detayStatement.setInt(2, siparis.getUrunIds().get(i));
                    detayStatement.setInt(3, siparis.getUrunAdets().get(i));
                    detayStatement.executeUpdate();
                }
            }
        }
    }

    // Tüm siparişleri getir
    public List<Siparis> siparisleriGetirTümü() throws SQLException {
        List<Siparis> siparisler = new ArrayList<>();
        String sql = "SELECT s.siparis_no, s.toplam_fiyat, s.alan_kisi, s.siparis_tarihi, s.siparis_durumu, s.teslimat_turu, s.odeme_turu, sd.urun_id, sd.adet " +
                "FROM siparisler s " +
                "LEFT JOIN siparis_detay sd ON s.siparis_no = sd.siparis_no";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Map<Integer, Siparis> siparisMap = new HashMap<>();

            while (resultSet.next()) {
                int siparisNo = resultSet.getInt("siparis_no");
                double toplamFiyat = resultSet.getDouble("toplam_fiyat");
                String alanKisi = resultSet.getString("alan_kisi");
                Timestamp siparisZamani = resultSet.getTimestamp("siparis_tarihi");
                int urunId = resultSet.getInt("urun_id");
                int adet = resultSet.getInt("adet");
                String siparisDurumu = resultSet.getString("siparis_durumu");
                String teslimatTuru = resultSet.getString("teslimat_turu"); // Teslimat türü
                String odemeTuru = resultSet.getString("odeme_turu"); // Ödeme türü

                Siparis siparis = siparisMap.get(siparisNo);
                if (siparis == null) {
                    siparis = new Siparis(siparisNo, toplamFiyat, alanKisi, new ArrayList<>(), new ArrayList<>(), siparisZamani,teslimatTuru,odemeTuru);
                    siparis.setSiparisdurum(siparisDurumu);
                    siparis.setTeslimatTuru(teslimatTuru); // Teslimat türünü ekliyoruz
                    siparis.setOdemeTuru(odemeTuru); // Ödeme türünü ekliyoruz
                    siparisMap.put(siparisNo, siparis);
                    siparisler.add(siparis);
                }
                siparis.getUrunIds().add(urunId);
                siparis.getUrunAdets().add(adet);
            }
        }
        return siparisler;
    }

    // Kullanıcıya ait siparişleri getir
    public List<Siparis> siparisleriGetirKullanici(String kullaniciAdi) throws SQLException {
        List<Siparis> siparisler = new ArrayList<>();
        String sql = "SELECT s.siparis_no, s.toplam_fiyat, s.alan_kisi, s.siparis_tarihi, s.siparis_durumu, s.teslimat_turu, s.odeme_turu " +
                "FROM siparisler s " +
                "WHERE s.alan_kisi = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, kullaniciAdi);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int siparisNo = resultSet.getInt("siparis_no");
                double toplamFiyat = resultSet.getDouble("toplam_fiyat");
                String alanKisi = resultSet.getString("alan_kisi");
                Timestamp siparisZamani = resultSet.getTimestamp("siparis_tarihi");
                String siparisDurumu = resultSet.getString("siparis_durumu");
                String teslimatTuru = resultSet.getString("teslimat_turu"); // Teslimat türü
                String odemeTuru = resultSet.getString("odeme_turu"); // Ödeme türü

                Siparis siparis = new Siparis(siparisNo, toplamFiyat, alanKisi, new ArrayList<>(), new ArrayList<>(), siparisZamani,teslimatTuru,odemeTuru);
                siparis.setSiparisdurum(siparisDurumu);
                siparis.setTeslimatTuru(teslimatTuru); // Teslimat türünü ekliyoruz
                siparis.setOdemeTuru(odemeTuru); // Ödeme türünü ekliyoruz
                siparisler.add(siparis);
            }
        }
        return siparisler;
    }

    // Siparişi ID ile getir
    public Siparis siparisGetirById(int siparisNo) throws SQLException {
        String sql = "SELECT s.siparis_no, s.toplam_fiyat, s.alan_kisi, s.siparis_tarihi, s.siparis_durumu, s.teslimat_turu, s.odeme_turu " +
                "FROM siparisler s " +
                "WHERE s.siparis_no = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siparisNo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double toplamFiyat = resultSet.getDouble("toplam_fiyat");
                String alanKisi = resultSet.getString("alan_kisi");
                Timestamp siparisZamani = resultSet.getTimestamp("siparis_tarihi");
                String siparisDurumu = resultSet.getString("siparis_durumu");
                String teslimatTuru = resultSet.getString("teslimat_turu"); // Teslimat türü
                String odemeTuru = resultSet.getString("odeme_turu"); // Ödeme türü

                Siparis siparis = new Siparis(siparisNo, toplamFiyat, alanKisi, new ArrayList<>(), new ArrayList<>(), siparisZamani,teslimatTuru,odemeTuru);
                siparis.setSiparisdurum(siparisDurumu);
                siparis.setTeslimatTuru(teslimatTuru); // Teslimat türünü ekliyoruz
                siparis.setOdemeTuru(odemeTuru); // Ödeme türünü ekliyoruz
                return siparis;
            }
        }
        return null;
    }

    // Sipariş durumunu güncelle
    public void siparisDurumGuncelle(int siparisNo, String yeniDurum) throws SQLException {
        String sql = "UPDATE siparisler SET siparis_durumu = ? WHERE siparis_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, yeniDurum);
            statement.setInt(2, siparisNo);
            statement.executeUpdate();
        }
    }
}
