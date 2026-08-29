package org.example;

import java.util.Comparator;

public class OrderTotalComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getTotalPrice(),  o2.getTotalPrice());
    }
}
