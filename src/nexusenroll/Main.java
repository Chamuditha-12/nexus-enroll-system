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
        