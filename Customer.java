/**
 * Nasabah. Satu nasabah bisa punya beberapa rekening (maksimal 5),
 * disimpan dalam array Account[].
 */
public class Customer {
    private String firstName;
    private String lastName;
    private Account[] accounts = new Account[5];
    private int numberOfAccounts = 0;

    public Customer(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /** Menambah rekening ke array. Mengembalikan false jika sudah penuh. */
    public boolean setAccount(Account acct) {
        if (numberOfAccounts < accounts.length) {
            accounts[numberOfAccounts++] = acct;
            return true;
        }
        return false;
    }

    /** Mengambil rekening pada index tertentu, null jika index tidak valid. */
    public Account getAccount(int account_index) {
        if (account_index >= 0 && account_index < numberOfAccounts) {
            return accounts[account_index];
        }
        return null;
    }

    public int getNumOfAccounts() {
        return numberOfAccounts;
    }
}
