import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== MENU BANK ===");
            System.out.println("1. Demo otomatis (Account, Customer, Bank)");
            System.out.println("2. Tambah nasabah");
            System.out.println("3. Daftar nasabah");
            System.out.println("4. Buka rekening untuk nasabah");
            System.out.println("5. Cek saldo");
            System.out.println("6. Setor tunai");
            System.out.println("7. Tarik tunai");
            System.out.println("0. Keluar");
            pilihan = bacaInt("Pilih: ");

            switch (pilihan) {
                case 1: demo(); break;
                case 2: tambahNasabah(); break;
                case 3: daftarNasabah(); break;
                case 4: bukaRekening(); break;
                case 5: cekSaldo(); break;
                case 6: setor(); break;
                case 7: tarik(); break;
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    // ---------- Demo otomatis ----------
    static void demo() {
        System.out.println("\n--- Demo Account ---");
        Account a = new Account(500);
        System.out.println("Saldo awal      : " + a.getBalance());
        System.out.println("Deposit 250     : " + a.deposit(250) + ", saldo = " + a.getBalance());
        System.out.println("Deposit -10     : " + a.deposit(-10) + ", saldo = " + a.getBalance());
        System.out.println("Withdraw 300    : " + a.withdraw(300) + ", saldo = " + a.getBalance());
        System.out.println("Withdraw 9999   : " + a.withdraw(9999) + ", saldo = " + a.getBalance());

        System.out.println("\n--- Demo Customer (array Account) ---");
        Customer c = new Customer("Budi", "Santoso");
        c.setAccount(new Account(1000));
        c.setAccount(new Account(2500));
        c.setAccount(new Account(300));
        System.out.println("Nasabah         : " + c.getFirstName() + " " + c.getLastName());
        System.out.println("Jumlah rekening : " + c.getNumOfAccounts());
        for (int i = 0; i < c.getNumOfAccounts(); i++) {
            System.out.println("Rekening " + i + "     : saldo = " + c.getAccount(i).getBalance());
        }

        System.out.println("\n--- Demo Bank (array Customer) ---");
        Bank b = new Bank();
        b.addCustomer("Siti", "Aminah");
        b.addCustomer("Andi", "Wijaya");
        b.addCustomer("Dewi", "Lestari");
        b.getCustomer(0).setAccount(new Account(1500));
        b.getCustomer(1).setAccount(new Account(700));
        b.getCustomer(1).setAccount(new Account(200));
        System.out.println("Jumlah nasabah  : " + b.getNumOfCustomers());
        for (int i = 0; i < b.getNumOfCustomers(); i++) {
            Customer cu = b.getCustomer(i);
            System.out.println((i + 1) + ". " + cu.getFirstName() + " " + cu.getLastName()
                    + " (" + cu.getNumOfAccounts() + " rekening)");
        }
    }

    // ---------- Menu ATM ----------
    static void tambahNasabah() {
        System.out.print("Nama depan    : ");
        String f = input.nextLine().trim();
        System.out.print("Nama belakang : ");
        String l = input.nextLine().trim();
        if (f.isEmpty()) {
            System.out.println("Nama depan tidak boleh kosong.");
            return;
        }
        if (bank.addCustomer(f, l)) {
            System.out.println("Nasabah ditambahkan. Nomor nasabah: " + bank.getNumOfCustomers());
        } else {
            System.out.println("Gagal: kapasitas bank penuh.");
        }
    }

    static void daftarNasabah() {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada nasabah.");
            return;
        }
        System.out.println("\nDaftar nasabah:");
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getFirstName() + " " + c.getLastName()
                    + " - " + c.getNumOfAccounts() + " rekening");
        }
    }

    static void bukaRekening() {
        Customer c = pilihNasabah();
        if (c == null) return;
        double saldoAwal = bacaDouble("Saldo awal: ");
        if (saldoAwal < 0) {
            System.out.println("Saldo awal tidak boleh negatif.");
            return;
        }
        if (c.setAccount(new Account(saldoAwal))) {
            System.out.println("Rekening dibuka. Nomor rekening: " + c.getNumOfAccounts());
        } else {
            System.out.println("Gagal: nasabah sudah punya 5 rekening.");
        }
    }

    static void cekSaldo() {
        Account a = pilihRekening();
        if (a != null) {
            System.out.println("Saldo: " + a.getBalance());
        }
    }

    static void setor() {
        Account a = pilihRekening();
        if (a == null) return;
        double jumlah = bacaDouble("Jumlah setor: ");
        if (a.deposit(jumlah)) {
            System.out.println("Setor berhasil. Saldo sekarang: " + a.getBalance());
        } else {
            System.out.println("Setor gagal: jumlah harus lebih dari 0.");
        }
    }

    static void tarik() {
        Account a = pilihRekening();
        if (a == null) return;
        double jumlah = bacaDouble("Jumlah tarik: ");
        if (a.withdraw(jumlah)) {
            System.out.println("Tarik berhasil. Saldo sekarang: " + a.getBalance());
        } else {
            System.out.println("Tarik gagal: jumlah tidak valid atau saldo tidak cukup.");
        }
    }

    // ---------- Helper ----------
    static Customer pilihNasabah() {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada nasabah. Tambah nasabah dulu (menu 2).");
            return null;
        }
        daftarNasabah();
        int no = bacaInt("Nomor nasabah: ");
        Customer c = bank.getCustomer(no - 1);
        if (c == null) {
            System.out.println("Nomor nasabah tidak valid.");
        }
        return c;
    }

    static Account pilihRekening() {
        Customer c = pilihNasabah();
        if (c == null) return null;
        if (c.getNumOfAccounts() == 0) {
            System.out.println("Nasabah belum punya rekening. Buka rekening dulu (menu 4).");
            return null;
        }
        int no = bacaInt("Nomor rekening (1-" + c.getNumOfAccounts() + "): ");
        Account a = c.getAccount(no - 1);
        if (a == null) {
            System.out.println("Nomor rekening tidak valid.");
        }
        return a;
    }

    static int bacaInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double bacaDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
