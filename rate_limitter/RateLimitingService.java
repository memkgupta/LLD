package rate_limitter;

import java.util.HashMap;
import java.util.Map;

public class RateLimitingService {
    
    private final Map<UserType , RateLimitter> rateLimitterMap;
    public RateLimitingService()
    {
        rateLimitterMap = new HashMap<>();

        rateLimitterMap.put(UserType.FREE_TIER,
            RateLimitterFactory.create(UserType.FREE_TIER)
        );
        rateLimitterMap.put(
            UserType.PREMIUIM, 
        RateLimitterFactory.create(UserType.PREMIUIM));
        
        rateLimitterMap.put(
            UserType.GUEST,
        RateLimitterFactory.create(UserType.GUEST));
    }
    public boolean allowRequest(User user)
    {
        return rateLimitterMap.get(user.type).allowRequest(user);
    }
}
