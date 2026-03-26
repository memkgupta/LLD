package factories;

import enums.QueueStage;

import java.util.List;

public class AbstractQueueFactory {
    private final List<QueueFactory> factoryList;

    public AbstractQueueFactory(List<QueueFactory> factoryList) {
        this.factoryList = factoryList;
    }

    QueueFactory getQueueFactory(QueueStage queueStage) {
   return factoryList.stream().filter(qf->qf.supports().equals(queueStage)).findFirst().orElseThrow();
    }

}
