/**
 * Rekening bank sederhana.
 * Encapsulation: balance private, diakses lewat method.
 */
public class Account {
    private double balance;

    public Account(double init_balance) {
        this.balance = init_balance;
    }

    public double getBalance() {
        return balance;
    }

    /** Menambah saldo. Gagal (false) jika jumlah tidak positif. */
    public boolean deposit(double amt) {
        if (amt > 0) {
            balance = balance + amt;
            return true;
        }
        return false;
    }

    /** Mengurangi saldo. Gagal (false) jika jumlah tidak valid atau saldo tidak cukup. */
    public boolean withdraw(double amt) {
        if (amt > 0 && balance >= amt) {
            balance = balance - amt;
            return true;
        }
        return false;
    }
}
