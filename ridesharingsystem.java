import java.util.Scanner;

// Custom Exception for Invalid Ride Type
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract class Ride
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    private double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    public abstract double calculateFare();

    public void displayDetails() {
        System.out.println("Driver: " + getDriverName());
        System.out.println("Vehicle No: " + getVehicleNumber());
        System.out.println("Distance: " + getDistance() + " km");
        System.out.println("Fare: ₹" + calculateFare());
    }
}

// Subclass BikeRide
class BikeRide extends Ride {
    private static final double FARE_PER_KM = 10;

    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * FARE_PER_KM;
    }
}

// Subclass CarRide
class CarRide extends Ride {
    private static final double FARE_PER_KM = 20;

    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * FARE_PER_KM;
    }
}

public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter ride type (bike or car): ");
            if (!scanner.hasNextLine()) {
                throw new InvalidRideTypeException("No input provided for ride type.");
            }
            String rideType = scanner.nextLine().trim().toLowerCase();

            if (!rideType.equals("bike") && !rideType.equals("car")) {
                throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            System.out.print("Enter distance in kilometers: ");
            if (!scanner.hasNextDouble()) {
                throw new java.util.InputMismatchException();
            }

            double distance = scanner.nextDouble();

            if (distance <= 0) {
                System.out.println("Distance must be greater than 0.");
                return;
            }

            String driverName = "John Doe";
            String vehicleNumber = "KA01AB1234";
            Ride ride;

            if (rideType.equals("bike")) {
                ride = new BikeRide(driverName, vehicleNumber, distance);
            } else {
                ride = new CarRide(driverName, vehicleNumber, distance);
            }

            ride.displayDetails();

        } catch (InvalidRideTypeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for distance.");
        } finally {
            scanner.close();
        }
    }
}
