import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Invoice {
    private final String customerName;
    private final Vehicle vehicle;
    private final Date startDate;
    private final Date endDate;
    private final Date actualEndDate;
    private final int reservedDays;
    private final int actualDays;

    public Invoice(String customerName, Vehicle vehicle, Date startDate, Date endDate, Date actualEndDate, int reservedDays, int actualDays) {
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualEndDate = actualEndDate;
        this.reservedDays = reservedDays;
        this.actualDays = actualDays;
    }

    public void printInvoice() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#0.00");

        double rentalCostPerDay = vehicle.getRentalCost(reservedDays);
        double initialInsurancePerDay = vehicle.getInsuranceRatePerDay();
        double insuranceAdditionPerDay = vehicle.getInsuranceAddition();
        double insurancePerDay = initialInsurancePerDay + insuranceAdditionPerDay;

        double totalRent = rentalCostPerDay * actualDays + (reservedDays - actualDays) * rentalCostPerDay / 2;
        double totalInsurance = insurancePerDay * actualDays;

        double insuranceDiscountPerDay = 0;

        if (vehicle instanceof Car car) {
            if (car.getSafetyRating() == 4 || car.getSafetyRating() == 5) {
                insuranceDiscountPerDay = initialInsurancePerDay * 0.1;
            }
        } else if (vehicle instanceof CargoVan van) {
            if (van.getDriverExperience() > 5) {
                insuranceDiscountPerDay = initialInsurancePerDay * 0.15;
            }
        }

        double earlyReturnRentDiscount = 0;
        double earlyReturnInsuranceDiscount = 0;

        long daysDifference = TimeUnit.DAYS.convert(endDate.getTime() - actualEndDate.getTime(), TimeUnit.MILLISECONDS);

        if (daysDifference > 0) {
            earlyReturnRentDiscount = (daysDifference * rentalCostPerDay) / 2;
            earlyReturnInsuranceDiscount = (totalInsurance / actualDays) * daysDifference;
        }

        System.out.println();
        System.out.println("XXXXXXXXXX");
        System.out.println("Date: " + sdf.format(new Date()));
        System.out.println("Customer Name: " + customerName);
        System.out.println("Rented Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println();
        System.out.println("Reservation start date: " + sdf.format(startDate));
        System.out.println("Reservation end date: " + sdf.format(endDate));
        System.out.println("Reserved rental days: " + reservedDays + " days");
        System.out.println();
        System.out.println("Actual Return date: " + sdf.format(actualEndDate));
        System.out.println("Actual rental days: " + actualDays + " days");
        System.out.println();
        System.out.println("Rental cost per day: $" + df.format(rentalCostPerDay));

        if (insuranceAdditionPerDay > 0) {
            System.out.println("Initial insurance per day: $" + df.format(initialInsurancePerDay));
            System.out.println("Insurance addition per day: $" + df.format(insuranceAdditionPerDay));
        }

        if (insuranceDiscountPerDay != 0) {
            System.out.println("Initial insurance per day: $" + df.format(initialInsurancePerDay));
            System.out.println("Insurance discount per day: $" + df.format(insuranceDiscountPerDay));
        }

        System.out.println("Insurance per day: $" + df.format(insurancePerDay));

        if (earlyReturnRentDiscount > 0) {
            System.out.println();
            System.out.println("Early return discount for rent: $" + df.format(earlyReturnRentDiscount));
        }
        if (earlyReturnInsuranceDiscount > 0) {
            System.out.println("Early return discount for insurance: $" + df.format(earlyReturnInsuranceDiscount));
        }

        System.out.println();
        System.out.println("Total rent: $" + df.format(totalRent));
        System.out.println("Total insurance: $" + df.format(totalInsurance));
        System.out.println("Total: $" + df.format(totalRent + totalInsurance));
        System.out.println("XXXXXXXXXX");
    }
}