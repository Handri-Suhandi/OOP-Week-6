package toko;

public class Barang {
    protected int id;
    protected String nama;
    protected double harga;
    protected int stok;

    public Barang(int id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void minusStok(int jml) {
        this.stok -= jml;
    }
}