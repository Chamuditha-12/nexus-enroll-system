// NexusEnroll Main entry point
package nexusenroll;

import java.util.Scanner;
import nexusenroll.cli.AdminMenu;
import nexusenroll.cli.FacultyMenu;
import nexusenroll.cli.StudentMenu;
import nexusenroll.models.Faculty;
import nexusenroll.models.Student;
import nexusenroll.services.AdminService;
import nexusenroll.services.EnrollmentService;
import nexusenroll.services.FacultyService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        EnrollmentService enrollmentService = new EnrollmentService();
        FacultyService facultyService = new FacultyService();

        StudentMenu studentMenu = new StudentMenu(scanner, adminService, enrollmentService);
        FacultyMenu facultyMenu = new FacultyMenu(scanner, adminService, facultyService);
        AdminMenu adminMenu = new AdminMenu(scanner, adminService);

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu - Select Role:");
            System.out.println("1. Student");
            System.out.println("2. Faculty");
            System.out.println("3. Administrator");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String line = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String sid = scanner.nextLine().trim();
                    Student s = adminService.getStudent(sid);
                    if (s == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    studentMenu.show(s);
                    break;
                case 2:
                    System.out.print("Enter Faculty ID: ");
                    String fid = scanner.nextLine().trim();
                    Faculty f = adminService.getFaculty(fid);
                    if (f == null) {
                        System.out.println("Faculty not found.");
                        break;
                    }
                    facultyMenu.show(f);
                    break;
                case 3:
                    adminMenu.show();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye.");
    }
}