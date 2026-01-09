package zomato;

import zomato.interfaces.Consumer;
import zomato.interfaces.OrderConsumer;
import zomato.interfaces.OrderProducer;
import zomato.interfaces.Producer;

import java.util.*;

public class ProducerConsumerManager {

    private final Map<Producer<?>, List<Consumer<?>>> map;


    private ProducerConsumerManager() {
        map = new HashMap<>();
    }

    // 2️⃣ static inner holder class
    private static class Holder {
        private static final ProducerConsumerManager INSTANCE =
                new ProducerConsumerManager();
    }

    // 3️⃣ global access point
    public static ProducerConsumerManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized <T> void registerConsumer(
            Producer<T> producer,
            Consumer<T> consumer
    ) {
        map.putIfAbsent(producer, new ArrayList<>());
        map.get(producer).add(consumer);
    }
    public synchronized void notifyConsumers(Producer<?> producer) {
       map.get(producer).forEach(
               consumer -> consumer.notification()
       );
    }
}
