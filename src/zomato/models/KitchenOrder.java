package zomato.models;

import zomato.dtos.OrderItem;

import java.awt.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class KitchenOrder {
    private String orderId;
    private String id;
    private Map<MenuItem,Integer> items;
    private Integer prepTimeInMinutes;
    private String instructions;
    private Instant preparedAt;
    private Instant arrivedAt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public void setItems(Map<MenuItem, Integer> items) {
        this.items = items;
    }

    public Integer getPrepTimeInMinutes() {
        return prepTimeInMinutes;
    }

    public void setPrepTimeInMinutes(Integer prepTimeInMinutes) {
        this.prepTimeInMinutes = prepTimeInMinutes;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Instant getPreparedAt() {
        return preparedAt;
    }

    public void setPreparedAt(Instant preparedAt) {
        this.preparedAt = preparedAt;
    }

    public Instant getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(Instant arrivedAt) {
        this.arrivedAt = arrivedAt;
    }
}
