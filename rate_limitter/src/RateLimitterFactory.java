

public class RateLimitterFactory {
    public static RateLimitter create(UserType type)
    {
        switch (type) {
            case FREE_TIER:
                return TokenBucket.getInstance();
            case PREMIUIM:
                return SlidingWindowLog.getInstance();
            case GUEST:
                return FixedSlidingWindow.getInstance() ;
        
            default:
                throw new RuntimeException("Invalid user type");
                
        }
    }
}
