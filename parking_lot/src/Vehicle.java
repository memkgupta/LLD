import java.util.UUID;

public  class Vehicle {
    private final String id;
    private final VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
