package strategie;

import entities.Group;
import entities.Transaction;
import entities.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PercentageSplittingStrategy implements SplittingStrategy<Map<String,Double>>{
    @Override
    public List<Transaction> split(Group group, User payee, double amount, Map<String, Double> extra) {
        Set<User> members = group.getMembers();
        Double totalPercentage = extra.values().stream().mapToDouble(Double::doubleValue).sum();
        if(!totalPercentage.equals(100.0))
        {
            throw new IllegalArgumentException("strategie.PercentageSplittingStrategy: total percentage is " + totalPercentage);
        }

        return members.stream().filter(
                m->!m.equals(payee)
        ).map(m->new Transaction(m,payee,getAmount(amount,extra.get(m.getId())),group))
                .toList();
    }

    @Override
    public List<Transaction> split(Group group, Set<User> members, User payee, double amount, Map<String, Double> extra) {
        Double totalPercentage = extra.values().stream().mapToDouble(Double::doubleValue).sum();
        if(!totalPercentage.equals(100.0))
        {
            throw new IllegalArgumentException("strategie.PercentageSplittingStrategy: total percentage is " + totalPercentage);
        }

        return members.stream().filter(
                        m->!m.equals(payee)
                ).map(m->new Transaction(m,payee,getAmount(amount,extra.get(m.getId())),group))
                .toList();
    }

    private double getAmount(double amount , double percentage){
        return (amount * percentage)/100.0;
    }
}
