package nexusenroll.patterns.factory;

import nexusenroll.models.Administrator;
import nexusenroll.models.Faculty;
import nexusenroll.models.Student;
import nexusenroll.models.User;

/**
 * FACTORY METHOD PATTERN.
 * Centralises the creation logic of every User subtype so that client code
 * (AdminService, CLI menus) never calls "new Student(...)" directly.
 * Open/Closed Principle: adding a new role later (e.g. "Advisor" or "TA")
 * only means extending this factory - no existing client code changes.
 */
public class UserFactory {
    public static User createUser(String type, String id, String name) {
        switch (type) {
            case "Student":
                return new Student(id, name);
            case "Faculty":
                return new Faculty(id, name);
            case "Administrator":
                return new Administrator(id, name);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}