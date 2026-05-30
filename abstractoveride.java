abstract class Foodorder {
    String customerName;
    int Quantity;
    double price;

    Foodorder() {
        System.out.println("Welcome to our food ordering system!");
    }

    Foodorder(String name, int quantity, double price) {
        this.customerName = name;
        this.Quantity = quantity;
        this.price = price;
    }

    abstract void orderFood();

    void generateBill() {
        double total = Quantity * price;

        System.out.println("Customer Name: " + customerName);
        System.out.println("Quantity: " + Quantity);
        System.out.println("Price per item: " + price);
        System.out.println("Total Bill: " + total);
    }
}

class PizzaOrder extends Foodorder {

    PizzaOrder(String name, int quantity, double price) {
        super(name, quantity, price);
    }

  
    void orderFood() {
        System.out.println("Pizza Order Confirmed!");
    }
}
public class abstractoveride {
    public static void main(String[] args) {

        PizzaOrder p1 = new PizzaOrder("Muskan", 2, 250);

        p1.orderFood();
        p1.generateBill();
    }
}