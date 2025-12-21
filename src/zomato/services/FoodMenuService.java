package zomato.services;

import zomato.models.MenuItem;
import zomato.models.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FoodMenuService {

    private Map<String , MenuItem> menuItems;
    public FoodMenuService() {
        menuItems = new HashMap<>();
    }
    public List<MenuItem> getMenuItems(String restaurantId)
    {

        return menuItems.values().stream().filter(
                item->{
                    return item.getRestaurantId().equals(restaurantId);
                }

        ).toList();
    }
    public MenuItem getMenuItem(String menuItemId)
    {
        return menuItems.get(menuItemId);
    }
    public void addMenuItem(String restaurantId , MenuItem menuItem)
    {
        String id = UUID.randomUUID().toString();
        menuItem.setId(id);
        menuItem.setRestaurantId(restaurantId);
        menuItems.put(id,menuItem);
    }
    public MenuItem updateMenuItem(String menuItemId , MenuItem nItem)
    {
        MenuItem old =  menuItems.get(menuItemId);
        if(old == null)
        {
            throw new RuntimeException(menuItemId + " not found");
        }
        old.setName(nItem.getName());
        old.setPrice(nItem.getPrice());
        old.setCategory(nItem.getCategory());
        old.setTax(nItem.getTax());
        menuItems.put(menuItemId,old);
        return old;
    }
    public void removeMenuItem(String menuItemId)
    {   if(!menuItems.containsKey(menuItemId)) throw new RuntimeException(menuItemId + " not found");
        menuItems.remove(menuItemId);
    }
}
