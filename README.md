# 🛒 E-Commerce Order & Inventory Manager

A robust, console-based E-Commerce Order and Inventory Management System built with **Java**. This project demonstrates practical usage of the **Java Collections Framework**, explicit object ordering (`Comparable` & `Comparator`), state control via `Enums`, and safe concurrent modifications.

---

## 🌟 Key Features

- **Product & Category Management:**
  - Add, search, list, and remove products with unique ID guarantees.
  - Maintains a duplicate-free list of categories using `HashSet`.
  - Constant time $O(1)$ product retrieval using `HashMap`.

- **Dynamic Order Processing:**
  - Manages permanent order history without deleting past records.
  - Automatic recalculation of order totals whenever items are added or removed.
  - First-In-First-Out (FIFO) queue for shipping orders using `ArrayDeque`.
  - Sequential record of delivered orders using `LinkedHashMap`.

- **Object Sorting & Filtering:**
  - **Natural Ordering:** Products sorted by price ascending using Java `Comparable`.
  - **Custom Ordering:** Orders sorted by total value using a custom `Comparator`.
  - **Safe Collection Filtering:** Purges out-of-stock items safely during iteration via `removeIf()` predicate to avoid `ConcurrentModificationException`.

- **Product Reviews:**
  - Flat collection of product feedback without altering product classes.

---

## 🛠️ Technical Stack & Data Structures

| Data Structure / Feature | Usage in Project |
| :--- | :--- |
| `HashMap<Integer, Product>` | $O(1)$ fast lookup for products and orders by ID. |
| `HashSet<String>` | Stores distinct category names without duplicates. |
| `ArrayDeque<Order>` | Implements FIFO queue for orders waiting to ship. |
| `LinkedHashMap<Integer, Order>` | Preserves exact sequence of delivered orders. |
| `Comparable<Product>` | Built-in default sorting by product price. |
| `Comparator<Order>` | Custom external sorting for orders by total value. |
| `removeIf()` | Safe in-loop deletion for out-of-stock inventory. |

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/FCI-MoGaafar/E-Commerce.git](https://github.com/FCI-MoGaafar/E-Commerce.git)
