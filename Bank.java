/**
 * Bank menyimpan banyak Customer dalam array Customer[].
 */
public class Bank {
    private Customer[] customers;
    private int numberOfCustomers;

    public Bank() {
        customers = new Customer[20];
        numberOfCustomers = 0;
    }

    /** Membuat Customer baru lalu menaruhnya di array. False jika array penuh. */
    public boolean addCustomer(String f, String l) {
        if (numberOfCustomers < customers.length) {
            customers[numberOfCustomers] = new Customer(f, l);
            numberOfCustomers++;
            return true;
        }
        return false;
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    /** Mengambil Customer pada index tertentu, null jika index tidak valid. */
    public Customer getCustomer(int index) {
        if (index >= 0 && index < numberOfCustomers) {
            return customers[index];
        }
        return null;
    }
}
