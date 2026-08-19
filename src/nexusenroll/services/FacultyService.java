package nexusenroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nexusenroll.models.Course;
import nexusenroll.models.CourseChangeRequest;
import nexusenroll.models.Faculty;
import nexusenroll.models.Grade;
import nexusenroll.models.Student;

/**
 * requestCourseChange() creates a CourseChangeRequest
 * and queues it in AdminService, instead of just returning a printed
 * message.
 */
public class FacultyService {

    public List<Student> viewRoster(Course course, Map<String, Student> allStudents) {
        List<Student> roster = new ArrayList<>();
        for (Student s : allStudents.values()) {
            if (s.getEnrolledCourses().contains(course)) {
                roster.add(s);
            }
        }
        return roster;
    }

    public void submitGrade(Student student, String courseId, char letter) {
        Grade grade = student.getGrade(courseId);
        if (grade == null) {
            grade = new Grade(student.getId(), letter);
            student.addGrade(courseId, grade);
        }
        grade.submit();
    }

    public void correctGrade(Student student, String courseId, char newLetter) {
        Grade grade = student.getGrade(courseId);
        if (grade != null) {
            grade.correct(newLetter);
        }
    }

    /** Now queues a real request in AdminService for later approval/rejection. */
    public String requestCourseChange(Faculty faculty, Course course, String requestedChange,
            AdminService adminService) {
        CourseChangeRequest request = new CourseChangeRequest(faculty, course, requestedChange);
        adminService.submitChangeRequest(request);
        return "Change request " + request.getRequestId() + " submitted by " + faculty.getName()
                + " for " + course.getName() + ": " + requestedChange
                + " (Pending Administrator approval)";
    }
}
