import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String customerName = getStringInput(scanner, "Enter customer name: ");

        String vehicleType;
        Vehicle vehicle = null;
        do {
            vehicleType = getStringInput(scanner, "Enter vehicle type (Car/Motorcycle/CargoVan): ").toLowerCase();
            switch (vehicleType) {
                case "car":
                    int safetyRating = getPositiveIntInput(scanner, "Enter car safety rating (1-5): ", 1, 5);
                    String carBrand = getStringInput(scanner, "Enter vehicle brand: ");
                    String carModel = getStringInput(scanner, "Enter vehicle model: ");
                    double carValue = getPositiveDoubleInput(scanner, "Enter vehicle value: ");
                    vehicle = new Car(carBrand, carModel, carValue, safetyRating);
                    break;
                case "motorcycle":
                    String motoBrand = getStringInput(scanner, "Enter vehicle brand: ");
                    String motoModel = getStringInput(scanner, "Enter vehicle model: ");
                    double motoValue = getPositiveDoubleInput(scanner, "Enter vehicle value: ");
                    int riderAge = getPositiveIntInput(scanner, "Enter rider age: ", 1, Integer.MAX_VALUE);
                    vehicle = new Motorcycle(motoBrand, motoModel, motoValue, riderAge);
                    break;
                case "cargovan":
                    String vanBrand = getStringInput(scanner, "Enter vehicle brand: ");
                    String vanModel = getStringInput(scanner, "Enter vehicle model: ");
                    double vanValue = getPositiveDoubleInput(scanner, "Enter vehicle value: ");
                    int driverExperience = getPositiveIntInput(scanner, "Enter driver's experience (years): ", 1, Integer.MAX_VALUE);
                    vehicle = new CargoVan(vanBrand, vanModel, vanValue, driverExperience);
                    break;
                default:
                    System.out.println("Invalid vehicle type. Please enter Car, Motorcycle or CargoVan.");
            }
        } while (vehicle == null);

        Date startDate = getDateInput(scanner, "Enter rental start date (yyyy-MM-dd): ");
        Date endDate = getDateInput(scanner, "Enter rental end date (yyyy-MM-dd): ");

        long reservedDays = TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);

        Date actualReturnDate = getDateInput(scanner, "Enter actual return date (yyyy-MM-dd): ");

        long actualDays = TimeUnit.DAYS.convert(actualReturnDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);

        Invoice invoice = new Invoice(customerName, vehicle, startDate, endDate, actualReturnDate, (int) reservedDays, (int) actualDays);
        invoice.printInvoice();

        scanner.close();
    }

    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getPositiveIntInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Value must be between " + min + " and " + max + ". Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive number.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return value;
    }

    private static double getPositiveDoubleInput(Scanner scanner, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Value must be positive. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive number.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return value;
    }

    private static Date getDateInput(Scanner scanner, String prompt) {
        Date date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String input = scanner.nextLine();
                date = sdf.parse(input);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        }
        return date;
    }
}