package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        InputValidator.requireNonEmpty(courseName, "Course Name");
        InputValidator.requirePositive(durationInWeeks, "Duration in Weeks");

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        throw new EntityNotFoundException("Course", id);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getActiveCourses() {
        List<Course> active = new ArrayList<>();
        for (Course c : courses) {
            if (c.isActive()) active.add(c);
        }
        return active;
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        findCourseById(id).setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        findCourseById(id).setActive(false);
    }

    public void updateCourse(int id, String courseName, String description, int durationInWeeks)
            throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(id);
        if (courseName != null && !courseName.isBlank()) course.setCourseName(courseName);
        if (description != null && !description.isBlank()) course.setDescription(description);
        if (durationInWeeks > 0) course.setDurationInWeeks(durationInWeeks);
    }
}
