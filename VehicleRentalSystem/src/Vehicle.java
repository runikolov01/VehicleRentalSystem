public abstract class Vehicle {
    private String brand;
    private String model;
    private double value;

    public Vehicle(String brand, String model, double value) {
        this.brand = brand;
        this.model = model;
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getValue() {
        return value;
    }

    public abstract double getInsuranceRatePerDay();

    public abstract double getRentalCost(int days);

    public abstract double getInsuranceAddition();
}