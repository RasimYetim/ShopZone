package DAO;

public class SiparisDetay {
    private int siparisNo;
    private int urunId;

    // Constructor
    public SiparisDetay(int siparisNo, int urunId) {
        this.siparisNo = siparisNo;
        this.urunId = urunId;
    }

    // Getters and Setters
    public int getSiparisNo() {
        return siparisNo;
    }

    public void setSiparisNo(int siparisNo) {
        this.siparisNo = siparisNo;
    }

    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }
}
