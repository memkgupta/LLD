package services;

import entities.Group;
import entities.SplitType;
import entities.Transaction;
import entities.User;
import strategie.SplittingStrategy;
import strategie.StrategyFactory;

import java.util.List;
import java.util.Set;

public class ExpenseService {
    private final BalanceService balanceService;
    public ExpenseService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }
    public synchronized void addExpense(Group group, User payee, double amount,
                           Set<User> participants, SplitType type, Object extra) {

            if (participants.stream().anyMatch(p -> !group.getMembers().contains(p))) {
                throw new IllegalArgumentException("Invalid participants list");
            }


        // Pure computation — no lock needed
        SplittingStrategy strategy = StrategyFactory.getSplittingStrategy(type);
        List<Transaction> transactions = strategy.split(group, participants, payee, amount, extra);

       synchronized(this){
           for (Transaction t : transactions) {
               balanceService.updateBalance(group, t.getFrom(), t.getTo(), t.getAmount());
           }
        }

        }
    }


