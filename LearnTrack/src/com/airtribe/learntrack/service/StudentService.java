package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    // Add student with full details
    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        InputValidator.requireNonEmpty(firstName, "First Name");
        InputValidator.requireNonEmpty(lastName, "Last Name");
        InputValidator.requireNonEmpty(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    // Overloaded: Add student without email
    public Student addStudent(String firstName, String lastName, String batch)
            throws InvalidInputException {
        InputValidator.requireNonEmpty(firstName, "First Name");
        InputValidator.requireNonEmpty(lastName, "Last Name");
        InputValidator.requireNonEmpty(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        throw new EntityNotFoundException("Student", id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getActiveStudents() {
        List<Student> active = new ArrayList<>();
        for (Student s : students) {
            if (s.isActive()) active.add(s);
        }
        return active;
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);
        if (firstName != null && !firstName.isBlank()) student.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) student.setLastName(lastName);
        if (email != null && !email.isBlank()) student.setEmail(email);
        if (batch != null && !batch.isBlank()) student.setBatch(batch);
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void activateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(true);
    }
}
