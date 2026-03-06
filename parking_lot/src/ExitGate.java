import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ExitGate {
    private final String id;
    private final ParkingLot parkingLot;

    public String getId() {
        return id;
    }

    public ExitGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.id = UUID.randomUUID().toString();
    }
    public void exit(String vehicleId,PaymentMethodEnum paymentMethod)
    {
        Timestamp timestamp = Timestamp.from(Instant.now());
        parkingLot.unparkVehicle(vehicleId,timestamp,paymentMethod);
    }
}
