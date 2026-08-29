package org.example;

import java.util.*;

public class Store {
    private ArrayList<Product> products=new ArrayList<>();
    private HashMap<Integer,Product> productsMap=new HashMap<>();
    private HashMap<Integer,Order> orders=new  HashMap<>();
    private HashSet<String> categories=new HashSet<>();
    private Queue<Order> orderShipped=new ArrayDeque<>();
    private LinkedHashMap<Integer,Order> orderDelivered=new LinkedHashMap<>();
    private ArrayList<Review> reviews=new  ArrayList<>();

    public boolean addProduct(int id,String name,double price,int quantity,String category){
        if(name==null||name.isBlank()||category==null||category.isBlank()){
            return false;
        }
        if (id<=0||price<=0||quantity<0){
            return false;
        }
        if(productsMap.containsKey(id)){
            System.out.println("Product Id already exists");
            return false;
        }
        for (Product existingProduct : productsMap.values()) {
            if (existingProduct.getName().trim().equalsIgnoreCase(name.trim())) {
                System.out.println("Product Name already exists!");
                return false;
            }
        }
        Product p=new Product(id,name,price,quantity,category);
        productsMap.put(id,p);
        products.add(p);
        categories.add(category.toUpperCase());
        System.out.println("Product added successfully");
        return true;

    }

    public boolean removeProduct(int id){
        if(products==null||products.isEmpty()){
            System.out.println("No Products Available");
            return false;
        }
        if(!productsMap.containsKey(id)){
            return false;
        }
        Product productRemove=productsMap.get(id);
        products.remove(productRemove);
        productsMap.remove(id);
        System.out.println("Removed product with id : "+id);
        boolean flag=true;
        for (Product p:products ){
            if(p.getCategory().equalsIgnoreCase(productRemove.getCategory() )){
                flag=false;
                break;
            }
        }
        if(flag){
            categories.remove(productRemove.getCategory());
        }
        return true;
    }

    public boolean increaseProductStock(int productId, int additionalQuantity) {
        if(products==null||products.isEmpty()){
            System.out.println("No Products Available");
            return false;
        }
        if (productId <= 0 || additionalQuantity <= 0) {
            System.out.println("Invalid product ID or quantity!");
            return false;
        }

        if (!productsMap.containsKey(productId)) {
            System.out.println("Product not found in store!");
            return false;
        }

        Product product = productsMap.get(productId);
        int newQuantity = product.getQuantity() + additionalQuantity;
        product.setQuantity(newQuantity);

        System.out.println("Stock updated successfully! New quantity for '"
                + product.getName() + " is: " + newQuantity);
        return true;
    }

    public void displayAllProducts(){
        System.out.println("****************************************");
        System.out.println("               All Products             ");
        System.out.println("****************************************");
        for(Product p:products){
            System.out.println(p);
        }
        System.out.println("****************************************");
    }

    public void searchProductByID(int id){
        if(products==null||products.isEmpty()){
            System.out.println("No Products Available");
            return;
        }
        if (productsMap.get(id) == null){
            System.out.println("Product not found in store!");
            return;
        }
        System.out.println("Product found in store!");
        System.out.println(productsMap.get(id));
    }
     public void showAllCategories(){
        if(categories.isEmpty() || categories==null){
            System.out.println("No categories available");
            return;
        }
        System.out.println("****************************************");
        System.out.println("              All Categories            ");
        System.out.println("****************************************");
        for (String c:categories){
            System.out.println("- "+c);
        }
        System.out.println("****************************************");
     }

     public void displayProductsOrderedByPrice(){
        if (products==null||products.isEmpty()){
            System.out.println("No Products available");
            return;
        }
        ArrayList<Product> productsOrderedByPrice=new ArrayList<>(products);
        Collections.sort(productsOrderedByPrice);
         System.out.println("****************************************");
         System.out.println("        Products Ordered By Price       ");
         System.out.println("****************************************");
         for(Product p:productsOrderedByPrice){
             System.out.println(p);
         }
         System.out.println("****************************************");
     }

