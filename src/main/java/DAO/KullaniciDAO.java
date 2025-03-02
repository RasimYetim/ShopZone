package DAO;
// DAO Implementation Class for `kullanicilar` table
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KullaniciDAO {
    private Connection connection;

    public KullaniciDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertKullanici(Kullanici kullanici) throws SQLException {

        
        String query = "INSERT INTO kullanicilar (username, ad, soyad, cinsiyet, tcNo, sifre, sehir, dogum_tarihi, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kullanici.getUsername());
            ps.setString(2, kullanici.getAd());
            ps.setString(3, kullanici.getSoyad());
            ps.setObject(4, kullanici.getCinsiyet());
            ps.setObject(5, kullanici.getTcNo());
            ps.setString(6, kullanici.getSifre());
            ps.setString(7, kullanici.getSehir());
            ps.setDate(8, kullanici.getDogumTarihi() != null ? new java.sql.Date(kullanici.getDogumTarihi().getTime()) : null);

            ps.setString(9, kullanici.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Kullanici> tumKullanicilariAl() throws SQLException {
        List<Kullanici> kullanicilar = new ArrayList<>();
        String query = "SELECT * FROM kullanicilar";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Kullanici kullanici = new Kullanici();
                kullanici.setUsername(rs.getString("username"));
                kullanici.setAd(rs.getString("ad"));
                kullanici.setSoyad(rs.getString("soyad"));
                kullanici.setCinsiyet(rs.getObject("cinsiyet", Boolean.class));
                kullanici.setTcNo(rs.getObject("tcNo", Integer.class));
                kullanici.setSifre(rs.getString("sifre"));
                kullanici.setSehir(rs.getString("sehir"));
                kullanici.setDogumTarihi(rs.getDate("dogum_tarihi"));
                kullanici.setEmail(rs.getString("email"));
                kullanicilar.add(kullanici);
            }
        }
        return kullanicilar;
    }
    public boolean updateKullanici(Kullanici kullanici) throws SQLException {
        String query = "UPDATE kullanicilar SET ad = ?, soyad = ?, sifre = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kullanici.getAd());
            ps.setString(2, kullanici.getSoyad());
            ps.setString(3, kullanici.getSifre());
            ps.setString(4, kullanici.getUsername());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Güncelleme başarılıysa true döner
        }
    }

}
