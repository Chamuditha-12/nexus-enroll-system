package nexusenroll.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import nexusenroll.patterns.observer.IObserver;

/**
 * UPDATED: added email/phone (Faculty roster requirement: "contact
 * information") and a courseId -> semester map so completed courses can be
 * shown as a real "past semester schedule".
 */
public class Student extends User implements IObserver {
    private final List<String> completedCourses = new ArrayList<>();
    private final Map<String, String> completedCourseSemester = new LinkedHashMap<>();
    private final List<Course> enrolledCourses = new ArrayList<>();
    private final Map<String, Grade> grades = new HashMap<>();
    private String email = "";
    private String phone = "";

    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean hasCompleted(String courseId) {
        return completedCourses.contains(courseId);
    }

    /**
     * Backward-compatible: marks a course completed with an unspecified semester.
     */
    public void addCompleted(String courseId) {
        addCompleted(courseId, "Unspecified Semester");
    }

    public void addCompleted(String courseId, String semester) {
        completedCourses.add(courseId);
        completedCourseSemester.put(courseId, semester);
    }

    /** Past semester schedule: courseId -> semester it was taken in. */
    public Map<String, String> getCompletedCourseSemesters() {
        return completedCourseSemester;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollIn(Course course) {
        enrolledCourses.add(course);
    }

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public void addGrade(String courseId, Grade grade) {
        grades.put(courseId, grade);
    }

    public Grade getGrade(String courseId) {
        return grades.get(courseId);
    }

    public Map<String, Grade> getGrades() {
        return grades;
    }

    @Override
    public void onNotify(String event, String message) {
        System.out.println("  [NOTIFY -> Student " + name + "] (" + event + "): " + message);
    }
}