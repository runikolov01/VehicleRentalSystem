public class CargoVan extends Vehicle {
    private final int driverExperience;

    public CargoVan(String brand, String model, double value, int driverExperience) {
        super(brand, model, value);
        this.driverExperience = driverExperience;
    }

    public int getDriverExperience() {
        return driverExperience;
    }

    @Override
    public double getInsuranceRatePerDay() {
        return getValue() * 0.0003;
    }

    @Override
    public double getRentalCost(int days) {
        return days > 7 ? 40 : 50;
    }

    @Override
    public double getInsuranceAddition() {
        return (driverExperience > 5) ? -(getInsuranceRatePerDay() * 0.15) : 0;
    }
}