package factories;

import enums.QueueStage;

import java.util.Queue;

public interface QueueFactory {
    Queue<?> getQueue();
    QueueStage supports();
}
