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


