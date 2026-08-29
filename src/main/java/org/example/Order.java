package org.example;

import java.util.ArrayList;

public class Order {
    private int id;
    private String customerName;
    private ArrayList<CartItem> item;
    private double totalPrice;
    private OrderStatus orderStatus;

    public Order(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = 0;
        this.orderStatus=OrderStatus.Pending;
        this.item =new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<CartItem> getCartItem() {
        return item;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public boolean addItem(CartItem cartItem){
        if(cartItem==null||cartItem.getProduct()==null||cartItem.getQuantity() <= 0){
            return false;
        }
        Product product=cartItem.getProduct();
        int addQuantity=cartItem.getQuantity();
        if(product.getQuantity()<addQuantity){
            System.out.println("Product Quantity Exceeded");
            return false;
        }
        for (CartItem i:item){
            if(i.getProduct().getId()==product.getId()){
                i.setQuantity(addQuantity+i.getQuantity());
                calculateTotal();
                product.setQuantity(product.getQuantity()-addQuantity);
                return true;
            }
        }
        this.item.add(cartItem);
        calculateTotal();
        product.setQuantity(product.getQuantity()-addQuantity);
        return true;
    }
    public boolean removeItem(int id){
        if(item==null||item.isEmpty()){
            return false;
        }
        for (int i=0;i<item.size();i++){
            CartItem cartItem=item.get(i);
            Product product=cartItem.getProduct();
            if(product.getId()==id&&product!=null){
                product.setQuantity(product.getQuantity()+cartItem.getQuantity());
                item.remove(i);
                calculateTotal();
                return true;
            }
        }
        return false;
    }
    public boolean increaseQuantity(int id, int quantity){
        if(quantity<=0||id<=0){
            return false;
        }
        for (CartItem i:item){
            if(i.getProduct().getId()==id){
                Product product=i.getProduct();
                if (product.getQuantity()<quantity){
                    System.out.println("Product Quantity Exceeded");
                    return false;
                }
                i.setQuantity(i.getQuantity()+quantity);
                product.setQuantity(product.getQuantity()-quantity);
                calculateTotal();
                return true;
            }
        }
        return false;
    }
    public boolean decreaseQuantity(int id, int quantity){
        if(quantity<=0||id<=0){
            return false;
        }
        for (int j=0;j<item.size();j++){
            CartItem cartItem=item.get(j);
            Product product=cartItem.getProduct();
            if(product.getId()==id){
                if(cartItem.getQuantity()>quantity){
                    cartItem.setQuantity(cartItem.getQuantity()-quantity);
                    product.setQuantity(product.getQuantity()+quantity);
                }
                else {
                    product.setQuantity(product.getQuantity()+cartItem.getQuantity());
                    item.remove(j);
                }
                calculateTotal();
                return true;
            }
        }
        return false;
    }
    public double calculateTotal(){
        totalPrice=0;
        for(CartItem cartItem: item){
            this.totalPrice+=cartItem.calculateSubtotal();
        }
        return this.totalPrice;
    }

    public boolean updateOrderStatus(OrderStatus Status){
        if (Status==null)
            return false;
        this.orderStatus=Status;
        return true;
    }

    public void displayOrder(){
        System.out.println("==================================================");
        System.out.println("                   ORDER DETAILS                  ");
        System.out.println("==================================================");
        System.out.println("Order ID: " + id);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %-10s %-10s\n","item","Quantity","Subtotal");
        System.out.println("--------------------------------------------------");
        if(item.isEmpty()){
            System.out.println("               Order Items is empty           ");
        }
        else {
            for(CartItem orderItem : item){
                System.out.printf("%-20s %-10d %-10.2f\n",orderItem.getProduct().getName(),orderItem.getQuantity(),orderItem.calculateSubtotal());
            }
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Price: $%.2f\n",calculateTotal());
        System.out.println("--------------------------------------------------");
    }


}
