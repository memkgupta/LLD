

public class FixedSlidingWindow extends RateLimitter{
    private static FixedSlidingWindow instance = new FixedSlidingWindow();
    
    private FixedSlidingWindow()
    {
        super(new Config());
    }
    public static FixedSlidingWindow getInstance()
    {
        if(instance == null)
        {
            instance = new FixedSlidingWindow();
        }
        return instance;
    }
    @Override
    public boolean allowRequest(User user)
    {
        return true;
    }
}
