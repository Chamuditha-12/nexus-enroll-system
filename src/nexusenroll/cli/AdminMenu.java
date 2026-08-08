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

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        adminService.addStudent(id, name);
        System.out.println("Student added successfully.");
    }

    private void addFaculty() {
        System.out.print("Enter Faculty ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Faculty Name: ");
        String name = scanner.nextLine().trim();
        adminService.addFaculty(id, name);
        System.out.println("Faculty added successfully.");
    }

    private void addCourse() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Capacity: ");
        int capacity = readInt();
        System.out.print("Enter Schedule (e.g. Mon/Wed 10:00-11:30): ");
        String schedule = scanner.nextLine().trim();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine().trim();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        adminService.addCourse(id, name, capacity, schedule, description, instructor, department);
        System.out.println("Course added successfully.");
    }

    private void editCourse() {
        System.out.print("Enter Course ID to edit: ");
        String id = scanner.nextLine().trim();
        if (adminService.getCourse(id) == null) {
            System.out.println("No such course.");
            return;
        }
        System.out.print("New Name (blank to skip): ");
        String name = scanner.nextLine().trim();
        System.out.print("New Capacity (blank to skip): ");
        String capStr = scanner.nextLine().trim();
        Integer capacity = capStr.isEmpty() ? null : Integer.parseInt(capStr);
        System.out.print("New Schedule (blank to skip): ");
        String schedule = scanner.nextLine().trim();
        System.out.print("New Description (blank to skip): ");
        String description = scanner.nextLine().trim();
        System.out.print("New Instructor (blank to skip): ");
        String instructor = scanner.nextLine().trim();
        System.out.print("New Department (blank to skip): ");
        String department = scanner.nextLine().trim();

        boolean ok = adminService.editCourse(id, name, capacity, schedule, description, instructor, department);
        System.out.println(ok ? "Course updated successfully." : "Update failed.");
    }

    private void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        String id = scanner.nextLine().trim();
        boolean ok = adminService.deleteCourse(id);
        System.out.println(ok ? "Course deleted." : "No such course.");
    }

    private void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine().trim();
        System.out.print("New Name (blank to skip): ");
        String name = scanner.nextLine().trim();
        System.out.print("New Email (blank to skip): ");
        String email = scanner.nextLine().trim();
        System.out.print("New Phone (blank to skip): ");
        String phone = scanner.nextLine().trim();
        boolean ok = adminService.editStudent(id, name, email.isEmpty() ? null : email, phone.isEmpty() ? null : phone);
        System.out.println(ok ? "Student updated." : "No such student.");
    }

    private void editFaculty() {
        System.out.print("Enter Faculty ID to edit: ");
        String id = scanner.nextLine().trim();
        System.out.print("New Name (blank to skip): ");
        String name = scanner.nextLine().trim();
        System.out.print("New Department (blank to skip): ");
        String department = scanner.nextLine().trim();
        boolean ok = adminService.editFaculty(id, name, department.isEmpty() ? null : department);
        System.out.println(ok ? "Faculty updated." : "No such faculty.");
    }

    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        adminService.getAllStudents().values()
                .forEach(s -> System.out.println("- " + s.getId() + ": " + s.getName()
                        + " | " + s.getEmail() + " | " + s.getPhone()));
    }

    private void viewAllFaculty() {
        System.out.println("\n--- All Faculty ---");
        adminService.getAllFaculties().values()
                .forEach(f -> System.out
                        .println("- " + f.getId() + ": " + f.getName() + " | Dept: " + f.getDepartment()));
    }

    private void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        for (Course c : adminService.getAllCourses().values()) {
            System.out.println("- " + c.getId() + ": " + c.getName()
                    + " | Dept: " + c.getDepartment()
                    + " | " + c.getEnrolledCount() + "/" + c.getCapacity() + " seats");
        }
    }


