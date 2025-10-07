package toko;

import java.util.*;

public class main {
    static ArrayList<Handphone> listHp = new ArrayList<>();
    static ArrayList<Voucher> listVoucher = new ArrayList<>();
    static ArrayList<Order> listOrder = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int noUrutOrder = 1;

    public static void main(String[] args) {
        // data awal (optional)
        listHp.add(new Handphone(1, "Samsung S9+ Hitam", 14499000, 10, "Hitam"));
        listHp.add(new Handphone(2, "iPhone X Hitam", 17999000, 10, "Hitam"));
        listVoucher.add(new Voucher(1, "Google Play", 20000, 100, 10));

        int pilih;
        do {
            System.out.println("\n-----------Menu Toko Voucher & HP-----------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.print("Pilihan : ");
            pilih = inputAngka();

            switch (pilih) {
                case 1 -> pesanBarang();
                case 2 -> lihatPesanan();
                case 3 -> barangBaru();
                case 0 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 0);
    }

    static void barangBaru() {
        System.out.println("\n-----------Menu Toko Voucher & HP-----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Barang Baru");
        System.out.print("Pilihan : 3\n");
        sc.nextLine(); // clear buffer

        System.out.print("Voucher / Handphone (V/H): ");
        String jenis = sc.nextLine().trim().toLowerCase();

        if (jenis.equals("v")) {
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            System.out.print("Harga : ");
            double harga = sc.nextDouble();
            System.out.print("Stok : ");
            int stok = sc.nextInt();
            System.out.print("PPN : ");
            double pajak = sc.nextDouble();
            listVoucher.add(new Voucher(listVoucher.size() + 1, nama, harga, stok, pajak));
            System.out.println("Voucher telah berhasil diinput");
        } else if (jenis.equals("h")) {
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            System.out.print("Harga : ");
            double harga = sc.nextDouble();
            System.out.print("Stok : ");
            int stok = sc.nextInt();
            sc.nextLine();
            System.out.print("Warna : ");
            String warna = sc.nextLine();
            listHp.add(new Handphone(listHp.size() + 1, nama, harga, stok, warna));
            System.out.println("Handphone telah berhasil diinput");
        } else {
            System.out.println("Pilihan tidak sesuai!");
        }
    }

    static void pesanBarang() {
        System.out.println("\n-----------Menu Toko Voucher & HP-----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Barang Baru");
        System.out.print("Pilihan : 1\n");

        System.out.println("Daftar Barang Toko Voucher & HP");
        System.out.println("1. Handphone");
        System.out.println("2. Voucher");
        System.out.print("Pilihan : ");
        int pilih = inputAngka();

        if (pilih == 1) {
            if (listHp.isEmpty()) {
                System.out.println("Barang tidak tersedia");
                return;
            }
            for (Handphone h : listHp) {
                System.out.println("\nID : " + h.getId());
                System.out.println("Nama : " + h.getNama());
                System.out.println("Stock : " + h.getStok());
                System.out.println("Harga : " + (int) h.getHarga());
                System.out.println("-----------------------------------------------");
            }

            System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
            int id = inputAngka();
            if (id == 0) return;

            Handphone hp = cariHp(id);
            if (hp == null) {
                System.out.println("Pemilihan barang tidak sesuai");
                return;
            }

            System.out.print("Masukkan Jumlah : ");
            int jumlah = inputAngka();
            if (jumlah > hp.getStok()) {
                System.out.println("Stok tidak mencukupi jumlah pesanan");
                return;
            }

            double total = hp.getHarga() * jumlah;
            System.out.println(jumlah + " @ " + hp.getNama() + " dengan total harga " + (int) total);
            System.out.print("Masukkan jumlah uang : ");
            double uang = sc.nextDouble();
            if (uang < total) {
                System.out.println("Jumlah uang tidak mencukupi");
                return;
            }

            hp.minusStok(jumlah);
            String idOrder = "OHO" + noUrutOrder++;
            listOrder.add(new Order(idOrder, hp, jumlah));
            System.out.println("Berhasil dipesan");

        } else if (pilih == 2) {
            if (listVoucher.isEmpty()) {
                System.out.println("Barang tidak tersedia");
                return;
            }
            for (Voucher v : listVoucher) {
                System.out.println("\nID : " + v.getId());
                System.out.println("Nama : " + v.getNama());
                System.out.println("Nominal : " + (int) v.getHarga());
                System.out.println("Stock : " + v.getStok());
                System.out.println("Harga : " + (int) v.getHargaJual());
                System.out.println("-----------------------------------------------");
            }

            System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
            int id = inputAngka();
            if (id == 0) return;

            Voucher vc = cariVoucher(id);
            if (vc == null) {
                System.out.println("Pemilihan barang tidak sesuai");
                return;
            }

            System.out.print("Masukkan Jumlah : ");
            int jumlah = inputAngka();
            if (jumlah > vc.getStok()) {
                System.out.println("Stok tidak mencukupi jumlah pesanan");
                return;
            }

            double total = vc.getHargaJual() * jumlah;
            System.out.println(jumlah + " @ " + vc.getNama() + " dengan harga " + (int) vc.getHargaJual());
            System.out.print("Masukkan jumlah uang : ");
            double uang = sc.nextDouble();
            if (uang < total) {
                System.out.println("Jumlah uang tidak mencukupi");
                return;
            }

            vc.minusStok(jumlah);
            String idOrder = "OVI" + noUrutOrder++;
            listOrder.add(new Order(idOrder, vc, jumlah));
            System.out.println("Berhasil dipesan");
        }
    }

    static void lihatPesanan() {
        System.out.println("\n-----------Menu Toko Voucher & HP-----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Barang Baru");
        System.out.print("Pilihan : 2\n");

        if (listOrder.isEmpty()) {
            System.out.println("Belum ada pesanan");
            return;
        }

        System.out.println("Daftar Pesanan Toko Multiguna");
        for (Order o : listOrder) {
            System.out.println("\nID : " + o.getId());
            if (o.getHandphone() != null) {
                System.out.println("Nama : " + o.getHandphone().getNama());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.println("Total : " + (int) (o.getHandphone().getHarga() * o.getJumlah()));
            } else {
                System.out.println("Nama : " + o.getVoucher().getNama() + " " + (int) o.getVoucher().getHarga());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.println("Total : " + (int) (o.getVoucher().getHargaJual() * o.getJumlah()));
            }
        }
    }

    static Handphone cariHp(int id) {
        for (Handphone h : listHp) {
            if (h.getId() == id) return h;
        }
        return null;
    }

    static Voucher cariVoucher(int id) {
        for (Voucher v : listVoucher) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    static int inputAngka() {
        while (!sc.hasNextInt()) {
            System.out.print("Input harus angka: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
