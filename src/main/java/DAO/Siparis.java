package DAO;
import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
public class Siparis {
    private int siparisNo;
    private double toplamFiyat;
    private String alanKisi;
    private List<Integer> urunIds; // Sipariş detayları için ürün ID'leri

    public String getTeslimatTuru() {
        return teslimatTuru;
    }

    public void setTeslimatTuru(String teslimatTuru) {
        this.teslimatTuru = teslimatTuru;
    }

    public String getOdemeTuru() {
        return odemeTuru;
    }

    public void setOdemeTuru(String odemeTuru) {
        this.odemeTuru = odemeTuru;
    }

    private String teslimatTuru;  // Teslimat türü
    private String odemeTuru;
    public String getSiparisdurum() {
        return siparisdurum;
    }

    public void setSiparisdurum(String siparisdurum) {
        this.siparisdurum = siparisdurum;
    }

    private String siparisdurum;
    public Timestamp getSipariszaman() {
        return sipariszaman;
    }

    public void setSipariszaman(Timestamp sipariszaman) {
        this.sipariszaman = sipariszaman;
    }

    private Timestamp sipariszaman;
    public List<Integer> getUrunAdets() {
        return urunAdets;
    }

    public void setUrunAdets(List<Integer> urunAdets) {
        this.urunAdets = urunAdets;
    }

    private List<Integer> urunAdets;
    // Constructor
    public Siparis(int siparisNo, double toplamFiyat, String alanKisi, Timestamp sipariszaman, String teslimatTuru, String odemeTuru) {
        this.siparisNo = siparisNo;
        this.toplamFiyat = toplamFiyat;
        this.alanKisi = alanKisi;
        this.sipariszaman = sipariszaman;
        this.urunIds = new ArrayList<>();
        this.urunAdets = new ArrayList<>();

        this.teslimatTuru = teslimatTuru;
        this.odemeTuru = odemeTuru;
    }
    public Siparis(int siparisNo, double toplamFiyat, String alanKisi, List<Integer> urunIds,String teslimatTuru, String odemeTuru) {
        this.siparisNo = siparisNo;
        this.toplamFiyat = toplamFiyat;
        this.alanKisi = alanKisi;
        this.urunIds = urunIds;

        this.teslimatTuru = teslimatTuru;
        this.odemeTuru = odemeTuru;
    }
    public Siparis(int siparisNo, double toplamFiyat, String alanKisi, List<Integer> urunIds, List<Integer> urunAdets , Timestamp sipariszaman,String teslimatTuru, String odemeTuru) {
        this.siparisNo = siparisNo;
        this.toplamFiyat = toplamFiyat;
        this.alanKisi = alanKisi;
        this.urunIds = urunIds;
        this.urunAdets = urunAdets;
        this.sipariszaman = sipariszaman;

        this.teslimatTuru = teslimatTuru;
        this.odemeTuru = odemeTuru;
    }

    // Getters and Setters
    public int getSiparisNo() {
        return siparisNo;
    }

    public void setSiparisNo(int siparisNo) {
        this.siparisNo = siparisNo;
    }

    public double getToplamFiyat() {
        return toplamFiyat;
    }

    public void setToplamFiyat(double toplamFiyat) {
        this.toplamFiyat = toplamFiyat;
    }

    public String getAlanKisi() {
        return alanKisi;
    }

    public void setAlanKisi(String alanKisi) {
        this.alanKisi = alanKisi;
    }

    public List<Integer> getUrunIds() {
        return urunIds;
    }

    public void setUrunIds(List<Integer> urunIds) {
        this.urunIds = urunIds;
    }
}
