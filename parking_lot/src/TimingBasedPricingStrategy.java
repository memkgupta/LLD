import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TimingBasedPricingStrategy implements PricingStrategy {
    @Override
    public double getPrice(ParkingSession parkingSession) {
        Timestamp entryTime = parkingSession.getEntryTime();
        Timestamp exitTime  = parkingSession.getExitTime();

        long diffMillis = exitTime.getTime() - entryTime.getTime();
        long totalHours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        return totalHours * parkingSession.getVehicle().getVehicleType().getHourlyRate() + parkingSession.getVehicle().getVehicleType().getFixedRate();
    }
}
