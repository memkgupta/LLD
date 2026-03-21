package entities;

public class Transaction {
    private final User from;
    private final User to;
    private final double amount;
    private final Group group;

    public Transaction(User from, User to, double amount, Group group) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.group = group;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                ", group=" + group +
                '}';
    }
}
