# Movie Booking System

## Project Overview
The Movie Booking System is a web application that allows users to book movie tickets, manage movies, theaters, and showtimes. The system provides features such as user authentication, movie and theater management, and booking management, with an admin interface for managing content.

## Technologies Used
- **Frontend**:  
  - CSS  
  - JavaScript  
  - JSP (Java Server Pages)

- **Backend**:  
  - Java Spring Boot  
  - Spring Data JPA  
  - Spring Security

- **Database**:  
  - MySQL

- **Build Tool**:  
  - Maven

- **Code Editor and Tools**:  
  - Eclipse IDE

- **Server**:  
  - Apache Tomcat

## Features

### 1. User Authentication and Authorization
- Implemented user registration and login functionality.
- Integrated role-based access control (RBAC) to manage different levels of access (admin, user).
- Secured APIs using **Spring Security** to protect sensitive endpoints.

### 2. Movie Management
- Admin users can **add, view, update, and delete** movies in the system.
- Implemented a feature to **filter movies by language**.

### 3. Theater Management
- Admin users can **add, view, update, and delete** theater details.
- Implemented functionality to **add screens** and **list available screens** in a theater.

### 4. Showtime Management
- Admin users can **add, view, update, and delete** showtimes for each movie.
- Integrated showtime data with movie schedules and theater details.

### 5. Booking Management
- Users can **create, update, delete, and view** bookings.
- Integrated booking details with available showtimes, theaters, and user information.



## Setup Instructions

### Prerequisites
- JDK 11 or above
- MySQL Database
- Maven
- Eclipse IDE (or any other Java IDE)
- Apache Tomcat (for running the application)

### Steps to Run the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/SouravBanerje/movieSystems.git
   cd movieSystems
