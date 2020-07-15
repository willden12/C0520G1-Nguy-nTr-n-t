package java_collection_framework.baitap;

import java.util.Comparator;

public class SortProduct_1 extends Product implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o2.getPrice()-o1.getPrice());
    }
}
