package java_collection_framework.baitap;

import java.util.Comparator;

public class SortProduct extends Product implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice()-o2.getPrice());
    }
}
