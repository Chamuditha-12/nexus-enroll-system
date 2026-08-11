package nexusenroll.services;

import java.util.ArrayList;
import java.util.List;
import nexusenroll.models.Course;
import nexusenroll.models.Student;
import nexusenroll.patterns.strategy.CapacityCheckStrategy;
import nexusenroll.patterns.strategy.IValidationStrategy;
import nexusenroll.patterns.strategy.PrerequisiteCheckStrategy;
import nexusenroll.patterns.strategy.TimeConflictCheckStrategy;

/**
 * FACADE PATTERN.
 * Gives client code (CLI menus) ONE simple entry point - enroll()/drop() -
 * that hides: running every Strategy validator, committing the "all or
 * nothing" transaction, and firing Observer notifications. The client never
 * needs to know these steps exist.
 *
 * Also satisfies the Transaction Management requirement: either every
 * check passes and the enrolment is committed, or nothing changes at all.
 */
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
