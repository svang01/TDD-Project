Spring Boot Order Management System
- This is a simple web application developed using Test Driven Development (TDD) principles. It allows users to handle basic functionalities related to order handling using a H2 Database.

Setting up the Project
- Clone the repo
- Open your project in your IDE


Running the Project
- Click and Run the "ProjectApplication" java class file.
- The application will be accessible at http://localhost:8080/order/all
- Here you will see all the orders if they existed.

Testing the Project:
- The Order Management System follows the principles of Test Driven Development (TDD), ensuring the application is thoroughly tested. To execute the test, follow these steps:
  - Open the project
  - Navigate to the test directory (src/test/java) and locate the test classes.
  - Right-click on a test class and select "Run" to execute the tests.
  - The test results will be displayed in the IDE's test runner.

H2 Database
- This project utilizes an H2 web-based console, which allows you to interact with the database, execute queries, and perform tasks using a web browser.
- To use this database:
  - Run the "ProjectApplication" java class
  - Open the web browser to http://localhost:8080/h2-console
  - Make sure the JDBC URL matches the one on the "application.properties" file, 
  - test connection (should say test successful) and press "connect"
  - Once you are connected to the H2 Database, you can interact with the database by executing SQL Statements:
  - Press the "run" button after each statement to get desired outcome.
  

- Create Table:

  -     CREATE TABLE orders (
        id INT PRIMARY KEY AUTO_INCREMENT,
        customer_name VARCHAR(255) NOT NULL,
        order_date DATE NOT NULL,
        shipping_address VARCHAR(255) NOT NULL,
        total DECIMAL(10, 2) NOT NULL
        );
    
- Inserting Data:

  -     INSERT INTO orders (customer_name, order_date, shipping_address, total)
          VALUES ('Billy Joel', '2021-07-01', '1 Morehead Street', 20.00) 

- Retrieving Data:
  -     SELECT * FROM orders;

- Updating Data:
  -     UPDATE orders SET customer_name = 'Billy Bob' WHERE id = 1;

- Delete Data:
  -     DELETE FROM orders WHERE id = 1;

API
 - All Methods can be tested individually in the ProjectApplication java class

Go to the controller class.
Test any method (Get, Create, Update, Delete) individually by going to the annotation for each method and "generate request in HTTP Client"
Based on which method is being tested, the correct method (GET, POST, PUT, DELETE) along with the URL will generate.
Make changes or modification accordingly. Click on the green play button and any changes will populate on the local server http://localhost:8080/order/all.

- Sample template to post:
  
        POST http://localhost:8080/order/create
        Content-Type: application/json 
        {
        "customerName": "Test Name",
        "orderDate": "2023-07-06",
        "shippingAddress": "1 Charlotte Drive",
        "total": 1000.0
        }


