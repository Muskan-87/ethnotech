import java.util.Scanner;
interface Notification {

    void sendNotification(String message);
}
class EmailNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("Email Sent: " + message);
    }
}
class SMSNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS Sent: " + message);
    }
}
class WhatsAppNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("WhatsApp Message Sent: " + message);
    }
}
class InstagramNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("Instagram Notification: " + message);
    }
}
class JobNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("Job Alert: " + message);
    }
}
public class overrideNotificationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Notification System =====");
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. WhatsApp");
        System.out.println("4. Instagram");
        System.out.println("5. Job Alert");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Message: ");
        String msg = sc.nextLine();

        Notification notification = null;

        switch(choice) {

            case 1:
                notification = new EmailNotification();
                break;

            case 2:
                notification = new SMSNotification();
                break;

            case 3:
                notification = new WhatsAppNotification();
                break;

            case 4:
                notification = new InstagramNotification();
                break;

            case 5:
                notification = new JobNotification();
                break;

            default:
                System.out.println("Invalid Choice");
                System.exit(0);
        }

        notification.sendNotification(msg);

        sc.close();
    }
}