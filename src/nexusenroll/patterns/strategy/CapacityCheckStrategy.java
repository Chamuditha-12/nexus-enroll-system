package nexusenroll.patterns.strategy;

import nexusenroll.models.Course;
import nexusenroll.models.Student;

public class CapacityCheckStrategy implements IValidationStrategy {
    @Override
    public String validate(Student student, Course course) {
        if (!course.hasSeats()) {
            return "Course " + course.getName() + " is full.";
        }
        return null; // valid
    }
}