package DAO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Yorum {
    private int urunId;
    private String kullaniciAdi;
    private String yorum;
    private float yildiz; // Ortalama yerine bireysel yıldız
    private String yanit;
    private Timestamp yorumTarihi; // Yeni alan

    // Default Constructor
    public Yorum() {}
    public Yorum(int urunId, String kullaniciAdi, String yorum, float yildiz, Timestamp yorumTarihi) {
        this.urunId = urunId;
        this.kullaniciAdi = kullaniciAdi;
        this.yorum = yorum;
        this.yildiz = yildiz;
        this.yorumTarihi = yorumTarihi;
    }
    // Parametreli Constructor
    public Yorum(int urunId, String kullaniciAdi, String yorum, float yildiz, String yanit, Timestamp yorumTarihi) {
        this.urunId = urunId;
        this.kullaniciAdi = kullaniciAdi;
        this.yorum = yorum;
        this.yildiz = yildiz;
        this.yanit = yanit;
        this.yorumTarihi = yorumTarihi;
    }

    // Getters and Setters
    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public float getYildiz() {
        return yildiz;
    }

    public void setYildiz(float yildiz) {
        this.yildiz = yildiz;
    }

    public String getYanit() {
        return yanit;
    }

    public void setYanit(String yanit) {
        this.yanit = yanit;
    }

    public LocalDateTime getYorumTarihi() {

        return yorumTarihi.toLocalDateTime();
    }

    public void setYorumTarihi(Timestamp yorumTarihi) {
        this.yorumTarihi = yorumTarihi;
    }

    @Override
    public String toString() {
        return "Yorum{" +
                "urunId=" + urunId +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", yorum='" + yorum + '\'' +
                ", yildiz=" + yildiz +
                ", yanit='" + yanit + '\'' +
                ", yorumTarihi=" + yorumTarihi +
                '}';
    }
}
