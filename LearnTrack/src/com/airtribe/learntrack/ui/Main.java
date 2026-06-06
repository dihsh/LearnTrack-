package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to LearnTrack v1.0");
        System.out.println("   Student & Course Management System");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> enrollmentMenu();
                case 0 -> {
                    System.out.println("\nGoodbye! Exiting LearnTrack.");
                    running = false;
                }
                default -> System.out.println("[!] Invalid option. Please try again.\n");
            }
        }
        scanner.close();
    }

    // ─────────────────────────────────────────────
    //  MAIN MENU
    // ─────────────────────────────────────────────
    private static void printMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println(" 1. Student Management");
        System.out.println(" 2. Course Management");
        System.out.println(" 3. Enrollment Management");
        System.out.println(" 0. Exit");
        System.out.println("================================");
    }

    // ─────────────────────────────────────────────
    //  STUDENT MENU
    // ─────────────────────────────────────────────
    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println(" 1. Add New Student");
            System.out.println(" 2. View All Students");
            System.out.println(" 3. Search Student by ID");
            System.out.println(" 4. Update Student");
            System.out.println(" 5. Deactivate Student");
            System.out.println(" 6. Activate Student");
            System.out.println(" 0. Back to Main Menu");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listStudents();
                case 3 -> searchStudentById();
                case 4 -> updateStudent();
                case 5 -> deactivateStudent();
                case 6 -> activateStudent();
                case 0 -> back = true;
                default -> System.out.println("[!] Invalid option.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n-- Add New Student --");
        String firstName = readString("First Name: ");
        String lastName  = readString("Last Name: ");
        String email     = readString("Email (press Enter to skip): ");
        String batch     = readString("Batch (e.g. Batch-2024): ");

        try {
            Student s;
            if (email.isBlank()) {
                s = studentService.addStudent(firstName, lastName, batch);
            } else {
                s = studentService.addStudent(firstName, lastName, email, batch);
            }
            System.out.println("[✓] Student added: " + s);
        } catch (InvalidInputException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    private static void listStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("[!] No students found.");
            return;
        }
        System.out.println("\n-- All Students --");
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }

    private static void searchStudentById() {
        int id = readInt("Enter Student ID: ");
        try {
            Student s = studentService.findStudentById(id);
            System.out.println("[✓] Found: " + s);
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void updateStudent() {
        int id = readInt("Enter Student ID to update: ");
        System.out.println("(Press Enter to keep existing value)");
        String firstName = readString("New First Name: ");
        String lastName  = readString("New Last Name: ");
        String email     = readString("New Email: ");
        String batch     = readString("New Batch: ");

        try {
            studentService.updateStudent(id, firstName, lastName, email, batch);
            System.out.println("[✓] Student updated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        int id = readInt("Enter Student ID to deactivate: ");
        try {
            studentService.deactivateStudent(id);
            System.out.println("[✓] Student deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void activateStudent() {
        int id = readInt("Enter Student ID to activate: ");
        try {
            studentService.activateStudent(id);
            System.out.println("[✓] Student activated.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  COURSE MENU
    // ─────────────────────────────────────────────
    private static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println(" 1. Add New Course");
            System.out.println(" 2. View All Courses");
            System.out.println(" 3. Search Course by ID");
            System.out.println(" 4. Update Course");
            System.out.println(" 5. Deactivate Course");
            System.out.println(" 6. Activate Course");
            System.out.println(" 0. Back to Main Menu");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> listCourses();
                case 3 -> searchCourseById();
                case 4 -> updateCourse();
                case 5 -> deactivateCourse();
                case 6 -> activateCourse();
                case 0 -> back = true;
                default -> System.out.println("[!] Invalid option.");
            }
        }
    }

    private static void addCourse() {
        System.out.println("\n-- Add New Course --");
        String name = readString("Course Name: ");
        String desc = readString("Description: ");
        int weeks   = readInt("Duration (in weeks): ");

        try {
            Course c = courseService.addCourse(name, desc, weeks);
            System.out.println("[✓] Course added: " + c);
        } catch (InvalidInputException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    private static void listCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("[!] No courses found.");
            return;
        }
        System.out.println("\n-- All Courses --");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
    }

    private static void searchCourseById() {
        int id = readInt("Enter Course ID: ");
        try {
            Course c = courseService.findCourseById(id);
            System.out.println("[✓] Found: " + c);
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void updateCourse() {
        int id = readInt("Enter Course ID to update: ");
        System.out.println("(Press Enter to keep existing value)");
        String name = readString("New Course Name: ");
        String desc = readString("New Description: ");
        String weeksStr = readString("New Duration in Weeks (0 to skip): ");
        int weeks = 0;
        try { weeks = Integer.parseInt(weeksStr); } catch (NumberFormatException ignored) {}

        try {
            courseService.updateCourse(id, name, desc, weeks);
            System.out.println("[✓] Course updated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void deactivateCourse() {
        int id = readInt("Enter Course ID to deactivate: ");
        try {
            courseService.deactivateCourse(id);
            System.out.println("[✓] Course deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void activateCourse() {
        int id = readInt("Enter Course ID to activate: ");
        try {
            courseService.activateCourse(id);
            System.out.println("[✓] Course activated.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  ENROLLMENT MENU
    // ─────────────────────────────────────────────
    private static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println(" 1. Enroll Student in a Course");
            System.out.println(" 2. View All Enrollments");
            System.out.println(" 3. View Enrollments for a Student");
            System.out.println(" 4. Mark Enrollment as Completed");
            System.out.println(" 5. Cancel Enrollment");
            System.out.println(" 0. Back to Main Menu");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> enrollStudent();
                case 2 -> listAllEnrollments();
                case 3 -> listEnrollmentsForStudent();
                case 4 -> markCompleted();
                case 5 -> cancelEnrollment();
                case 0 -> back = true;
                default -> System.out.println("[!] Invalid option.");
            }
        }
    }

    private static void enrollStudent() {
        int studentId = readInt("Enter Student ID: ");
        int courseId  = readInt("Enter Course ID: ");
        try {
            Enrollment e = enrollmentService.enrollStudent(studentId, courseId, studentService, courseService);
            System.out.println("[✓] Enrolled successfully: " + e);
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void listAllEnrollments() {
        List<Enrollment> list = enrollmentService.getAllEnrollments();
        if (list.isEmpty()) {
            System.out.println("[!] No enrollments found.");
            return;
        }
        System.out.println("\n-- All Enrollments --");
        for (Enrollment e : list) {
            System.out.println("  " + e);
        }
    }

    private static void listEnrollmentsForStudent() {
        int id = readInt("Enter Student ID: ");
        List<Enrollment> list = enrollmentService.getEnrollmentsForStudent(id);
        if (list.isEmpty()) {
            System.out.println("[!] No enrollments found for Student ID " + id + ".");
            return;
        }
        System.out.println("\n-- Enrollments for Student " + id + " --");
        for (Enrollment e : list) {
            System.out.println("  " + e);
        }
    }

    private static void markCompleted() {
        int id = readInt("Enter Enrollment ID to mark completed: ");
        try {
            enrollmentService.markCompleted(id);
            System.out.println("[✓] Enrollment marked as COMPLETED.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    private static void cancelEnrollment() {
        int id = readInt("Enter Enrollment ID to cancel: ");
        try {
            enrollmentService.markCancelled(id);
            System.out.println("[✓] Enrollment marked as CANCELLED.");
        } catch (EntityNotFoundException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  HELPER METHODS
    // ─────────────────────────────────────────────
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Please enter a valid number.");
            }
        }
    }
}
