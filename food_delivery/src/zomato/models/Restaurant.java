package zomato.models;

public class Restaurant {
    private String name;
    private String address;
    private String id;

    public Restaurant(Restaurant old) {
        this.name = old.getName();
        this.address = old.getAddress();
        this.id = old.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
