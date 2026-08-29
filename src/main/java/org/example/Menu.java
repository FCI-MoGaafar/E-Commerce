package org.example;

import java.util.Scanner;

public class Menu {
    private  Scanner input = new Scanner(System.in);
    private Store store=new Store();

    public Menu() {
        initializeDefaultMenu();
    }
    private void initializeDefaultMenu() {
        store.addProduct(1,"Wireless Mouse",250,15,"Electronics");
        store.addProduct(2,"Notebook",30,0,"Stationery");
        store.addProduct(3,"Desk Lamp",180,8,"Home");
        store.addProduct(4,"USB Cable",60,20,"Electronics");
        store.createOrder(501,"Sara");
        store.addItemToOrder(501,1,1);
        store.addItemToOrder(501,4,2);
    }

    private void displayOptions() {
        System.out.println("============= STORE MENU =============");
        System.out.println("1.  Add Product");
        System.out.println("2.  Remove Product");
        System.out.println("3.  Increase Product Stock");
        System.out.println("4.  Display All Products");
        System.out.println("5.  Search Product by ID");
        System.out.println("6.  Show All Categories");
        System.out.println("7.  Display Products Ordered by Price");
        System.out.println("8.  Create Order");
        System.out.println("9.  Add Item to Order");
        System.out.println("10. Decrease Item Quantity");
        System.out.println("11. Remove Item from Order");
        System.out.println("12. Display Order");
        System.out.println("13. Display All Orders");
        System.out.println("14. Add Order to the Shipping List");
        System.out.println("15. Ship Next Order");
        System.out.println("16. Cancel Order");
        System.out.println("17. Search Order by ID");
        System.out.println("18. Add Review to a Product");
        System.out.println("19. Show All Reviews for a Product");
        System.out.println("20. Remove Out-of-Stock Products");
        System.out.println("21. Display Orders Ordered by Total");
        System.out.println("22. Exit");
        System.out.println("======================================");
    }

    public void start() {
        System.out.println("=========================================");
        System.out.println("    Welcome to E-Commerce System Console ");
        System.out.println("=========================================");

        boolean exit = false;

        while (!exit) {
            displayOptions();
            System.out.print("Enter your choice (1-21): ");

            int choice = readIntInput();
            System.out.println("\n-----------------------------------------");

            switch (choice) {
                case 1:
                    handleAddProduct();
                    break;
                case 2:
                    handleRemoveProduct();
                    break;
                case 3:
                    handleIncreaseProductStock();
                    break;
                case 4:
                    store.displayAllProducts();
                    break;
                case 5:
                    handleSearchProductById();
                    break;
                case 6:
                    store.showAllCategories();
                    break;
                case 7:
                    store.displayProductsOrderedByPrice();
                    break;
                case 8:
                    handleCreateOrder();
                    break;
                case 9:
                    handleAddItemToOrder();
                    break;
                case 10:
                    handleDecreaseQuantityFromOrder();
                    break;
                case 11:
                    handleRemoveItemFromOrder();
                    break;
                case 12:
                    handleDisplayOrder();
                    break;
                case 13:
                    store.displayAllOrders();
                    break;
                case 14:
                    handleAddOrderToShippingList();
                    break;
                case 15:
                    store.shipNextOrder();
                    break;
                case 16:
                    handleCancelOrder();
                    break;
                case 17:
                    handleSearchOrderById();
                    break;
                case 18:
                    handleAddReview();
                    break;
                case 19:
                    handleShowReviews();
                    break;
                case 20:
                    store.removeOutOfStockProducts();
                    break;
                case 21:
                    store.displayOrdersByTotal();
                    break;
                case 22:
                    System.out.println("Thank you for using our Store System! Goodbye.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Choice! Please choose a number from 1 to 22.");
            }
            System.out.println("-----------------------------------------\n");
        }
    }
//-----------------------------------------------------------------------------------------------
    private int readIntInput() {
        while (true) {
           if (input.hasNextInt()) {
               int choice = input.nextInt();
               input.nextLine();
               return choice;
           }
           else {
               System.out.print("Invalid input! Please enter Number again : ");
               input.nextLine();
           }
        }
    }
    private double readDoubleInput() {
        while (true) {
            if (input.hasNextDouble()) {
                Double num = input.nextDouble();
                input.nextLine();
                return num;
            }
            else {
                System.out.print("Invalid input! Please enter again : ");
                input.nextLine();
            }
        }
    }
//-----------------------------------------------------------------------------------------------
private void handleAddProduct() {
    System.out.print("Enter Product ID: ");
    int productId = readIntInput();
    System.out.print("Enter Product Name: ");
    String name = input.nextLine().trim();
    System.out.print("Enter Product Price: ");
    double price = readDoubleInput();
    System.out.print("Enter Stock Quantity: ");
    int quantity = readIntInput();
    System.out.print("Enter Category Name: ");
    String category = input.nextLine().trim();
    
    store.addProduct(productId, name, price, quantity,category);
}

    private void handleRemoveProduct() {
        System.out.print("Enter Product ID to remove: ");
        int productId = readIntInput();
        store.removeProduct(productId);
    }

    private void handleIncreaseProductStock() {
        System.out.print("Enter Product ID to increase stock: ");
        int productId = readIntInput();
        System.out.print("Enter Quantity to Add to Stock: ");
        int quantity = readIntInput();

        store.increaseProductStock(productId, quantity);
    }

    private void handleSearchProductById() {
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();
        store.searchProductByID(productId);
    }

    private void handleCreateOrder() {
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();
        System.out.print("Enter Customer Name: ");
        String customerName = input.nextLine().trim();

        store.createOrder(orderId, customerName);
    }

    private void handleAddItemToOrder() {
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();
        System.out.print("Enter Quantity: ");
        int quantity = readIntInput();

        store.addItemToOrder(orderId, productId, quantity);
    }

    private void handleDecreaseQuantityFromOrder() {
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();
        System.out.print("Enter Quantity to Decrease: ");
        int quantity = readIntInput();

        store.decreaseQuantityFromOrder(orderId, productId, quantity);
    }

    private void handleRemoveItemFromOrder() {
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();

        store.removeItemFromOrder(orderId, productId);
    }

    private void handleDisplayOrder() {
        System.out.print("Enter Order ID to display: ");
        int orderId = readIntInput();
        store.searchOrderByID(orderId);
    }

    private void handleAddOrderToShippingList() {
        System.out.print("Enter Order ID to add to shipping queue: ");
        int orderId = readIntInput();
        store.addOrderToTheShipping(orderId);
    }

    private void handleCancelOrder() {
        System.out.print("Enter Order ID to Cancel: ");
        int orderId = readIntInput();
        store.cancelOrder(orderId);
    }

    private void handleSearchOrderById() {
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();
        store.searchOrderByID(orderId);
    }

    private void handleAddReview() {
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();
        System.out.print("Enter Your Name: ");
        String name = input.nextLine().trim();
        System.out.print("Enter Your Comment: ");
        String comment = input.nextLine().trim();

        store.addReviewToProduct(productId, name, comment);
    }

    private void handleShowReviews() {
        System.out.print("Enter Product ID: ");
        int productId = readIntInput();
        store.showReviews(productId);
    }

}
