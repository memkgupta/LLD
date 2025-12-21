package zomato.services;

import zomato.models.DishAddOn;
import zomato.utils.CompositeKey;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import zomato.utils.CompositeKey;

public class DishAddOnService {

    private final Map<CompositeKey<String, Integer>, DishAddOn> addOns;

    public DishAddOnService() {
        this.addOns = new HashMap<>();
    }


    public void addAddOn(String dishId, Integer addOnId, DishAddOn dishAddOn) {
        CompositeKey<String, Integer> key =
                new CompositeKey<>(dishId, addOnId);
        addOns.put(key, dishAddOn);
    }


    public DishAddOn getAddOn(String dishId, Integer addOnId) {
        CompositeKey<String, Integer> key =
                new CompositeKey<>(dishId, addOnId);
        return addOns.get(key);
    }


    public List<DishAddOn> getAddOnsForDish(String dishId) {
        List<DishAddOn> result = new ArrayList<>();

        for (Map.Entry<CompositeKey<String, Integer>, DishAddOn> entry : addOns.entrySet()) {
            if (entry.getKey().getKey1().equals(dishId)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }


    public void removeAddOn(String dishId, Integer addOnId) {
        CompositeKey<String, Integer> key =
                new CompositeKey<>(dishId, addOnId);
        if(!addOns.containsKey(key)) {
            throw new RuntimeException("No such DishAddOn");
        }
        addOns.remove(key);
    }
}
