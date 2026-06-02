import java.util.Scanner;

public class TemperatureAnalysis {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] temp = new int[30];

        int highest, lowest;
        int hotDays = 0;
        int sum = 0;

        System.out.println("Enter temperatures for 30 days:");

        for(int i = 0; i < 30; i++) {
            temp[i] = sc.nextInt();
        }

        highest = temp[0];
        lowest = temp[0];

        for(int i = 0; i < 30; i++) {

            if(temp[i] > highest) {
                highest = temp[i];
            }

            if(temp[i] < lowest) {
                lowest = temp[i];
            }

            sum += temp[i];

            if(temp[i] > 35) {
                hotDays++;
            }
        }

        double average = (double) sum / 30;

        System.out.println("Highest Temperature: " + highest);
        System.out.println("Lowest Temperature: " + lowest);
        System.out.println("Average Temperature: " + average);
        System.out.println("Number of Hot Days (>35°C): " + hotDays);

        sc.close();
    }
}