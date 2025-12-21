package adapter;

public class PostgresSQLAdapter implements Database{
    private final PostgresSQLDriver driver;
    public PostgresSQLAdapter(PostgresSQLDriver driver) {
        this.driver = driver;
    }

    @Override
    public void connect() {
        driver.openConnection();
    }

    @Override
    public void insert(Object query) {
        driver.execQuery((String)query);
    }
}
