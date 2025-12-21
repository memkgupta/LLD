package adapter;

public class MongoDBDriver {
    public void openConnection() {
        System.out.println("MongoDB connection established.");
    }

    public void addDocument(String json) {
        System.out.println("Document added to MongoDB: " + json);
    }
}
