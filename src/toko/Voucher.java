package toko;

public class Voucher extends Barang{
    private double pajak;
    public static int total = 0;

    public voucher(int id, String nama, double hagra, int stok, double pajak){
        super(id, nama, harga, stok);
        this.pajak = pajak;
    }

    public double Pajak(){
        return pajak;
    }

    public double getHargaJual(){
        return harga + (harga * pajak / 100);
    }
}
