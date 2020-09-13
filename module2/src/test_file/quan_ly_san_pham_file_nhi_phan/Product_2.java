package test_file.quan_ly_san_pham_file_nhi_phan;

import java.io.Serializable;

public class Product_2 implements Serializable {
    private int id;
    private String name;
    private String brand;
    private double price;
    public Product_2(int id, String name, String brand, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }
    public Product_2(){
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product: [ " +
                "id=" + id +
                " name= " + name +
                " brand= " + brand +
                " price=" + price +
                " ]";
    }
}
