package nexusenroll.models;

/**
 * Instructors can submit requests to update course descriptions, add
 * prerequisites, or change course capacity (these requests must be approved
 * by an administrator).
 */

public class CourseChangeRequest {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private static int counter = 1;

    private final String requestId;
    private final Faculty faculty;
    private final Course course;
    private final String requestedChange;
    private Status status = Status.PENDING;

    public CourseChangeRequest(Faculty faculty, Course course, String requestedChange) {
        this.requestId = "REQ" + (counter++);
        this.faculty = faculty;
        this.course = course;
        this.requestedChange = requestedChange;
    }

    public String getRequestId() {
        return requestId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Course getCourse() {
        return course;
    }

    public String getRequestedChange() {
        return requestedChange;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}