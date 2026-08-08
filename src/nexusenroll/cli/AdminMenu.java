package nexusenroll.cli;

import java.util.List;
import java.util.Scanner;
import nexusenroll.models.Course;
import nexusenroll.models.CourseChangeRequest;
import nexusenroll.models.Program;
import nexusenroll.models.Student;
import nexusenroll.services.AdminService;

public class AdminMenu {
    private final Scanner scanner;
    private final AdminService adminService;

    public AdminMenu(Scanner scanner, AdminService adminService) {
        this.scanner = scanner;
        this.adminService = adminService;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Faculty");
            System.out.println("3. Add Course");
            System.out.println("4. Edit Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Edit Student");
            System.out.println("7. Edit Faculty");
            System.out.println("8. View All Students");
            System.out.println("9. View All Faculty");
            System.out.println("10. View All Courses");
            System.out.println("11. Assign Course to Faculty");
            System.out.println("12. Force-Add Student to Course");
            System.out.println("13. Deactivate Student");
            System.out.println("14. Add Degree Program");
            System.out.println("15. View All Programs");
            System.out.println("16. View Pending Course Change Requests");
            System.out.println("17. Approve/Reject a Change Request");
            System.out.println("18. Generate Enrolment Report");
            System.out.println("19. Generate Faculty Workload Report");
            System.out.println("20. Generate Course Popularity Report (>90%)");
            System.out.println("21. Generate Enrolment-by-Department Report");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addFaculty();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    editCourse();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 6:
                    editStudent();
                    break;
                case 7:
                    editFaculty();
                    break;
                case 8:
                    viewAllStudents();
                    break;
                case 9:
                    viewAllFaculty();
                    break;
                case 10:
                    viewAllCourses();
                    break;
                case 11:
                    assignCourseToFaculty();
                    break;
                case 12:
                    forceAddStudent();
                    break;
                case 13:
                    deactivateStudent();
                    break;
                case 14:
                    addProgram();
                    break;
                case 15:
                    viewAllPrograms();
                    break;
                case 16:
                    viewPendingChangeRequests();
                    break;
                case 17:
                    resolveChangeRequest();
                    break;
                case 18:
                    System.out.println(adminService.generateEnrollmentReport());
                    break;
                case 19:
                    System.out.println(adminService.generateFacultyWorkloadReport());
                    break;
                case 20:
                    System.out.println(adminService.generateCoursePopularityReport());
                    break;
                case 21:
                    System.out.println(adminService.generateEnrollmentByDepartmentReport());
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


