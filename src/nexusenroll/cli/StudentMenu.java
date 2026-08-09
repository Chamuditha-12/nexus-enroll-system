package nexusenroll.cli;

import java.util.List;
import java.util.Scanner;
import nexusenroll.models.Course;
import nexusenroll.models.Student;
import nexusenroll.services.AdminService;
import nexusenroll.services.EnrollmentService;

public class StudentMenu {
    private final Scanner scanner;
    private final AdminService adminService;
    private final EnrollmentService enrollmentService;

    public StudentMenu(Scanner scanner, AdminService adminService, EnrollmentService enrollmentService) {
        this.scanner = scanner;
        this.adminService = adminService;
        this.enrollmentService = enrollmentService;
    }

    public void show(Student student) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Student Menu (" + student.getName() + ") ===");
            System.out.println("1. Browse Course Catalogue");
            System.out.println("2. Search Courses (department / instructor / keyword)");
            System.out.println("3. Enrol in a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View My Current Schedule");
            System.out.println("6. View Past Semester Schedule");
            System.out.println("7. Track Academic Progress");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    browseCatalogue();
                    break;
                case 2:
                    searchCourses();
                    break;
                case 3:
                    enrol(student);
                    break;
                case 4:
                    drop(student);
                    break;
                case 5:
                    viewSchedule(student);
                    break;
                case 6:
                    viewPastSemesterSchedule(student);
                    break;
                case 7:
                    trackProgress(student);
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

    private void printCourse(Course c) {
        System.out.println("- " + c.getId() + ": " + c.getName()
                + " | Dept: " + c.getDepartment()
                + " | Instructor: " + c.getInstructorName()
                + " | " + c.getDescription()
                + " | Schedule: " + c.getSchedule()
                + " | Seats: " + c.getEnrolledCount() + "/" + c.getCapacity()
                + " | Prerequisites: " + c.getPrerequisites());
    }


