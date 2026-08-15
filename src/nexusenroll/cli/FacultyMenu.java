package nexusenroll.cli;

import java.util.List;
import java.util.Scanner;
import nexusenroll.models.Course;
import nexusenroll.models.Faculty;
import nexusenroll.models.Student;
import nexusenroll.services.AdminService;
import nexusenroll.services.FacultyService;

public class FacultyMenu {
    private final Scanner scanner;
    private final AdminService adminService;
    private final FacultyService facultyService;

    public FacultyMenu(Scanner scanner, AdminService adminService, FacultyService facultyService) {
        this.scanner = scanner;
        this.adminService = adminService;
        this.facultyService = facultyService;
    }

    public void show(Faculty faculty) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Faculty Menu (" + faculty.getName() + ") ===");
            System.out.println("1. View Class Roster (with contact info)");
            System.out.println("2. Submit Grade");
            System.out.println("3. Correct a Submitted Grade");
            System.out.println("4. Request Course Change");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    viewRoster(faculty);
                    break;
                case 2:
                    submitGrade();
                    break;
                case 3:
                    correctGrade();
                    break;
                case 4:
                    requestCourseChange(faculty);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void viewRoster(Faculty faculty) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        Course course = adminService.getCourse(courseId);
        if (course == null) {
            System.out.println("No such course.");
            return;
        }
        List<Student> roster = facultyService.viewRoster(course, adminService.getAllStudents());
        System.out.println("\n--- Roster for " + course.getName() + " ---");
        if (roster.isEmpty()) {
            System.out.println("No students enrolled yet.");
        }
        for (Student s : roster) {
            System.out.println("- " + s.getId() + ": " + s.getName()
                    + " | Email: " + (s.getEmail().isEmpty() ? "N/A" : s.getEmail())
                    + " | Phone: " + (s.getPhone().isEmpty() ? "N/A" : s.getPhone()));
        }
    }

    private void submitGrade() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        Student student = adminService.getStudent(studentId);
        if (student == null) {
            System.out.println("No such student.");
            return;
        }
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        System.out.print("Enter Grade Letter (e.g. A, B, C): ");
        char letter = scanner.nextLine().trim().charAt(0);

        facultyService.submitGrade(student, courseId, letter);
        System.out.println("Grade submitted for " + student.getName() + ".");
    }

    private void correctGrade() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        Student student = adminService.getStudent(studentId);
        if (student == null) {
            System.out.println("No such student.");
            return;
        }
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        System.out.print("Enter New Grade Letter: ");
        char letter = scanner.nextLine().trim().charAt(0);

        facultyService.correctGrade(student, courseId, letter);
        System.out.println("Grade corrected for " + student.getName() + ".");
    }

    private void requestCourseChange(Faculty faculty) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        Course course = adminService.getCourse(courseId);
        if (course == null) {
            System.out.println("No such course.");
            return;
        }
        System.out.print("Describe the requested change: ");
        String change = scanner.nextLine().trim();
        System.out.println(facultyService.requestCourseChange(faculty, course, change, adminService));
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
