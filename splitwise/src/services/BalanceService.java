package services;

import entities.Group;
import entities.Transaction;
import entities.User;

import java.util.HashMap;
import java.util.List;

public class BalanceService {
    public synchronized void updateBalance(Group group, User from, User to, double amount) {


        group.updateBalance(from, to, amount);
    }


}
