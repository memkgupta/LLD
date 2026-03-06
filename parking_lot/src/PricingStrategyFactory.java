public class PricingStrategyFactory {
    private TimingBasedPricingStrategy strategy = new  TimingBasedPricingStrategy();
    public  PricingStrategy getPricingStrategy() {
        return strategy;
    }
}
