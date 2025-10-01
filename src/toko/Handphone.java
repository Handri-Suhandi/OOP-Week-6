package toko;

public class Handphone extends Barang{
    private String warna;
    public static int total = 0;

    public Handphone(int id, String nama, int harga, int stok, String warna, double ppn){
        super(id, nama, harga, stok, ppn);
        this.warna = warna;
    }

    public String getWarna() {return warna;}
}
