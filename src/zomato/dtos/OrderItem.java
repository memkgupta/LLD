package zomato.dtos;

import zomato.models.DishAddOn;
import zomato.models.MenuItem;
import zomato.models.Order;

import java.util.List;

public class OrderItem {
    private MenuItem item;
    private int quantity;
    private List<DishAddOn> addOns;

    public static OrderItem builder()
    {
        return new OrderItem();
    }
    public OrderItem item(MenuItem item)
        {
        this.item = item;
        return this;
        }
    public OrderItem quantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }
    public OrderItem addOns(List<DishAddOn> addOns)
    {
        this.addOns = addOns;
        return this;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<DishAddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<DishAddOn> addOns) {
        this.addOns = addOns;
    }
}
