# Ecommerce_Backend
Backend README – Employee Management System
Employee Management System - Backend

Spring Boot backend for an Employee Management System with REST APIs. Uses Oracle SQL/PLSQL for database and Spring Security for authentication.

Table of Contents

Features

Technologies Used

Setup

Running the Backend

API Endpoints

Usage

Contributing

License

Features

Authentication: Sign up and login system with Spring Security.

Employee CRUD: Add, edit, update, delete employees.

Role & Department CRUD: Manage roles and departments.

Employee Reviews: Add/edit/delete reviews.

Promotions: Update roles/departments for promoted employees.

Audit Logs: Track all changes in employees and reviews.

Technologies Used

Spring Boot 3.3.4 – REST APIs

Maven – Dependency and build management

Spring Security – Authentication & authorization

Oracle SQL / PLSQL – Database backend

Java 17 – Language version

Setup
Clone the Repository:
git clone https://github.com/your-username/EmployeeManagement_Backend
cd EmployeeManagement_Backend

Database Setup:

Open Oracle SQL Developer.

Run EmployeeManagement.sql (or provided SQL scripts) to create tables and sequences.

Update src/main/resources/application.properties with your Oracle DB credentials:

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update

Build and Run
mvn clean install
mvn spring-boot:run


Backend runs at http://localhost:8080.

API Endpoints

/auth/signup – Register user

/auth/login – Login user

/employees – CRUD operations for employees

/roles – CRUD operations for roles

/departments – CRUD operations for departments

/reviews – CRUD operations for employee reviews

/promote – Promote employees

Usage

Start backend server.

Ensure Oracle database is running.

Connect frontend to backend to perform all operations.

Contributing

Pull requests and issues are welcome.

Run tests before submitting.

License

MIT License

cd src/main/resources/Wallet_EmpMgmtApp
