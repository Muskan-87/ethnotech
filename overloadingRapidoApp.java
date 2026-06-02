class RideBooking {
    void bookRide(String rideType, int distance) {
        int fare = 180;

        System.out.println("\n----- AUTO BOOKING -----");
        System.out.println("Ride Type : " + rideType);
        System.out.println("Distance : " + distance + " km");
        System.out.println("Fare : Rs." + fare);
    }
    void bookRide(String rideType, int distance, boolean bike) {
        int fare = 80;

        System.out.println("\n----- BIKE BOOKING -----");
        System.out.println("Ride Type : " + rideType);
        System.out.println("Distance : " + distance + " km");
        System.out.println("Fare : Rs." + fare);
    }
    void bookRide(String rideType, int distance, double surge) {
        double fare = 250 * surge;

        System.out.println("\n----- CAB BOOKING -----");
        System.out.println("Ride Type : " + rideType);
        System.out.println("Distance : " + distance + " km");
        System.out.println("Fare : Rs." + fare);
    }
}

public class overloadingRapidoApp {

    public static void main(String[] args) {

        RideBooking ride = new RideBooking();

        // Auto
        ride.bookRide("Auto", 2);

        // Bike
        ride.bookRide("Bike", 2, true);

        // Cab
        ride.bookRide("Cab", 2, 1.2);
    }
}