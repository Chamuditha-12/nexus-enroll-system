package nexusenroll.services;

import java.util.ArrayList;
import java.util.List;
import nexusenroll.models.Course;
import nexusenroll.models.Student;
import nexusenroll.patterns.strategy.CapacityCheckStrategy;
import nexusenroll.patterns.strategy.IValidationStrategy;
import nexusenroll.patterns.strategy.PrerequisiteCheckStrategy;
import nexusenroll.patterns.strategy.TimeConflictCheckStrategy;

//Facade Pattern 
public class EnrollmentService {
    private final List<IValidationStrategy> strategies = new ArrayList<>();

    public EnrollmentService() {
        strategies.add(new PrerequisiteCheckStrategy());
        strategies.add(new CapacityCheckStrategy());
        strategies.add(new TimeConflictCheckStrategy());
    }

    /** Returns null on success, or an error message on failure. */
    public String enroll(Student student, Course course) {
        for (IValidationStrategy strategy : strategies) {
            String error = strategy.validate(student, course);
            if (error != null) {
                if (error.contains("full")) {
                    course.addToWaitlist(student);
                    return error + " Added to waitlist.";
                }
                return error;
            }
        }
        // All checks passed - commit
        course.commitEnrollment();
        student.enrollIn(course);
        return null;
    }

    public void drop(Student student, Course course) {
        student.dropCourse(course);
        course.dropStudent(); // internally notifies advisor + waitlisted students
    }
}
