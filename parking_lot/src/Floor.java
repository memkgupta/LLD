import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;
    private final String parkingLotId;
    public Floor(int floorNumber, String parkingLotId) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();

        this.parkingLotId = parkingLotId;
    }
    public int getFloorNumber() {
        return floorNumber;
    }
    public String getParkingLotId() {
        return parkingLotId;
    }
    public List<ParkingSpot> getParkingSpots() {
        return new ArrayList<>(parkingSpots);
    }
    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }
    public ParkingSpot getFreeSpot(VehicleType vehicleType)
    {
        return parkingSpots.stream().filter(sp->!sp.getBooked() && sp.getVehicleTypeSupported().equals(vehicleType)).findFirst().orElse(null);
    }

}
