package zomato.services;

import zomato.dtos.OrderItem;
import zomato.dtos.PlaceOrderDTO;
import zomato.enums.OrderType;
import zomato.models.MenuItem;
import zomato.models.Order;
import zomato.models.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService {
    private FoodMenuService foodMenuService;
    private PricingService pricingService;
    private CancelOrderService cancelOrderService;
    private Map<String , Order> orders;
    public Order creatOrder(User user , PlaceOrderDTO dto) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUser(user);
        for(OrderItem item : dto.getItems() ) {

            MenuItem menuItem = foodMenuService.getMenuItem(item.getItem().getId());
            if(menuItem == null) {
                throw new RuntimeException("Item not found");
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(menuItem);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setAddOns(item.getAddOns());

            order.getItems().add(orderItem);
        }
        order.setGrandTotal(pricingService.getBill(order, dto.getCouponCode()));
        orders.put(order.getId(), order);
        return order;
    }
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
    public void cancelOrder(String orderId ,String userId) {
        Order order = orders.get(orderId);
        if(order == null) {
            throw new RuntimeException("Order not found");
        }
        if(!order.getUser().getId().equals(userId))
        {
            throw new RuntimeException("Not allowed to cancel order");
        }
        cancelOrderService.cancelOrder(order);
    }
}
