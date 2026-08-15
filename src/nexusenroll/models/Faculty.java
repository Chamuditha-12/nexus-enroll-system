package nexusenroll.models;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {
    private final List<Course> assignedCourses = new ArrayList<>();
    private String department = "";

    public Faculty(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}