package toko;

public class Barang {
    protected int id ;
    protected String nama;
    protected int harga;
    protected int stok;

    public Barang(int id, String nama, int harga, int stok){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
    public int getId() {return id;}

    public String getNama() {return nama;}

    public double getHarga() {return harga;}

    public int getStok() {return stok;}

    public double getPpn() {return ppn;}

    public void minusStok(int jumlah) {
        if (jumlah <= stok) {
            stok -= jumlah;
        }
    }
}
