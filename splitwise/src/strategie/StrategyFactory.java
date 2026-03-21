package strategie;

import entities.SplitType;

public class StrategyFactory {
    private static final EqualSplittingStrategy equalSplittingStrategy = new EqualSplittingStrategy();
    private static PercentageSplittingStrategy percentageSplittingStrategy = new PercentageSplittingStrategy();
    public static SplittingStrategy<?> getSplittingStrategy(SplitType splitType)
    {
        switch (splitType)
            {
            case EQUAL:
                return equalSplittingStrategy;
            case PERCENTAGE:
                    return percentageSplittingStrategy;
            default:
                throw new IllegalArgumentException("Invalid SplitType");
            }
    }

    public static PercentageSplittingStrategy getPercentageSplittingStrategy() {
        return percentageSplittingStrategy;
    }

    public static void setPercentageSplittingStrategy(PercentageSplittingStrategy percentageSplittingStrategy) {
        StrategyFactory.percentageSplittingStrategy = percentageSplittingStrategy;
    }
}
