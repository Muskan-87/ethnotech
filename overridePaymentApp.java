import java.util.Scanner;

// Parent Class
class Pay {

    public void pay(double amount) {
        System.out.println("Payment is processing...");
    }
}

// Child Class 1
class UPIPayment extends Pay {

    @Override
    public void pay(double amount) {
        System.out.println("UPI Payment Successful");
        System.out.println("Amount Paid: " + amount);
    }
}

// Child Class 2
class CardPayment extends Pay {

    @Override
    public void pay(double amount) {
        System.out.println("Card Payment Successful");
        System.out.println("Amount Paid: " + amount);
    }
}

// Child Class 3
class NetBankingPayment extends Pay {

    @Override
    public void pay(double amount) {
        System.out.println("Net Banking Payment Successful");
        System.out.println("Amount Paid: " + amount);
    }
}

public class overridePaymentApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== PAYMENT SYSTEM =====");
        System.out.println("1. UPI");
        System.out.println("2. CARD");
        System.out.println("3. NET BANKING");

        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        Pay pay = null;

        switch(choice) {

            case 1:
                pay = new UPIPayment();
                break;

            case 2:
                pay = new CardPayment();
                break;

            case 3:
                pay = new NetBankingPayment();
                break;

            default:
                System.out.println("Invalid Choice");
                sc.close();
                return;
        }

        pay.pay(amount);

        sc.close();
    }
}