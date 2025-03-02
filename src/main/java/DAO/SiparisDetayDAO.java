package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiparisDetayDAO {
    private Connection connection;

    public SiparisDetayDAO(Connection connection) {
        this.connection = connection;
    }

    public void siparisDetayEkle(SiparisDetay siparisDetay) throws SQLException {
        String sql = "INSERT INTO siparis_detay (siparis_no, urun_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siparisDetay.getSiparisNo());
            statement.setInt(2, siparisDetay.getUrunId());
            statement.executeUpdate();
        }
    }

    public List<SiparisDetay> siparisDetaylariGetir(int siparisNo) throws SQLException {
        List<SiparisDetay> detaylar = new ArrayList<>();
        String sql = "SELECT * FROM siparis_detay WHERE siparis_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siparisNo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int urunId = resultSet.getInt("urun_id");
                detaylar.add(new SiparisDetay(siparisNo, urunId));
            }
        }
        return detaylar;
    }
}