# Student Management Console Application (Java + MySQL)

A Java console-based CRUD application for managing student records using MySQL database and JDBC.

## Features
- Add Student
- View All Students
- Search Student by ID
- Update Student
- Delete Student

## Tech Stack
- Java
- JDBC
- MySQL
- IntelliJ IDEA

## Database Structure

```sql
CREATE TABLE students (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  age INT
);
