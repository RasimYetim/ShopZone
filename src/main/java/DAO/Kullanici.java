package DAO;

public class Kullanici {
    private String username;
    private String ad;
    private String soyad;
    private Boolean cinsiyet;
    private Integer tcNo;
    private String sifre;
    private String sehir;
    private java.util.Date dogumTarihi;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Kullanici() {}
    public Kullanici(String kadi, String adi, String soyadi, Boolean cinsiyeti, Integer tcN, String sifresi, String email ) {
        this.username = kadi;
        this.ad = adi;
        this.soyad = soyadi;
        this.cinsiyet = cinsiyeti;
        this.tcNo = tcN;
        this.sifre = sifresi;
        this.email = email;
    }
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Boolean getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Boolean cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public Integer getTcNo() {
        return tcNo;
    }

    public void setTcNo(Integer tcNo) {
        this.tcNo = tcNo;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public java.util.Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(java.util.Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }
}


