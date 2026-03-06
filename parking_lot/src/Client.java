import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory(
                List.of(new CashPaymentMethod(),new UPIPaymentMethod())
        );
        PaymentGateway paymentGateway = new PaymentGateway(paymentMethodFactory);
        ParkingLot parkingLot = new ParkingLot(paymentGateway,new PricingStrategyFactory());
        VehicleType[] distribution = {
                VehicleType.BIKE, VehicleType.BIKE, VehicleType.BIKE, VehicleType.BIKE,
                VehicleType.CAR,  VehicleType.CAR,  VehicleType.CAR,
                VehicleType.TRUCK, VehicleType.TRUCK,
                VehicleType.BUS
        };

        for (int floorNum = 1; floorNum <= 3; floorNum++) {
            Floor floor = new Floor(floorNum, "P" + floorNum);

            for (int spotNum = 1; spotNum <= 10; spotNum++) {
                String spotId = "F" + floorNum + "S" + spotNum;

                ParkingSpot spot = new ParkingSpot(
                        spotId,
                        distribution[spotNum - 1]
                );

                floor.addParkingSpot(spot);
            }

            parkingLot.addFloor(floor);
        }
        List<EntryGate> entryGates = new ArrayList<>();
        List<ExitGate> exitGates = new ArrayList<>();
        for(int i = 0;i<3;i++)
        {
            EntryGate gate = new EntryGate(parkingLot);
            entryGates.add(gate);
        }
        for(int i = 0;i<3;i++)
        {
            ExitGate gate = new ExitGate(parkingLot);
            exitGates.add(gate);
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<String> parkedVehicleIds = Collections.synchronizedList(new ArrayList<>());

        // 🚗 ENTRY SIMULATION
        for (int i = 0; i < 15; i++) {

            EntryGate gate = entryGates.get(i % entryGates.size());
            VehicleType type = VehicleType.values()[i % VehicleType.values().length];

            EntryTask task = new EntryTask(gate, type,parkedVehicleIds);
            executor.submit(task);
        }

        try {
            Thread.sleep(2000); // wait for some vehicles to park
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 🚙 EXIT SIMULATION
        for (int i = 0; i < 10 && i < parkedVehicleIds.size(); i++) {
            ExitGate gate = exitGates.get(i % exitGates.size());
            String vehicleId = parkedVehicleIds.get(i);

            ExitTask task = new ExitTask(gate, vehicleId);
            executor.submit(task);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
class EntryTask implements Runnable {

    private final EntryGate gate;
    private final VehicleType vehicleType;
    private final List<String> parkedVehicleIds;

    public EntryTask(EntryGate gate, VehicleType vehicleType, List<String> parkedVehicleIds) {
        this.gate = gate;
        this.vehicleType = vehicleType;
        this.parkedVehicleIds = parkedVehicleIds;
    }

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();

        try {
            ParkingSession session = gate.entry(vehicleType);

            if (session == null || session.getVehicle() == null) {
                System.err.println(thread + " | ENTRY FAILED | Gate: " + gate.getId()
                        + " | Reason: Session or Vehicle is null");
                return;
            }

            String vehicleId = session.getVehicle().getId();
            parkedVehicleIds.add(vehicleId);

            System.out.println(thread + " | ENTRY SUCCESS | Gate: "
                    + gate.getId() + " | VehicleId: " + vehicleId);

        } catch (IllegalStateException e) {
            System.err.println(thread + " | ENTRY DENIED | Gate: "
                    + gate.getId() + " | " + e.getMessage());

        } catch (Exception e) {
            System.err.println(thread + " | ENTRY ERROR | Gate: "
                    + gate.getId() + " | Unexpected: " + e.getMessage());
        }
    }
}
class ExitTask implements Runnable {

    private final ExitGate gate;
    private final String vehicleId;

    public ExitTask(ExitGate gate, String vehicleId) {
        this.gate = gate;
        this.vehicleId = vehicleId;
    }

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();

        try {
            if (vehicleId == null || vehicleId.isBlank()) {
                System.err.println(thread + " | EXIT FAILED | Gate: "
                        + gate.getId() + " | Invalid vehicle ID");
                return;
            }

            gate.exit(vehicleId, PaymentMethodEnum.UPI);

            System.out.println(thread + " | EXIT SUCCESS | Gate: "
                    + gate.getId() + " | VehicleId: " + vehicleId);

        } catch (IllegalArgumentException e) {
            System.err.println(thread + " | EXIT REJECTED | Gate: "
                    + gate.getId() + " | " + e.getMessage());

        } catch (Exception e) {
            System.err.println(thread + " | EXIT ERROR | Gate: "
                    + gate.getId() + " | Unexpected: " + e.getMessage());
        }
    }
}
