package strategie;

import entities.Group;
import entities.Transaction;
import entities.User;

import java.util.List;
import java.util.Set;

public class EqualSplittingStrategy implements SplittingStrategy<Void>{
    @Override
    public List<Transaction> split(Group group, User payee, double amount, Void extra) {
        Set<User> members = group.getMembers();
        double splitAmount = amount/members.size();
        return members.stream().filter(m->!m.getId().equals(payee.getId()))
                .map(m->new Transaction(m,payee,splitAmount,group))
                .toList();

    }

    @Override
    public List<Transaction> split(Group group, Set<User> members, User payee, double amount, Void extra) {

        double splitAmount = amount/members.size();
        return members.stream().filter(m->!m.getId().equals(payee.getId()))
                .map(m->new Transaction(m,payee,splitAmount,group))
                .toList();
    }

}
