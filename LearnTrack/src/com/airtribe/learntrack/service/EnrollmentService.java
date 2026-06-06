package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Enrollment.Status;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public Enrollment enrollStudent(int studentId, int courseId,
                                    StudentService studentService,
                                    CourseService courseService)
            throws EntityNotFoundException, InvalidInputException {

        // Validate student and course exist and are active
        var student = studentService.findStudentById(studentId);
        var course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Student is not active and cannot be enrolled.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Course is not active and cannot accept enrollments.");
        }

        // Check for duplicate active enrollment
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId
                    && e.getCourseId() == courseId
                    && e.getStatus() == Status.ACTIVE) {
                throw new InvalidInputException("Student is already actively enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) result.add(e);
        }
        return result;
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) return e;
        }
        throw new EntityNotFoundException("Enrollment", id);
    }

    public void markCompleted(int enrollmentId) throws EntityNotFoundException {
        findEnrollmentById(enrollmentId).setStatus(Status.COMPLETED);
    }

    public void markCancelled(int enrollmentId) throws EntityNotFoundException {
        findEnrollmentById(enrollmentId).setStatus(Status.CANCELLED);
    }
}
