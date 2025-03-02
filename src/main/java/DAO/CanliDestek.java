package DAO;

import java.sql.Timestamp;

public class CanliDestek {
    private int id;
    private String kullaniciId; // Kayıtlı kullanıcı varsa ID'si, yoksa null
    private String mesaj; // Gönderilen mesaj içeriği
    private String gonderen; // Mesajı gönderen ("admin" veya kullanıcı)
    private Timestamp zaman; // Mesajın gönderilme zamanı
    private Boolean aktif;

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = true;
    }
public boolean isAktif() {return true;}

    // Constructor
    public CanliDestek(int id, String kullaniciId,  String mesaj, String gonderen, Timestamp zaman, boolean aktif ) {
        this.id = id;
        this.kullaniciId = kullaniciId;
        this.mesaj = mesaj;
        this.gonderen = gonderen;
        this.zaman = zaman;
        this.aktif = true;
    }

    // Default Constructor
    public CanliDestek() {
    }

    // Getter ve Setter Metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(String kullaniciId) {
        this.kullaniciId = kullaniciId;
    }



    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public Timestamp getZaman() {
        return zaman;
    }

    public void setZaman(Timestamp zaman) {
        this.zaman = zaman;
    }

    // toString Metodu
    @Override
    public String toString() {
        return "CanliDestek{" +
                "id=" + id +
                ", kullaniciId='" + kullaniciId + '\'' +

                ", mesaj='" + mesaj + '\'' +
                ", gonderen='" + gonderen + '\'' +
                ", zaman=" + zaman +
                '}';
    }
}
