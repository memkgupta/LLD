package adapter;

public class PostgresSQLDriver {
    public void openConnection() {
        System.out.println("Opening connection to POSTGRESQL");
    }
    public void execQuery(String query)
    {
        System.out.println("Executing query: " + query);
    }
}
