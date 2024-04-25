# Student Sphere - Multi-User Client-Server System

**Student Sphere** is a Java-based multi-user client-server application designed to manage student information within a college. It facilitates the administration, organization, and retrieval of student data through a user-friendly console interface. The Student Sphere application manages student information within a college through a central MySQL database. 

## Database Schema

The database schema consists of the following interrelated entities:

### 1. Departments

- Represents academic departments within the college.
- Attributes:
    - `department_id (INT)`: Unique identifier for the department (auto-incremented primary key).
    - `department_name (VARCHAR(50))`: Name of the department (e.g., "Computing", "Creative Media").

### 2. Courses

- Represents academic courses offered by the college.
- Attributes:
    - `course_id (INT)`: Unique identifier for the course (auto-incremented primary key).
    - `course_name (VARCHAR(255))`: Name of the course (e.g., "BSc (Hons) in Computing in Software Development").
    - `course_code (VARCHAR(20))`: Unique code for the course (e.g., "SD").
    - `department_id (INT)`: Foreign key referencing the department_id of the department offering the course.
    - `credits (INT)`: Total number of credits associated with the course.
    - `level (VARCHAR(50))`: Level of the course (e.g., "Bachelors", "Postgraduate Diploma").

### 3. Modules

- Represents individual modules that make up courses.
- Attributes:
    - `module_id (INT)`: Unique identifier for the module (auto-incremented primary key).
    - `module_name (VARCHAR(255))`: Name of the module (e.g., "Introduction to Programming").
    - `credits (INT)`: Number of credits associated with the module.

### 4. CourseModules (Many-to-Many Relationship)

- This table establishes a many-to-many relationship between Courses and Modules.
- A course can consist of multiple modules, and a module can be included in multiple courses.
- Attributes:
    - `course_module_id (INT)`: Unique identifier for the relationship (auto-incremented primary key).
    - `course_id (INT)`: Foreign key referencing the course_id of the associated course.
    - `module_id (INT)`: Foreign key referencing the module_id of the associated module.

### 5. Students

- Represents students enrolled in the college.
- Attributes:
    - `id (INT)`: Unique identifier for the student (auto-incremented primary key).
    - `first_name (VARCHAR(50))`: Student's first name.
    - `last_name (VARCHAR(50))`: Student's last name.
    - `birth_date (DATE)`: Student's date of birth.
    - `student_email (VARCHAR(100))`: Student's email address.
    - `student_phone (VARCHAR(20))`: Student's phone number.
    - `address (VARCHAR(255))`: Student's address.
    - `graduation_year (INT)`: Year the student is expected to graduate.
    - `has_paid_full_fee (BOOLEAN)`: Indicates whether the student has paid the full fees.
    - `current_gpa (DECIMAL(3,2))`: Student's current GPA (Grade Point Average).
    - `course_id (INT)`: Foreign key referencing the course_id of the course the student is enrolled in.

## Features

- **Database Access:**
    - Stores information about students, departments, courses, and modules in a centralized MySQL database.
    - Provides a Data Access Layer (DAL) for interacting with the database using DTOs (Data Transfer Objects) and DAOs (Data Access Objects).
    - Uses DTOs (Data Transfer Objects) to efficiently move data between application and DAL.
    - Uses DAOs (Data Access Objects) to implement CRUD operations (Create, Read, Update, Delete) for each entity.
- **Client-Server Communication:**
    - Utilizes a multithreaded server to handle multiple client connections.
    - Implements a communication protocol for exchanging data between client and server using JSON format.
    - Supports serialization and deserialization of data using GSON Parser.
- **Client Features:**
    - Offers a menu-driven interface for users to interact with the application.
    - Allows users to perform CRUD operations (Create, Read, Update, Delete) on entities stored in the database.
    - Provides functionalities for displaying entities, adding new entities, deleting entities, retrieving image filenames, and downloading image files from the server.
- **Additional Features:**
    - Incorporates unit tests using JUnit to ensure code quality.
    - Employs design patterns like Factory, Singleton, Command, Decorator, and adheres to DRY principles.
    - Utilizes relevant collection classes for efficient data management.

## Technologies Used

- Java
- MySQL
- GSON Parser (for JSON serialization/deserialization)
- JUnit (for unit testing)
- Git (for version control)

## Getting Started

1. Clone this repository.
2. Set up a MySQL database server.
3. Configure database connection details in the application code.
4. Build and run the server application.
5. Build and run the client application.
