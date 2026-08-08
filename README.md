# NexusEnroll - University Course Enrolment System

A modernised university course enrolment system built as a proof-of-concept 
for the Software Architecture (SCS 2303) module, University of Colombo School 
of Computing (UCSC).

## Overview
NexusEnroll replaces a legacy monolithic enrolment system with a scalable, 
maintainable 3-Tier architecture, demonstrating core software design 
principles (SOLID, DRY, KISS) and object-oriented design patterns.

## Modules
- **Student Module** - course search/browsing, enrolment/drop, current & past 
  semester schedules, academic progress tracking
- **Faculty Module** - class rosters (with contact info), grade submission 
  and correction, course change requests
- **Administrator Module** - course/program management (create, edit, 
  delete), student/faculty account management, change-request approval, 
  and reporting (enrolment, faculty workload, course popularity, 
  department statistics)

## Architecture
3-Tier Architecture: Presentation Tier -> Business Logic Tier -> Data Tier

- **Presentation Tier** (`cli/`) - console menus for Student, Faculty, and 
  Administrator roles
- **Business Logic Tier** (`services/`, `patterns/`) - core rules, 
  validation, and design pattern implementations
- **Data Tier** (`models/`) - in-memory data representation (Student, 
  Faculty, Course, Grade, Program, etc.)

## Design Patterns Used
- **Factory Method** - `UserFactory` centralises creation of Student/Faculty/Administrator objects
- **Strategy** - `PrerequisiteCheckStrategy`, `CapacityCheckStrategy`, `TimeConflictCheckStrategy` encapsulate enrolment validation rules
- **Observer** - `NotificationSubject`/`IObserver`/`Advisor` power the decoupled notification system (waitlist alerts, advisor alerts)
- **State** - `PendingState`/`SubmittedState` model the grade lifecycle
- **Facade** - `EnrollmentService` provides a single simplified interface over all enrolment validation logic

##Project Structure
```
NexusEnroll/
├── src/
│   └── nexusenroll/
│       ├── Main.java
│       ├── models/
│       │   ├── User.java
│       │   ├── Student.java
│       │   ├── Faculty.java
│       │   ├── Administrator.java
│       │   ├── Course.java
│       │   ├── Grade.java
│       │   ├── Program.java
│       │   └── CourseChangeRequest.java
│       ├── patterns/
│       │   ├── factory/
│       │   │   └── UserFactory.java
│       │   ├── strategy/
│       │   │   ├── IValidationStrategy.java
│       │   │   ├── PrerequisiteCheckStrategy.java
│       │   │   ├── CapacityCheckStrategy.java
│       │   │   └── TimeConflictCheckStrategy.java
│       │   ├── observer/
│       │   │   ├── IObserver.java
│       │   │   ├── NotificationSubject.java
│       │   │   └── Advisor.java
│       │   └── state/
│       │       ├── IGradeState.java
│       │       ├── PendingState.java
│       │       └── SubmittedState.java
│       ├── services/
│       │   ├── EnrollmentService.java
│       │   ├── FacultyService.java
│       │   └── AdminService.java
│       └── cli/
│           ├── StudentMenu.java
│           ├── FacultyMenu.java
│           └── AdminMenu.java
└── test/
    └── TestScenarios.java
```

## Tech Stack
- Language: Java (JDK 17+)
- Tools: VS Code, javac/java (JDK command-line tools)

## How to Build and Run

**Interactive CLI (Main.java):**
```bash
cd src
javac nexusenroll/*.java nexusenroll/models/*.java nexusenroll/patterns/factory/*.java nexusenroll/patterns/strategy/*.java nexusenroll/patterns/observer/*.java nexusenroll/patterns/state/*.java nexusenroll/services/*.java nexusenroll/cli/*.java
java nexusenroll.Main
```

**Automated demo (TestScenarios.java, no input needed):**
```bash
cd src
javac nexusenroll/*.java nexusenroll/models/*.java nexusenroll/patterns/factory/*.java nexusenroll/patterns/strategy/*.java nexusenroll/patterns/observer/*.java nexusenroll/patterns/state/*.java nexusenroll/services/*.java nexusenroll/cli/*.java
javac -cp . ../test/TestScenarios.java -d .
java TestScenarios
```

## How to Contribute (for team members)

1. Clone the repo: `git clone <repo-url>`
2. Create a feature branch: `git checkout -b feature/your-module-name`
3. Make your changes in your assigned folder (`models/`, `services/`, `cli/`, etc.)
4. Compile and test locally before pushing
5. Commit with a clear message: `git commit -m "feat: implement grade submission logic"`
6. Push and open a Pull Request for review before merging to `main`

## Assignment
Software Architecture (SCS 2303) - Assignment 3
Deadline: 20 August 2026

## MIT License

Copyright (c) 2026 NexusEnroll Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
