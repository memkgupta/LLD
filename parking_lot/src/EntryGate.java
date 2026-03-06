import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class EntryGate {
    private final String gateId;
    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.gateId = UUID.randomUUID().toString();
        this.parkingLot = parkingLot;
    }

    public String getId() {
        return gateId;
    }

    public ParkingSession entry(VehicleType vehicleType)
    {

        Vehicle vehicle = new Vehicle(vehicleType);
        return parkingLot.parkVehicle(vehicle, Timestamp.from(Instant.now()));
    }

}
