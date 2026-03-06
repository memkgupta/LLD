import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private final List<Floor> floors;
    private final PaymentGateway paymentGateway;
    private final Map<String,ParkingSession> sessions;
    private final PricingStrategyFactory pricingStrategyFactory;
    public ParkingLot(PaymentGateway paymentGateway, PricingStrategyFactory pricingStrategyFactory) {
        this.paymentGateway = paymentGateway;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.floors = new ArrayList<>();
        this.sessions = new HashMap<>();
    }
    public List<Floor> getFloors() {
        return new ArrayList<>(this.floors);
    }
    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }
    public ParkingSession parkVehicle(Vehicle vehicle , Timestamp entry){
        for(Floor floor : this.floors){
            ParkingSpot spot = floor.getFreeSpot(vehicle.getVehicleType());
            if(spot != null && spot.book(vehicle)){
                ParkingSession session = new ParkingSession(vehicle,entry,spot.getId());
                this.sessions.put(vehicle.getId(),session);
                return session;
            }
        }
        throw new RuntimeException("No free spot found");
    }
    public void unparkVehicle(String vehicleId , Timestamp exit , PaymentMethodEnum paymentMethod){
        if(!sessions.containsKey(vehicleId)){
            throw new IllegalArgumentException("Vehicle does not exist");
        }
        ParkingSession session = sessions.get(vehicleId);
        session.setExitTime(exit);
        paymentGateway.pay(pricingStrategyFactory.getPricingStrategy().getPrice(session),paymentMethod);
        sessions.remove(vehicleId);
    }

}
