package nexusenroll.patterns.strategy;

import nexusenroll.models.Course;
import nexusenroll.models.Student;

public class TimeConflictCheckStrategy implements IValidationStrategy {
    @Override
    public String validate(Student student, Course course) {
        for (Course enrolled : student.getEnrolledCourses()) {
            if (enrolled.getId().equals(course.getId())) {
                return "Already enrolled in this course.";
            }
            if (enrolled.getSchedule().equals(course.getSchedule())) {
                return "Schedule conflict with " + enrolled.getName() + ".";
            }
        }
        return null; // valid
    }
}
