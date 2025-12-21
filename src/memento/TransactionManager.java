package memento;

import java.util.List;
import java.util.Stack;

public class TransactionManager {
    private final Stack<Memento> history = new Stack<>();

    public void save(Database db) {
        history.add(db.save());
    }

    public void rollback(Database db) {
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            db.restore(memento);
        } else {
            System.out.println("No transaction to rollback!");
        }
    }
}