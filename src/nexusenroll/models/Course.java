package nexusenroll.models;

import java.util.ArrayList;
import java.util.List;
import nexusenroll.patterns.observer.NotificationSubject;

/**
 * carries description, instructorName and department so the
 * Student Module can display full course info and search by them, and so
 * the Administrator Module can edit these fields and run department-based
 * reports.
 */
public class Course extends NotificationSubject {
    private final String id;
    private String name;
    private int capacity;
    private int enrolledCount = 0;
    private String schedule;
    private String description;
    private String instructorName;
    private String department;
    private final List<String> prerequisites = new ArrayList<>();
    private final List<Student> waitlist = new ArrayList<>();

    /** Backward-compatible constructor (defaults for the new fields). */
    public Course(String id, String name, int capacity, String schedule) {
        this(id, name, capacity, schedule, "No description provided.", "TBA", "General");
    }

    public Course(String id, String name, int capacity, String schedule,
            String description, String instructorName, String department) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.schedule = schedule;
        this.description = description;
        this.instructorName = instructorName;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(String courseId) {
        prerequisites.add(courseId);
    }

    public boolean hasSeats() {
        return enrolledCount < capacity;
    }

    /** Used by the Course Popularity Trends report (>90% capacity). */
    public double getOccupancyRate() {
        return capacity == 0 ? 0.0 : (double) enrolledCount / capacity;
    }

    public void commitEnrollment() {
        enrolledCount++;
    }

    public void addToWaitlist(Student student) {
        waitlist.add(student);
        subscribe(student);
    }

    public void dropStudent() {
        if (enrolledCount > 0)
            enrolledCount--;
        notifyAll("ADVISOR_ALERT", "A student dropped " + name + ".");
        if (!waitlist.isEmpty()) {
            waitlist.remove(0);
            notifyAll("SEAT_AVAILABLE", "A seat opened up in " + name + ". You can now enrol.");
        }
    }
}
