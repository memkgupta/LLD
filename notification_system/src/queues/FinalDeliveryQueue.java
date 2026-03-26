package queues;

import channels.Channel;
import dtos.NotificationJob;
import exceptions.FailedDeliveryException;
import exceptions.QueueFullException;
import factories.ChannelFactory;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class FinalDeliveryQueue<T extends NotificationJob> implements NotificationQueue<T> {
    /*Will have an internal PriorityQueue
    *
    * */
    protected final PriorityQueue<T> queue;
    protected final ChannelFactory channelFactory;
    protected int size ;
    protected static int MAX_SIZE = 100;
    protected static int MAX_WORKERS_COUNT = 5;
    protected final Lock lock;
    protected FinalDeliveryQueue(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
        this.size = 0;
        this.lock = new ReentrantLock();
        Comparator<NotificationJob> comparator = new Comparator<NotificationJob>() {
            @Override
            public int compare(NotificationJob job1, NotificationJob job2) {
                if(job2.getPriority() != job1.getPriority()){
                    return job1.getPriority() - job2.getPriority();
                }
                if(job1.getRetryCount() != job2.getRetryCount()){
                    return job1.getRetryCount() - job2.getRetryCount();
                }
                return 0;
            }
        };
        this.queue = new PriorityQueue<>(comparator);

    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public synchronized T poll() {
        size--;
        return queue.poll();
    }

    @Override
    public boolean offer(T job) {
        Channel<?> channel = channelFactory.getChannel(job.getChannel());
        if(job.getRetryCount()>=channel.maxRetryCount())
        {
        throw new FailedDeliveryException(job);
        }
        if(isFull())
        {
            throw new QueueFullException(job);
        }
        return queue.offer(job);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return size == MAX_SIZE;
    }
}
