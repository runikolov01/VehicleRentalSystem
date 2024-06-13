public class Car extends Vehicle {
    private final int safetyRating;

    public Car(String brand, String model, double value, int safetyRating) {
        super(brand, model, value);
        this.safetyRating = safetyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }

    @Override
    public double getInsuranceRatePerDay() {
        return getValue() * 0.0001;
    }

    @Override
    public double getRentalCost(int days) {
        return days > 7 ? 15 : 20;
    }

    @Override
    public double getInsuranceAddition() {
        return (safetyRating == 4 || safetyRating == 5) ? -(getInsuranceRatePerDay() * 0.1) : 0;
    }
}