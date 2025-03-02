package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAO {

    private Connection connection;

    // Constructor to initialize the database connection
    public AdresDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new address
    public boolean adresEkle(Adres adres) {
        String sql = "INSERT INTO adres (adresID, adres, kullanici_adi) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, adres.getAdresID());
            stmt.setString(2, adres.getAdres());
            stmt.setString(3, adres.getKullanici_adi());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch all addresses
    public List<Adres> adresleriGetir() {
        List<Adres> adresler = new ArrayList<>();
        String sql = "SELECT * FROM adres";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Adres adres = new Adres();
                adres.setAdresID(rs.getInt("adresID"));
                adres.setAdres(rs.getString("adres"));
                adres.setKullanici_adi(rs.getString("kullanici_adi"));
                adresler.add(adres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adresler;
    }

    // Method to fetch addresses by username
    public List<Adres> adresleriGetirByKullaniciAdi(String kullaniciAdi) {
        List<Adres> adresler = new ArrayList<>();
        String sql = "SELECT * FROM adres WHERE kullanici_adi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, kullaniciAdi);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Adres adres = new Adres();
                adres.setAdresID(rs.getInt("adresID"));
                adres.setAdres(rs.getString("adres"));
                adres.setKullanici_adi(rs.getString("kullanici_adi"));
                adresler.add(adres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adresler;
    }

    // Method to delete an address by ID
    public boolean adresSil(int adresID) {
        String sql = "DELETE FROM adres WHERE adresID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, adresID);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing address
    public boolean adresGuncelle(Adres adres) {
        String sql = "UPDATE adres SET adres = ?, kullanici_adi = ? WHERE adresID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, adres.getAdres());
            stmt.setString(2, adres.getKullanici_adi());
            stmt.setInt(3, adres.getAdresID());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
