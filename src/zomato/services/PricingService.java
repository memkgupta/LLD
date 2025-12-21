package zomato.services;

import zomato.dtos.OrderItem;
import zomato.models.DishAddOn;
import zomato.models.MenuItem;
import zomato.models.Order;

import java.util.Map;

public class PricingService {
    public PricingService() {

    }
    private double calcTax(int percentage , double originalPrice , int qty)
    {
        double taxPrice = (percentage * originalPrice)/100;
        return taxPrice*qty;
    }
    public double getBill(Order order,String couponCode)
    {
        double price = 0;
        for(OrderItem orderItem : order.getItems() )
        {
            MenuItem key = orderItem.getItem();
            int qty = orderItem.getQuantity();
            double itemPrice = key.getPrice();
            int tax = key.getTax();
            for(DishAddOn addOn : orderItem.getAddOns())
            {
                itemPrice+=addOn.getPrice();
            }
            double finalPrice = itemPrice*qty + calcTax(tax,itemPrice,qty);
            price+=finalPrice;
        }
        return price;
    }

}
