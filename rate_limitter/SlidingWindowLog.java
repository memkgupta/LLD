package rate_limitter;


import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLog extends RateLimitter {
    private static SlidingWindowLog instance = new SlidingWindowLog();
    private final Map<String , Queue<Long>> map;
    private SlidingWindowLog()
    {
        super(new Config());
        this.map = new ConcurrentHashMap<>();
    }
    public static SlidingWindowLog getInstance()
    {
      
        return instance;
    }
    @Override
    public boolean allowRequest(User user)
    {
        map.computeIfAbsent(user.id,key->{

            return new LinkedList<>();
        });
        Long current_timestamp = System.currentTimeMillis();
        Queue<Long> user_queue = map.get(user.id);
        while(!user_queue.isEmpty() && current_timestamp - user_queue.peek()>=MINUTE_TIMESTAMP)
        {
            user_queue.poll();
        }
        if(user_queue.size()>=10) return false;

        user_queue.offer(current_timestamp);
        return true;
    }
}
