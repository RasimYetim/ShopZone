package DAO;
import java.util.Base64;
public class Urun {
    private int urunId;
    private String urunAdi;
    private int stok;



    private float fiyat;
    private float puan;
    private String kategori;
    private byte[] resim;

    public Urun() {
    }

    public Urun(String urunAdi, int stok, float fiyat, String kategori, byte[] resim) {
        this.urunAdi = urunAdi;
        this.stok = stok;
        this.fiyat = fiyat;
        this.puan = 0.0f; // Yeni ürün için varsayılan puan
        this.kategori = kategori;
        this.resim = resim;
    }
    public Urun(int urun_id, String urunAdi, int stok, float fiyat, String kategori, byte[] resim, float puan) {
        this.urunId = urun_id;
        this.urunAdi = urunAdi;
        this.stok = stok;
        this.fiyat = fiyat;
        this.puan = puan; // Yeni ürün için varsayılan puan
        this.kategori = kategori;
        this.resim = resim;
    }

    // Getters ve Setters
    public int getUrunId() { return urunId; }
    public void setUrunId(int urunId) { this.urunId = urunId; }

    public String getUrunAdi() { return urunAdi; }
    public void setUrunAdi(String urunAdi) { this.urunAdi = urunAdi; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public float getFiyat() { return fiyat; }
    public void setFiyat(float fiyat) { this.fiyat = fiyat; }

    public float getPuan() { return puan; }
    public void setPuan(float puan) { this.puan = puan; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public byte[] getResim() { return resim; }
    public void setResim(byte[] resim) { this.resim = resim; }
    public String getResimBase64() {
        if (resim != null) {
            return Base64.getEncoder().encodeToString(resim);
        }
        return ""; // Resim yoksa boş dön
    }
}
