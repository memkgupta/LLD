public enum VehicleType {

    BIKE(20, 100),
    CAR(50, 300),
    TRUCK(80, 500),
    BUS(100, 700);

    private final double hourlyRate;
    private final double fixedRate;

    VehicleType(double hourlyRate, double fixedRate) {
        this.hourlyRate = hourlyRate;
        this.fixedRate = fixedRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getFixedRate() {
        return fixedRate;
    }
}