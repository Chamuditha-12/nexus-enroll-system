package nexusenroll.models;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a degree program - Administrators can define and manage
 * degree programs, including the required courses and credits.
 */
public class Program {
    private final String id;
    private String name;
    private int totalCredits;
    private double minGpa;
    private final List<String> requiredCourseIds = new ArrayList<>();

    public Program(String id, String name, int totalCredits, double minGpa) {
        this.id = id;
        this.name = name;
        this.totalCredits = totalCredits;
        this.minGpa = minGpa;
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

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public double getMinGpa() {
        return minGpa;
    }

    public void setMinGpa(double minGpa) {
        this.minGpa = minGpa;
    }

    public List<String> getRequiredCourseIds() {
        return requiredCourseIds;
    }

    public void addRequiredCourse(String courseId) {
        requiredCourseIds.add(courseId);
    }
}