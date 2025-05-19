CREATE DATABASE Online_Food_Order;
USE Online_Food_Order;


CREATE TABLE Districts (
    district_id INT PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    Restaurants_number INT
);

INSERT INTO Districts (district_id, name, Restaurants_number) 
VALUES 
(1, 'District 1', 6),
(2, 'District 2', 4),
(3, 'District 3', 11),
(4, 'District 4', 9),
(5, 'District 5', 6);


CREATE TABLE Restaurants (
    R_id INT PRIMARY KEY,
    Manager VARCHAR(50) NOT NULL,
    Location VARCHAR(100) NOT NULL,
    R_name VARCHAR(50) NOT NULL,
    Districts_district_no INT NOT NULL,
    FOREIGN KEY (Districts_district_no) REFERENCES Districts(district_id));

INSERT INTO Restaurants (R_id, Manager, Location, R_name, Districts_district_no) 
VALUES 
(101, 'Dan Brown', '3 Sunny St.', 'Kebab World', 3),
(102, 'Elisa Bracken', '948 Point St.', 'Pizza House', 2),
(103, 'Jack Train', '53 Book St.', 'Street Food', 5),
(104, 'Victoria Hans', '46 House St.', 'Home Made', 1),
(105, 'Kay Glass', '156 Water St.', 'Foody Moody', 4);




INSERT INTO Restaurants (R_id, Manager, Location, R_name, Districts_district_no) 
VALUES (106, 'Mark Taylor', '78 River St.', 'River Side', 2);

UPDATE Restaurants
SET Manager = 'Michael Brown'
WHERE R_id = 103;

DELETE FROM Restaurants
WHERE R_id = 106;

SELECT * FROM Restaurants
ORDER BY R_name;

SELECT Districts_district_no, COUNT(R_id) AS RestaurantCount
FROM Restaurants
GROUP BY Districts_district_no;

CREATE TABLE Customers (
    Cst_name VARCHAR(50) NOT NULL,
    CustomerAddress VARCHAR(100) NOT NULL,
    T_Number VARCHAR(11) NOT NULL UNIQUE,
    PRIMARY KEY (T_Number));
    

INSERT INTO Customers (Cst_name, CustomerAddress, T_Number) 
VALUES 
('Elizabeth Young', '123 Main St.', '05551234567'),
('Sophie Taylor', '456 Elm St.','05559874516'),
('Adam Smith', '789 Oak St.','05558523614'),
('Yasmin Sunny', '321 Pine St.','05557845213'),
('George Snow', '842 Map St.','05558459137');

INSERT INTO Customers (Cst_name, CustomerAddress, T_Number) 
VALUES ('Laura White', '100 High St.','05551234999');

DELETE FROM Customers
WHERE T_Number = '05551234999';



CREATE TABLE Orders (
    order_id INT PRIMARY KEY NOT NULL UNIQUE,
    customer_id VARCHAR(11) NOT NULL,
    restaurant_id INT NOT NULL,
    address VARCHAR(100) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(T_Number),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(R_id)
);

INSERT INTO Orders (order_id, address, restaurant_id, customer_id) 
VALUES 
(77, '123 Main St.', 101, '05551234567'),
(84, '456 Elm St.', 102, '05559874516'),
(19, '789 Oak St.', 103, '05558523614'),
(60, '321 Pine St.', 104, '05557845213'),
(73, '842 Map St.', 105, '05558459137');



DELETE FROM Orders
WHERE order_id = 19;


SELECT * FROM Orders
WHERE customer_id = '05551234567';




CREATE TABLE Carriers (
    SSN VARCHAR(9) NOT NULL UNIQUE PRIMARY KEY,
    Salary DECIMAL(10, 2),
    C_Name VARCHAR(50) NOT NULL,
    Sex CHAR(1),
    Restaurants_R_id INT,
    Restaurants_Districts_district_no INT,

    FOREIGN KEY (Restaurants_R_id) REFERENCES Restaurants(R_id)
);

INSERT INTO Carriers (SSN, Salary, C_Name, Sex, Restaurants_R_id, Restaurants_Districts_district_no) 
VALUES 
('123456789', 5000, 'John Doe', 'M', 101, 3),
('987654321', 6000, 'Jane Doe', 'F', 102, 2),
('456789123', 5500, 'Alice Smith', 'F', 103, 5),
('654321987', 4800, 'Bob Brown', 'M', 104, 1),
('789123456', 5200, 'Emily Johnson', 'F', 105, 4);

UPDATE Carriers
SET Salary = 5700
WHERE SSN = '456789123';

SELECT * FROM Carriers
ORDER BY Salary DESC;

SELECT Sex, AVG(Salary) AS AverageSalary
FROM Carriers
GROUP BY Sex;

SELECT SUM(Salary) AS TotalSalary
FROM Carriers;

SELECT ca.SSN, ca.C_Name, ca.Salary, r.R_name
FROM Carriers ca
JOIN Restaurants r ON ca.Restaurants_R_id = r.R_id;

DROP database Online_Food_Order;