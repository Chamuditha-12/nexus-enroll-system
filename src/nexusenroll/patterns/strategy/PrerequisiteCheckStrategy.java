package nexusenroll.patterns.strategy;

import nexusenroll.models.Course;
import nexusenroll.models.Student;

public class PrerequisiteCheckStrategy implements IValidationStrategy {
    @Override
    public String validate(Student student, Course course) {
        for (String prereq : course.getPrerequisites()) {
            if (!student.hasCompleted(prereq)) {
                return "Missing prerequisite: " + prereq;
            }
        }
        return null; // valid
    }
}
