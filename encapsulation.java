
public class encapsulation {
    private int balance;
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public static void main(String[] args) {
        encapsulation bal = new encapsulation();
        bal.setBalance(1000);
        System.out.println(bal.getBalance());
    }
}

