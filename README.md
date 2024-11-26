
# 🎬 Movie Booking System

## 🌟 Project Overview
The **Movie Booking System** is a feature-packed web application designed for users to explore movies, book tickets, and manage theaters, screens, and showtimes. With a focus on user experience, it offers secure login, role-based access, and a smooth movie booking process. Admins can manage all aspects of the system, from movies to bookings, while users can easily make reservations for their favorite movies.

---

## 🛠️ Technologies Used

### **Frontend:**
- **CSS**: Beautiful and responsive UI for an immersive experience.
- **JavaScript**: Enhances interactivity and dynamic content.
- **JSP (Java Server Pages)**: Renders dynamic content on the frontend.

### **Backend:**
- **Java Spring Boot**: Fast and robust backend framework for building web applications.
- **Spring Data JPA**: Efficient and easy-to-use ORM for database interactions.
- **Spring Security**: Protects endpoints and ensures secure login and user roles.

### **Database:**
- **MySQL**: Stores movie, theater, and user data in a relational database.

### **Build Tool:**
- **Maven**: Manages dependencies and builds the application efficiently.

### **Code Editor & Tools:**
- **Eclipse IDE**: Java IDE for seamless development and debugging.

### **Server:**
- **Apache Tomcat**: A reliable Java-based server for deploying the application.

---

## 💡 Features

### 1. **User Authentication and Authorization**
- **User Registration & Login**: Users can securely register and log in to the system.
- **Role-Based Access Control (RBAC)**: Admins and users have specific roles with permissions tailored to their needs.
- **Secure APIs**: APIs are protected with **Spring Security** to ensure secure access.

### 2. **Movie Management**
- **CRUD Operations**: Admins can **add, view, update, and delete** movies in the system.
- **Movie Search & Filter**: Users can easily **search movies** by title, genre, and language. 
- **Movies by Language**: Admins can filter movies by language for better navigation.

### 3. **Theater Management**
- **Manage Theaters**: Admins can **add, view, update, and delete** theater details.
- **Screen Management**: Admins can **add screens** to theaters and **list available screens** with their respective details.
- **Theater Locations**: Displays theater locations for easy booking.

### 4. **Showtime Management**
- **Manage Showtimes**: Admins can **add, update, and delete** showtimes for each movie.
- **Movie-Specific Showtimes**: Showtime data is linked to individual movies and screens.
- **Dynamic Scheduling**: Showtimes can be dynamically adjusted as per demand.

### 5. **Booking Management**
- **Create and Manage Bookings**: Users can **create, update, delete, and view** their bookings.
- **Booking Notifications**: Users receive notifications on booking confirmations, reminders, and cancellations.
- **Booking History**: Users can view past bookings with easy navigation.

### 6. **Payment Integration** (New Feature)
- **Secure Payment Gateway**: Integrated with a payment gateway for secure online payments.
- **Booking Confirmation**: Users receive a confirmation email with booking and payment details.
- **Booking Invoices**: Admins can view and generate invoices for completed bookings.

### 7. **Movie Rating and Reviews** (New Feature)
- **User Ratings**: Users can rate movies based on their experience.
- **Movie Reviews**: Users can write reviews and share their thoughts about movies.
- **Admin Moderation**: Admins can moderate reviews to ensure content quality.

---

## 🛠️ Setup Instructions

### 📋 Prerequisites
To get started, you’ll need the following:
- **JDK 11 or above**
- **MySQL Database**
- **Maven**
- **Eclipse IDE** (or any preferred Java IDE)
- **Apache Tomcat** (for running the application)

### 🚀 Steps to Run the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/SouravBanerje/movieSystems.git
   cd movieSystems
