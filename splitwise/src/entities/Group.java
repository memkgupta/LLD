package entities;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Group {
    private String id;
    private Set<User> members;
    private Map<User, Map<User, Double>> balanceGraph;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Group(String id) {
        this.id = id;
        this.members = new HashSet<>();
        this.balanceGraph = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public Set<User> getMembers() {
        return members;
    }


    public synchronized Map<User, Map<User, Double>> getBalanceGraph() {
//        lock.readLock().lock();
        try {
            Map<User, Map<User, Double>> snapshot = new HashMap<>();
            for (var entry : balanceGraph.entrySet()) {
                snapshot.put(entry.getKey(), new HashMap<>(entry.getValue()));
            }
            return snapshot;
        } finally {
//            lock.readLock().unlock();
        }
    }

    public void addMember(User member) {
        lock.writeLock().lock();
        try {
            members.add(member);
            balanceGraph.put(member, new HashMap<>());
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeMember(User member) {
        lock.writeLock().lock();
        try {
            members.remove(member);
            balanceGraph.remove(member);
            // Clean up references to this member in other users' maps
            for (Map<User, Double> innerMap : balanceGraph.values()) {
                innerMap.remove(member);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Update balance: creditor is owed `amount` by debtor.
     * Use negative amount to reduce a balance.
     */
    public synchronized void updateBalance(User from, User to, double amount) {

        try {
            balanceGraph.putIfAbsent(from, new HashMap<>());
            balanceGraph.putIfAbsent(to, new HashMap<>());

            double forward = balanceGraph.get(from).getOrDefault(to, 0.0);
            double reverse = balanceGraph.get(to).getOrDefault(from, 0.0);

            if (forward > 0) {
                double net = forward - amount;
                if (net > 0) {
                    balanceGraph.get(from).put(to, net);
                } else if (net == 0) {
                    balanceGraph.get(from).remove(to);
                } else {
                    balanceGraph.get(from).remove(to);
                    balanceGraph.get(to).put(from, -net);
                }
            } else if (reverse > 0) {
                double net = reverse - amount;
                if (net > 0) {
                    balanceGraph.get(to).put(from, net);
                } else if (net == 0) {
                    balanceGraph.get(to).remove(from);
                } else {
                    balanceGraph.get(to).remove(from);
                    balanceGraph.get(from).put(to, -net);
                }
            } else {
                balanceGraph.get(from).put(to, amount);
            }
        } finally {
//            lock.writeLock().unlock();
        }
    }

    public void printGraph() {
//        lock.readLock().lock();
        try {
            System.out.println("\n===== BALANCE GRAPH : " + id + " =====");
            boolean empty = true;

            for (var fromEntry : balanceGraph.entrySet()) {
                User from = fromEntry.getKey();
                for (var toEntry : fromEntry.getValue().entrySet()) {
                    User to = toEntry.getKey();
                    double amount = toEntry.getValue();
                    if (amount > 0) {
                        empty = false;
                        System.out.println(from.getId() + " → " + to.getId() + " : " + amount);
                    }
                }
            }

            if (empty) System.out.println("No balances");
            System.out.println("=================================\n");
        } finally {
//            lock.readLock().unlock();
        }
    }

}