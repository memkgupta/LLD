package adapter;


public class MongoDBAdapter implements Database {
    private final MongoDBDriver driver;
    public MongoDBAdapter(MongoDBDriver driver) {
        this.driver = driver;
    }
    @Override
    public void connect() {
        System.out.println("connecting to mongodb");
        driver.openConnection();

    }

    @Override
    public void insert(Object json) {
        System.out.println("inserting to MongoDB: " + json);
    driver.addDocument((String) json);
    }
}
