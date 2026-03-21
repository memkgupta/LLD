import entities.Group;
import entities.SplitType;
import entities.Transaction;
import entities.User;
import services.BalanceService;
import services.ExpenseService;
import services.SettlementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadedTest {

    public static void main(String[] args) {

        Group g1 = new Group("group1");
        Group g2 = new Group("group2");

        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");

        g1.addMember(user1);
        g1.addMember(user2);
        g1.addMember(user3);

//        g2.addMember(user1);
//        g2.addMember(user4);

        BalanceService balanceService = new BalanceService();
        ExpenseService expenseService = new ExpenseService(balanceService);
        SettlementService settlementService = new SettlementService(balanceService);

        System.out.println("===== START =====");

        // 🔹 Step 1
        log(g1, "EXPENSE", "user1 pays 300 for u1,u2,u3");
        expenseService.addExpense(g1, user1, 300.0,
                Set.of(user1, user2, user3),
                SplitType.EQUAL, null);

        printState(g1, settlementService);

        // 🔹 Step 2
        log(g1, "EXPENSE", "user2 pays 150 for u1,u2");
        expenseService.addExpense(g1, user2, 150.0,
                Set.of(user1, user2),
                SplitType.EQUAL, null);

        printState(g1, settlementService);

        // 🔹 Step 3
        log(g1, "SETTLEMENT", "user2 pays user1 50");
        settlementService.settle(g1, user2, user1, 50.0);

        printState(g1, settlementService);

        // 🔹 Step 4
        log(g1, "EXPENSE", "user3 pays 90 for u1,u3");
        expenseService.addExpense(g1, user3, 90.0,
                Set.of(user1, user3),
                SplitType.EQUAL, null);

        printState(g1, settlementService);

        // 🔹 Step 5
        log(g1, "SETTLEMENT", "user3 pays user1 30");
        settlementService.settle(g1, user3, user1, 30.0);

        printState(g1, settlementService);

        // 🔹 Group2
//        log(g2, "EXPENSE", "user1 pays 200 for u1,u4");
//        expenseService.addExpense(g2, user1, 200.0,
//                Set.of(user1, user4),
//                SplitType.EQUAL, null);

        System.out.println("\n===== FINAL SIMPLIFIED =====");
        printSimplified(g1, settlementService);
//        printSimplified(g2, settlementService);
    }

    private static void log(Group g, String type, String msg) {
        System.out.println("[" + g.getId() + "][" + type + "] " + msg);
    }

    private static void printState(Group g, SettlementService settlementService) {
        System.out.println("---- CURRENT STATE (" + g.getId() + ") ----");
        for (Transaction t : settlementService.simplifyDebt(g)) {
            System.out.println("[" + g.getId() + "] " + t);
        }
        System.out.println("--------------------------------------\n");
    }

    private static void printSimplified(Group g, SettlementService settlementService) {
        for (Transaction t : settlementService.simplifyDebt(g)) {
            System.out.println("[" + g.getId() + "][SIMPLIFIED] " + t);
        }
    }
}