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

    private void browseCatalogue() {
        System.out.println("\n--- Course Catalogue ---");
        if (adminService.getAllCourses().isEmpty()) {
            System.out.println("No courses available yet.");
            return;
        }
        for (Course c : adminService.getAllCourses().values()) {
            printCourse(c);
        }
    }

    private void searchCourses() {
        System.out.println("\nSearch by: 1. Department  2. Instructor  3. Keyword");
        System.out.print("Choice: ");
        int mode = readInt();
        System.out.print("Enter search text: ");
        String text = scanner.nextLine().trim();

        List<Course> results;
        switch (mode) {
            case 1:
                results = adminService.searchCoursesByDepartment(text);
                break;
            case 2:
                results = adminService.searchCoursesByInstructor(text);
                break;
            case 3:
                results = adminService.searchCoursesByKeyword(text);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No matching courses.");
        }
        for (Course c : results) {
            printCourse(c);
        }
    }

    private void enrol(Student student) {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        Course course = adminService.getCourse(courseId);
        if (course == null) {
            System.out.println("No such course.");
            return;
        }
        String error = enrollmentService.enroll(student, course);
        if (error == null) {
            System.out.println("Enrolment successful! Seats remaining: "
                    + (course.getCapacity() - course.getEnrolledCount()));
        } else {
            System.out.println("Enrolment failed: " + error);
        }
    }

    private void drop(Student student) {
        System.out.print("Enter Course ID to drop: ");
        String courseId = scanner.nextLine().trim();
        Course course = adminService.getCourse(courseId);
        if (course == null || !student.getEnrolledCourses().contains(course)) {
            System.out.println("You are not enrolled in that course.");
            return;
        }
        enrollmentService.drop(student, course);
        System.out.println("Dropped " + course.getName() + ".");
    }

    private void viewSchedule(Student student) {
        System.out.println("\n--- My Current Schedule ---");
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }
        for (Course c : student.getEnrolledCourses()) {
            System.out.println("- " + c.getName() + " | " + c.getSchedule());
        }
    }

    private void viewPastSemesterSchedule(Student student) {
        System.out.println("\n--- Past Semester Schedule ---");
        if (student.getCompletedCourseSemesters().isEmpty()) {
            System.out.println("No completed courses on record.");
            return;
        }
        student.getCompletedCourseSemesters().forEach((courseId, semester) -> {
            Course c = adminService.getCourse(courseId);
            String courseName = (c != null) ? c.getName() : courseId;
            System.out.println("- " + courseName + " (" + semester + ")");
        });
    }

    private void trackProgress(Student student) {
        System.out.println("\n--- Academic Progress ---");
        student.getGrades().forEach((courseId, grade) -> System.out.println("- " + courseId + ": " + grade.getLetter()
                + " (" + grade.getStatus() + ")"));
        if (student.getGrades().isEmpty()) {
            System.out.println("No grades recorded yet.");
        }
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}