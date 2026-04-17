# Training Reservations Platform

A full-stack gym management and training reservation system designed for real-world operations.  
The platform streamlines scheduling, reservation, and on-site attendance verification through QR codes.

## Overview

This application supports three user roles (Admin, Employee, and Gym Member) and provides:
- Secure authentication and role-based authorization
- Training and schedule management
- Online reservation workflow
- QR-based attendance confirmation using a device camera
- Account verification and gym contact via email

## Key Functionalities

### Authentication and Accounts
- User registration with email verification
- Login and logout
- Role-based access control for protected operations

### Training Management
- Create, update, and manage trainings and training-day schedules
- View upcoming trainings by role
- Track training reservations per session

### QR Attendance Verification
- Each gym member has a unique QR code in their profile
- At check-in, an employee scans the member QR code using the camera
- The system confirms arrival and records attendance for the selected training

### Communication
- Contact form for gym members
- Email notifications for registration confirmation and communication flows

## User Roles and Permissions

### Admin
- Full visibility over all trainings
- CRUD operations for trainings and schedules
- Visibility over all users
- Can unblock users automatically blocked after repeated no-shows

### Employee
- Access to upcoming trainings
- Confirms member attendance by scanning QR codes with a camera

### Gym Member
- Access to upcoming trainings
- Personal profile with unique QR code
- Training reservation capability
- Contact gym administration via email

## Tech Stack

- Backend: Spring Boot (`diplomski-back`)
- Frontend: Angular (`diplomski-front`)
- Database: MySQL
- Auth: JWT-based authentication and authorization

## Project Structure

- `diplomski-back` - Spring Boot REST API, business logic, persistence, security
- `diplomski-front` - Angular client application
