package memento;

public class Demo {
    public static void main(String[] args) {
        Database db = new Database();
        db.create("Rahul","Unemployed");
        System.out.println(db.getMap());
        TransactionManager tx = new TransactionManager();
        tx.save(db);
        db.update("Rahul","Election Commission");
        tx.rollback(db);
        System.out.println(db.getMap());
    }
}
