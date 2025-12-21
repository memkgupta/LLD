package zomato.services;

import zomato.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantService {
    private final Map<String , Restaurant> restaurants;
    public RestaurantService() {
        restaurants = new HashMap<>();
    }
    public Restaurant getRestaurant(String id) {
        return restaurants.get(id);

    }
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurant.setId(restaurant.getId());
        restaurants.put(restaurant.getId(), restaurant);
        return restaurant;
    }
    public void removeRestaurant(String id) {
        restaurants.remove(id);
    }
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant old =  restaurants.get(restaurant.getId());
        if(old == null) {
            throw new RuntimeException("Restaurant with id " + restaurant.getId() + " does not exist");
        }
        restaurants.put(old.getId(), new Restaurant(old));
        return  old;

    }
}
