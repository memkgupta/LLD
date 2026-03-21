package services;

import entities.Group;
import entities.Transaction;
import entities.User;

import java.util.*;
public class SettlementService {
    private final BalanceService balanceService;

    public SettlementService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public synchronized List<Transaction> simplifyDebt(Group group) {
        // Take a consistent snapshot under the read lock

        Map<User, Map<User, Double>> graph;
        Set<User> members;

            graph   = group.getBalanceGraph(); // already returns deep copy
            members = group.getMembers();      // returns unmodifiable snapshot


        // Pure computation — no shared state, no lock needed
        Map<User, Double> netBalance = new HashMap<>();
        for (User u : members) {
            netBalance.put(u, 0.0);
        }

        for (User u : members) {
            Map<User, Double> edges = graph.getOrDefault(u, Collections.emptyMap());
            for (var entry : edges.entrySet()) {
                User v      = entry.getKey();
                double amt  = entry.getValue();
                netBalance.merge(u, -amt, Double::sum);
                netBalance.merge(v,  amt, Double::sum);
            }
        }

        PriorityQueue<Map.Entry<User, Double>> owes =
                new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));

        PriorityQueue<Map.Entry<User, Double>> gains =
                new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));

        for (var entry : netBalance.entrySet()) {
            double val = entry.getValue();
            if (val < -1e-9)       owes.add(Map.entry(entry.getKey(), -val));
            else if (val >  1e-9)  gains.add(Map.entry(entry.getKey(), val));
        }

        List<Transaction> result = new ArrayList<>();

        while (!owes.isEmpty() && !gains.isEmpty()) {
            var o = owes.poll();
            var g = gains.poll();

            double settleAmount = Math.min(o.getValue(), g.getValue());
            result.add(new Transaction(o.getKey(), g.getKey(), settleAmount, null));

            if (o.getValue() - settleAmount > 1e-9)
                owes.add(Map.entry(o.getKey(), o.getValue() - settleAmount));

            if (g.getValue() - settleAmount > 1e-9)
                gains.add(Map.entry(g.getKey(), g.getValue() - settleAmount));
        }

        return result;
    }

    public synchronized void settle(Group group, User from, User to, double amount) {

            balanceService.updateBalance(group, from, to, amount);
    }
}