import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
private final String id;
private AtomicBoolean booked;

private VehicleType vehicleTypeSupported;
public ParkingSpot(String id , VehicleType vehicleTypeSupported) {
    this.id = id;
    this.vehicleTypeSupported = vehicleTypeSupported;
    this.booked = new AtomicBoolean(false);
}

public synchronized boolean book(Vehicle vehicle) {
    if(!vehicleTypeSupported.equals(vehicle.getVehicleType())) {
        throw new IllegalArgumentException("Vehicle type not supported");
    }
    if(booked.get()) {
        return false;
    }
    booked.set(true);
    return true;
}

    public String getId() {
        return id;
    }

    public boolean getBooked() {
        return booked.get();
    }



    public VehicleType getVehicleTypeSupported() {
        return vehicleTypeSupported;
    }

    public void setVehicleTypeSupported(VehicleType vehicleTypeSupported) {
        this.vehicleTypeSupported = vehicleTypeSupported;
    }
}
