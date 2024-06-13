public class Motorcycle extends Vehicle {
    private final int riderAge;

    public Motorcycle(String brand, String model, double value, int riderAge) {
        super(brand, model, value);
        this.riderAge = riderAge;
    }

    public int getRiderAge() {
        return riderAge;
    }

    @Override
    public double getInsuranceRatePerDay() {
        return getValue() * 0.0002;
    }

    @Override
    public double getRentalCost(int days) {
        return days > 7 ? 10 : 15;
    }

    @Override
    public double getInsuranceAddition() {
        return (riderAge < 25) ? (getInsuranceRatePerDay() * 0.2) : 0;
    }
}