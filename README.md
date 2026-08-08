# NexusEnroll - Course Enrollment System

 Project Overview

NexusEnroll is a Java-based command-line interface (CLI) proof-of-concept for a university course enrollment system. It replaces the legacy system "LegacyEnroll" with a modern, modular architecture that demonstrates key software design patterns and principles.

 Team Members and Contributions

A.D.C. Madushan -- 24001236
Project Leader and Design Lead
Overall project coordination, architecture design oversight, design patterns integration, all parts handling and integration

G.D.T. Aberathna -- 24000027
Documentation and Compilation Lead
Introduction and Background, Problem Statement and Legacy System Analysis, Documentation of Team Work, Conclusion and Appendices, Final formatting and consistency check

R.A.N.S. Sathsara -- 24001867
Student Module and Factory Pattern Lead
Student Module features, Factory Method pattern, Student Class Diagram, Student Implementation and Test Cases

<<<<<<< HEAD
## Assignment
Software Architecture (SCS 2303) - Assignment 3
Deadline: 20 Aug
=======
S.W.A.H. Samarawickrama -- 24001813
Faculty Module and Facade Pattern Lead
Faculty Module features, Facade pattern (enrollment validation), Sequence Diagram, Faculty Implementation and Test Cases

H.M.M.A. Bandara -- 24000248
Admin Module, Observer and Principles Lead
Administrator Module, Observer pattern (notifications), Activity and State Diagrams, SOLID/DRY/KISS principles, Tools and Integration

K.G.S. Vishwajith -- 24002135
Team Member and Testing Support
Assisted with student module testing, helped debug enrollment validation logic, supported faculty grade submission testing, contributed to documentation review, and provided general testing assistance across all modules


 Architecture-- 3-Tier Architecture

The system follows a clean 3-Tier Architecture:


 PRESENTATION TIER (CLI - Student/Faculty/Admin Menus)     
 BUSINESS LOGIC TIER (Services - Enrollment, Faculty, Admin Services)   
 DATA TIER (Models - User, Student, Faculty, Course) 

Design Patterns Used

Factory Method  -Creational 
purpose-Creates different types of users (Student, Faculty, Admin) 
Strategy - Behavioral
purpose- Implements various validation strategies (Prerequisite, Capacity, Time Conflict) 
Observer - Behavioral 
purpose-Notifies advisors when grade changes occur 
State - Behavioral 
purpose-Manages grade lifecycle (Pending to Submitted) 

Software Design Principles Followed

SOLID Principles - Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
DRY (Don't Repeat Yourself) - Code reuse through abstraction
KISS (Keep It Simple, Stupid) - Simple, maintainable code

 Project Structure
NexusEnroll/
│
├── src/
│   └── nexusenroll/
│       │
│       ├── Main.java
│       │
│       ├── models/
│       │   ├── User.java
│       │   ├── Student.java
│       │   ├── Faculty.java
│       │   ├── Administrator.java
│       │   ├── Course.java
│       │   ├── Grade.java
│       │   ├── Program.java
│       │   └── CourseChangeRequest.java
│       │
│       ├── patterns/
│       │   ├── factory/
│       │   │   └── UserFactory.java
│       │   │
│       │   ├── strategy/
│       │   │   ├── IValidationStrategy.java
│       │   │   ├── PrerequisiteCheckStrategy.java
│       │   │   ├── CapacityCheckStrategy.java
│       │   │   └── TimeConflictCheckStrategy.java
│       │   │
│       │   ├── observer/
│       │   │   ├── IObserver.java
│       │   │   ├── NotificationSubject.java
│       │   │   └── Advisor.java
│       │   │
│       │   └── state/
│       │       ├── IGradeState.java
│       │       ├── PendingState.java
│       │       └── SubmittedState.java
│       │
│       ├── services/
│       │   ├── EnrollmentService.java
│       │   ├── FacultyService.java
│       │   └── AdminService.java
│       │
│       └── cli/
│           ├── StudentMenu.java
│           ├── FacultyMenu.java
│           └── AdminMenu.java
│
└── test/
    └── TestScenarios.java

Features
 Student Module
- View available courses
- Enroll in courses with validation (Strategy Pattern: Prerequisite, Capacity, Time Conflict checks)
- View enrolled courses
- View grades

Faculty Module
- View assigned courses
- Submit grades for students (State Pattern: Pending to Submitted)
- View student lists

 Admin Module
- Add/remove courses
- Manage users
- View system reports
- Notify advisors about grade changes (Observer Pattern)

Technologies Used

- Language: Java (JDK 8+)
- Architecture: 3-Tier Architecture
- Design Patterns: Factory Method, Strategy, Observer, State
- Principles: SOLID, DRY, KISS
- Database: In-memory (no external database)


 License

MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
 Notes

- This is a proof-of-concept implementation
- All data is stored in-memory (no persistent database)
- The system demonstrates key architectural patterns and software design principles
- Java version: 8 or higher    
>>>>>>> 3f056cb23269b51947238856c5b4c06dba766cdf
