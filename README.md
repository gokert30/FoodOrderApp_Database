# 🍔 Food Order App

This project includes a Java-based Graphical User Interface (GUI) application and an SQL database schema. It simulates an online food ordering system.

## 📁 Project Contents

- **`FoodOrderAppGUI.java`**  
  A desktop application built using Java Swing that allows users to place orders at restaurants through a simple GUI.

- **`food_order_schema.sql`**  
  An SQL schema that creates all necessary tables (`Districts`, `Restaurants`, `Customers`, `Orders`, `Carriers`), populates them with sample data, and includes various SQL operations such as INSERT, UPDATE, DELETE, and SELECT.

## 🧱 Database Structure

- **Districts:** Stores district information.
- **Restaurants:** Contains restaurant details and their associated district.
- **Customers:** Stores customer information.
- **Orders:** Holds order records.
- **Carriers:** Contains delivery staff information.

## ⚙️ Setup and Usage

1. **Setting Up the Database:**
   - Execute the `food_order_schema.sql` file using a SQL client (e.g., MySQL Workbench) to create the database and tables.
   - **Note:** The file ends with `DROP DATABASE Online_Food_Order;`. You may want to remove or skip this line to keep your database.

2. **Running the Application:**
   - Open `FoodOrderAppGUI.java` in a Java IDE (e.g., IntelliJ IDEA, NetBeans).
   - Compile and run the application.

## 📝 Notes

- The SQL file contains sample CRUD operations.
- The GUI does not currently connect to the database. You can extend the project to add JDBC support if desired.

## 📌 Requirements

- Java 8+
- MySQL or another compatible SQL database
- (Optional) JDBC for database connectivity