     public boolean createOrder(int id,String orderName){
        if (id<=0||orderName==null||orderName.isBlank()){
            return false;
        }
        if (orders.containsKey(id)){
            System.out.println("Order already exists");
            return false;
        }
        Order order=new Order(id,orderName);
        orders.put(id,order);
        System.out.println("Order created");
        return true;
     }

     public boolean addItemToOrder(int orderId,int itemId,int quantity){
        if(orders==null||orders.isEmpty()){
            return false;
        }
        if (!orders.containsKey(orderId)){
            System.out.println("Order not found");
            return false;
        }
        if(!productsMap.containsKey(itemId)||quantity<=0){
            System.out.println("Product not found or quantity less than 0");
            return false;
        }
        Order order=orders.get(orderId);
        if(order.increaseQuantity(itemId,quantity)){
            System.out.println("the Item already exists in order so increase quantity successfully");
            return true;
        }
        Product product=productsMap.get(itemId);
        CartItem cartItem=new CartItem(product,quantity);
        if (order.addItem(cartItem))
            System.out.println("Order added successfully");
        else
            System.out.println("Order could not be added");
        return true;

     }

    public void displayAllOrders(){
        System.out.println("****************************************");
        System.out.println("               All Order             ");
        System.out.println("****************************************");
        for(Order o:orders.values()){
            o.displayOrder();
        }
        System.out.println("****************************************");
    }

     public boolean removeItemFromOrder(int orderId,int itemId){
        if(orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return false;
        }
        if (!orders.containsKey(orderId)){
            System.out.println("Order not found");
            return false;
        }
        if(orders.get(orderId).getOrderStatus()!=OrderStatus.Pending){
            System.out.println("Cannot remove items. Order " + orderId + " is already " +orders.get(orderId).getOrderStatus());
            return false;
        }

        if (!orders.get(orderId).removeItem(itemId)){
            System.out.println("Item not found in order");
            return false;
        }
        System.out.println("Item removed successfully from order");
        return true;
     }

     public void displayOrder(int orderId){
        if (orders==null||!orders.containsKey(orderId)){
            System.out.println("Orders is empty");
            return;
        }
        orders.get(orderId).displayOrder();
    }

    public boolean addOrderToTheShipping(int orderId){
        if(orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return false;
        }
        if (!orders.containsKey(orderId)){
            System.out.println("Order not found");
            return false;
        }
        if(orders.get(orderId).getOrderStatus()!=OrderStatus.Pending){
            System.out.println("Cannot Add order to the Shipping list. Order " + orderId + " is already " +orders.get(orderId).getOrderStatus());
            return false;
        }
        if (orders.get(orderId).getCartItem() == null || orders.get(orderId).getCartItem().isEmpty()) {
            System.out.println("Cannot ship an empty order with no items!");
            return false;
        }
        if (orderShipped.contains(orders.get(orderId))) {
            System.out.println("Order is already in the shipping list!");
            return false;
        }
        orderShipped.add(orders.get(orderId));
        orders.get(orderId).updateOrderStatus(OrderStatus.Shipped);
        System.out.println("Order added successfully to Shipping list");
        return true;
    }

    public boolean shipNextOrder(){
        if(orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return false;
        }
        if(orderShipped==null||orderShipped.isEmpty()){
            System.out.println("No Orders Ship Available!");
            return false;
        }
        Order order=orderShipped.peek();
        if (orderDelivered.containsKey(order.getId())){
            System.out.println("Order is already in the delivery list!");
            orderShipped.poll();
            return false;
        }
        if (order.getCartItem() == null || order.getCartItem().isEmpty()) {
            System.out.println("Cannot ship an empty order with no items!");
            return false;
        }
        orderShipped.poll();
        order.updateOrderStatus(OrderStatus.Delivered);
        orderDelivered.put(order.getId(),order);
        System.out.println("Order ID " + order.getId() + " delivered successfully!");
        return true;
    }

