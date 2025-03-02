package DAO;

public class Iletisim {




    private String ad;
    private String email;
    private String konu;
    private String mesaj;
    public Iletisim() {}
    public Iletisim(String ad, String email, String konu, String mesaj) {
        this.ad = ad;
        this.email = email;
        this.konu = konu;
        this.mesaj = mesaj;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }


}
