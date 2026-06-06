# LearnTrack — Student & Course Management System

A console-based Student & Course Management System built in **Core Java**, demonstrating OOP fundamentals, encapsulation, inheritance, polymorphism, collections, and exception handling.

---

## 📁 Project Structure

```
LearnTrack/
├── src/
│   └── com/airtribe/learntrack/
│       ├── entity/          # Core domain classes
│       │   ├── Person.java
│       │   ├── Student.java
│       │   ├── Trainer.java
│       │   ├── Course.java
│       │   └── Enrollment.java
│       ├── service/         # Business logic
│       │   ├── StudentService.java
│       │   ├── CourseService.java
│       │   └── EnrollmentService.java
│       ├── exception/       # Custom exceptions
│       │   ├── EntityNotFoundException.java
│       │   └── InvalidInputException.java
│       ├── util/            # Helper utilities
│       │   ├── IdGenerator.java
│       │   └── InputValidator.java
│       └── ui/              # Console UI
│           └── Main.java
└── docs/
    ├── Setup_Instructions.md
    ├── JVM_Basics.md
    └── Design_Notes.md
```

---

## 🚀 How to Compile and Run

### Prerequisites
- Java JDK 17 or above installed
- VS Code with the **Extension Pack for Java** installed (recommended)

### Option 1: Run in VS Code
1. Open the `LearnTrack` folder in VS Code (`File → Open Folder`)
2. Open `src/com/airtribe/learntrack/ui/Main.java`
3. Click **Run** (▶️) button above the `main` method
4. Interact with the console menu in the Terminal panel

### Option 2: Run via Terminal

```bash
# From the LearnTrack root directory

# Step 1: Compile all Java files
javac -d out $(find src -name "*.java")

# Step 2: Run the Main class
java -cp out com.airtribe.learntrack.ui.Main
```

---

## ✨ Features

### Student Management
- Add a new student (with or without email — constructor overloading)
- View all students
- Search student by ID
- Update student details
- Activate / Deactivate a student

### Course Management
- Add a new course
- View all courses
- Search course by ID
- Update course details
- Activate / Deactivate a course

### Enrollment Management
- Enroll a student in a course
- View all enrollments
- View enrollments for a specific student
- Mark enrollment as Completed or Cancelled

---

## 🗂️ Class Diagram

```
                    ┌─────────────┐
                    │   Person    │
                    │─────────────│
                    │ - id        │
                    │ - firstName │
                    │ - lastName  │
                    │ - email     │
                    │─────────────│
                    │ +getDisplay │
                    │  Name()     │
                    └──────┬──────┘
                           │ extends
              ┌────────────┴────────────┐
              │                         │
       ┌──────▼──────┐           ┌──────▼──────┐
       │   Student   │           │   Trainer   │
       │─────────────│           │─────────────│
       │ - batch     │           │ - expertise │
       │ - active    │           │─────────────│
       │─────────────│           │ +getDisplay │
       │ +getDisplay │           │  Name()     │
       │  Name()     │           └─────────────┘
       └─────────────┘

       ┌─────────────┐           ┌──────────────┐
       │   Course    │           │  Enrollment  │
       │─────────────│           │──────────────│
       │ - id        │           │ - id         │
       │ - courseName│           │ - studentId  │◄─── references Student.id
       │ - description│          │ - courseId   │◄─── references Course.id
       │ - duration  │           │ - date       │
       │ - active    │           │ - status     │
       └─────────────┘           │  (enum)      │
                                 └──────────────┘

       ┌──────────────────┐   ┌──────────────────┐   ┌──────────────────────┐
       │  StudentService  │   │  CourseService   │   │  EnrollmentService   │
       │──────────────────│   │──────────────────│   │──────────────────────│
       │ -students:List   │   │ -courses:List    │   │ -enrollments:List    │
       │ +addStudent()    │   │ +addCourse()     │   │ +enrollStudent()     │
       │ +findById()      │   │ +findById()      │   │ +markCompleted()     │
       │ +deactivate()    │   │ +deactivate()    │   │ +markCancelled()     │
       └──────────────────┘   └──────────────────┘   └──────────────────────┘

       ┌──────────────┐   ┌──────────────────┐
       │  IdGenerator │   │  InputValidator  │
       │──────────────│   │──────────────────│
       │ -counters    │   │ +requireNonEmpty │
       │  (static)    │   │ +requirePositive │
       │ +getNext*()  │   │ +isValidEmail()  │
       └──────────────┘   └──────────────────┘

       ┌─────────────────────────┐   ┌──────────────────────────┐
       │  EntityNotFoundException│   │  InvalidInputException   │
       │  (extends Exception)    │   │  (extends Exception)     │
       └─────────────────────────┘   └──────────────────────────┘
```

---

## 📚 Documentation

See the `docs/` folder for:
- `Setup_Instructions.md` — JDK setup & Hello World
- `JVM_Basics.md` — JDK vs JRE vs JVM, bytecode, WORA
- `Design_Notes.md` — Design decisions and reasoning
