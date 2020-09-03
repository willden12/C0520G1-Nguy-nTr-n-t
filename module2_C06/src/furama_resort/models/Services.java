package furama_resort.models;

public abstract class Services {
    private String name;
    private String userArea;
    private String price;
    private String maxPeople;
    private String rentType;
    private String id;

    public Services() {
    }

    public Services(String name, String userArea, String price, String maxPeople, String rentType, String id) {
        this.name = name;
        this.userArea = userArea;
        this.price = price;
        this.maxPeople = maxPeople;
        this.rentType = rentType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Services{" +
                "name='" + name + '\'' +
                ", userArena='" + userArea + '\'' +
                ", price='" + price + '\'' +
                ", maxPeople='" + maxPeople + '\'' +
                ", rentType='" + rentType + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public abstract void showInform();

}
