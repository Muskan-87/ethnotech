import java.util.Scanner;

class Food {
    String foodName;
    int price;

    void orderFood(int qty) {
        System.out.println("Food Ordered");
    }
}

class Pizza extends Food {

    Pizza() {
        foodName = "Pizza";
        price = 250;
    }

    @Override
    void orderFood(int qty) {
        int total = qty * price;
        System.out.println("\n----- BILL -----");
        System.out.println("Item : " + foodName);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qty);
        System.out.println("Total Amount : " + total);
    }
}

class Burger extends Food {

    Burger() {
        foodName = "Burger";
        price = 150;
    }

    @Override
    void orderFood(int qty) {
        int total = qty * price;
        System.out.println("\n----- BILL -----");
        System.out.println("Item : " + foodName);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qty);
        System.out.println("Total Amount : " + total);
    }
}

class Biryani extends Food {

    Biryani() {
        foodName = "Biryani";
        price = 300;
    }

    @Override
    void orderFood(int qty) {
        int total = qty * price;
        System.out.println("\n----- BILL -----");
        System.out.println("Item : " + foodName);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qty);
        System.out.println("Total Amount : " + total);
    }
}

class Dosa extends Food {

    Dosa() {
        foodName = "Dosa";
        price = 100;
    }

    @Override
    void orderFood(int qty) {
        int total = qty * price;
        System.out.println("\n----- BILL -----");
        System.out.println("Item : " + foodName);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qty);
        System.out.println("Total Amount : " + total);
    }
}

class Noodles extends Food {

    Noodles() {
        foodName = "Noodles";
        price = 180;
    }

    @Override
    void orderFood(int qty) {
        int total = qty * price;
        System.out.println("\n----- BILL -----");
        System.out.println("Item : " + foodName);
        System.out.println("Price : " + price);
        System.out.println("Quantity : " + qty);
        System.out.println("Total Amount : " + total);
    }
}

public class inheritanceOverrideSwiggyApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========== SWIGGY ==========");
        System.out.println("1. Pizza      - Rs.250");
        System.out.println("2. Burger     - Rs.150");
        System.out.println("3. Biryani    - Rs.300");
        System.out.println("4. Dosa       - Rs.100");
        System.out.println("5. Noodles    - Rs.180");

        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Food f;

        switch (choice) {

            case 1:
                f = new Pizza();
                f.orderFood(qty);
                break;

            case 2:
                f = new Burger();
                f.orderFood(qty);
                break;

            case 3:
                f = new Biryani();
                f.orderFood(qty);
                break;

            case 4:
                f = new Dosa();
                f.orderFood(qty);
                break;

            case 5:
                f = new Noodles();
                f.orderFood(qty);
                break;

            default:
                System.out.println("Invalid Choice");
        }

        sc.close();
    }
}