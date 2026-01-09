package rate_limitter;

public abstract class RateLimitter {
    protected Config config;
        protected static Long MINUTE_TIMESTAMP = 60000l;

protected RateLimitter(Config config)
    {
        this.config = config;
    }
   public abstract boolean allowRequest(User user); 
}
