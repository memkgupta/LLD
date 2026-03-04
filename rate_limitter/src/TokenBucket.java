
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket extends RateLimitter {
    private static TokenBucket instance = new TokenBucket();
    
    private final Map<String,Integer> tokenMap;
    private final double REFILL_RATE = 10.0/60000.0;
    private final Map<String,Long> lastRefillTime;
    private TokenBucket()
    {
        super(new Config());
        tokenMap = new ConcurrentHashMap<>();
        lastRefillTime = new ConcurrentHashMap<>();
    }
    public static TokenBucket getInstance()
    {
       
    return instance;      
    }
    @Override
    public boolean allowRequest(User user)
    {
        Long current_timestamp = System.currentTimeMillis();
      if(!tokenMap.containsKey(user.id))
      {
        tokenMap.put(user.id,10);
        lastRefillTime.put(user.id, current_timestamp);
      }
       else{
        Long last = lastRefillTime.get(user.id);
        Long diff = current_timestamp - last;
      double tokensToAdd = diff * REFILL_RATE;
      tokenMap.put(user.id,Math.min(10,tokenMap.get(user.id)+(int)tokensToAdd));
      lastRefillTime.put(user.id,current_timestamp);
        boolean result =  tokenMap.get(user.id)>0;
        if(result)
        {
            tokenMap.put(user.id, tokenMap.get(user.id) - 1);
            return true;
        }
   
       }
     return false;

    }
}
