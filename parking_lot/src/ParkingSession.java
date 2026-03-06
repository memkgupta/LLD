import java.sql.Timestamp;

public class ParkingSession {
    private final Vehicle vehicle;
    private final Timestamp entryTime;
    private Timestamp exitTime;
    private final String spotId;

    public ParkingSession(Vehicle vehicle, Timestamp entryTime, String spotId) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.spotId = spotId;

    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public Timestamp getEntryTime() {
        return entryTime;
    }



    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public String getSpotId() {
        return spotId;
    }
}
