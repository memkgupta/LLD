package strategie;

import entities.Group;
import entities.Transaction;
import entities.User;

import java.util.List;
import java.util.Set;

public interface SplittingStrategy<T>{
    List<Transaction> split(Group group , User payee , double amount , T extra);
    List<Transaction> split(Group group , Set<User> participants, User payee , double amount , T extra);

}