    public boolean cancelOrder(int orderId){
        if (orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return false;
        }
        if (!orders.containsKey(orderId)){
            System.out.println("Order not found");
            return false;
        }
        Order order=orders.get(orderId);
        if (order.getOrderStatus()!=OrderStatus.Pending&&order.getOrderStatus()!=OrderStatus.Shipped){
            System.out.println("Cannot Add order to the Cancel list. Order " + orderId + " is already " +order.getOrderStatus());
            return false;
        }
        if(order.getCartItem()!=null){
            for (CartItem cartItem : order.getCartItem()) {
                Product p=cartItem.getProduct();
                if (p!=null)
                    p.setQuantity(p.getQuantity() + cartItem.getQuantity());
            }
        }
        if(order.getOrderStatus()==OrderStatus.Shipped){
            orderShipped.remove(order);
        }
        order.updateOrderStatus(OrderStatus.Cancelled);
        System.out.println("Order ID " + orderId + " cancelled successfully!");
        return true;
    }

    public void searchOrderByID(int id){
        if (orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return;
        }
        Order order=orders.get(id);
        if (order!=null){
            order.displayOrder();
        }
        else
            System.out.println("Order not found");
    }

    public boolean addReviewToProduct(int productId,String customerName,String comment){
        if (productsMap==null||productsMap.isEmpty()){
            System.out.println("No Products available");
            return false;
        }
        if (productsMap.get(productId) == null){
            System.out.println("Product not found");
            return false;
        }
        if (customerName==null||customerName.isBlank()||comment==null||comment.isBlank()){
            System.out.println("Customer name or comment is empty");
            return false;
        }
        Review review=new Review(productId,customerName,comment);
        reviews.add(review);
        return true;
    }
    public void showReviews(int productId){
        if (productsMap==null||productsMap.isEmpty()){
            System.out.println("No Products available");
            return;
        }
        if (productsMap.get(productId) == null||productsMap.containsKey(productId)){
            System.out.println("Product not found");
        }
        if (reviews==null||reviews.isEmpty()){
            System.out.println("No Reviews available");
            return;
        }
        boolean flag=true;
        for (Review review:reviews){
            if (review.getProductId()==productId){
                System.out.println(review);
                flag=false;
            }
        }
        if (flag){
            System.out.println("the product not have reviews");
        }
    }

    public boolean removeOutOfStockProducts(){
        if (productsMap==null||productsMap.isEmpty()){
            System.out.println("No Products available");
            return false;
        }
        int count=productsMap.size();
        productsMap.values().removeIf(r->r.getQuantity()<=0);
        if (products!=null)
            products.removeIf(r->r.getQuantity()<=0);
        if (count==productsMap.size()){
            System.out.println("No out-of-stock products found (quantity = 0)");
            return false;
        }
        System.out.println("Out-of-stock products removed successfully");
        return true;
    }

    public void displayOrdersByTotal(){
        if (orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return;
        }
        ArrayList<Order> sortedOrders=new ArrayList<>(orders.values());
        sortedOrders.sort(new OrderTotalComparator());
        System.out.println("=== Orders Sorted by Total Value ===");
        for (Order ord:sortedOrders){
            ord.displayOrder();
        }
    }
    public boolean decreaseQuantityFromOrder(int orderId,int productId,int quantity){
        if(orders==null||orders.isEmpty()){
            System.out.println("No Orders available");
            return false;
        }
        if (!orders.containsKey(orderId)){
            System.out.println("Order not found");
            return false;
        }
        Order order=orders.get(orderId);
        if(order.getOrderStatus()!=OrderStatus.Pending){
            System.out.println("Cannot modify order. Order " + orderId + " is already " + order.getOrderStatus());
            return false;
        }
        boolean flag=order.decreaseQuantity(productId,quantity);
        if (flag){
            System.out.println("Decrease quantity successfully");
            return true;
        }
        else {
            System.out.println("Product not found in order or invalid quantity");
            return false;
        }
    }

}
