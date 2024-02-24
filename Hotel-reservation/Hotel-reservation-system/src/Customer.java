public class Customer {
    private int serialNumber; // Unique identifier for the customer
    private String name; // Customer's name
    private String bankAccount; // Customer's bank account number

    public Customer(int serialNumber, String name, String bankAccount) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.bankAccount = bankAccount;
    }

    // Getters and setters
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}