package nexusenroll.patterns.strategy;

import nexusenroll.models.Course;
import nexusenroll.models.Student;

/**
 * STRATEGY PATTERN.
 * Each enrolment business rule is its own interchangeable strategy object.
 * Adding a new rule (e.g. a financial-hold check) means adding a new class
 * that implements this interface - EnrollmentService itself never changes
 * (Open/Closed Principle). Each strategy has exactly one responsibility
 * (Single Responsibility Principle).
 *
 * Returns null if the check passes, or an error message describing why it
 * failed.
 */
public interface IValidationStrategy {
    String validate(Student student, Course course);
}