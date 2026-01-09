package zomato.dtos;

public class Address {
    private String house_no;
    private String street;
    private String city;
    private String state;

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static Address builder()
    {
        return new Address();
    }
    public Address  build()
    {
        return this;
    }
    public Address city(String city)
    {
        this.city = city;
        return this;
    }
    public Address state(String state)
    {
        this.state = state;
        return this;
        }
    public Address house_no(String house_no)
    {
        this.house_no = house_no;
        return this;
    }
    public Address street(String street)
    {
        this.street = street;
        return this;
    }
}
