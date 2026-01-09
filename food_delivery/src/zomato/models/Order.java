package zomato.models;

import zomato.dtos.OrderItem;
import zomato.enums.OrderPaymentStatus;
import zomato.enums.OrderStatus;
import zomato.enums.OrderType;
import zomato.enums.PaymentMethod;

import java.util.List;
import java.util.Map;

public class Order {
    private String id;
    private User user;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private String note;
    private OrderStatus orderStatus;
    private OrderPaymentStatus orderPaymentStatus;
    private Delivery delivery;
    private double grandTotal;
    private PaymentMethod paymentMethod;
    private OrderType orderType;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderPaymentStatus getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(OrderPaymentStatus orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
