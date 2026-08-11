import nexusenroll.models.Course;
import nexusenroll.models.Faculty;
import nexusenroll.models.Student;
import nexusenroll.patterns.observer.Advisor;
import nexusenroll.services.AdminService;
import nexusenroll.services.EnrollmentService;
import nexusenroll.services.FacultyService;

/**
 * TestScenarios - automated, non-interactive demo of every module and every
 * design pattern. No user input needed - just run it and read the console
 * output. Use this for the screencast video instead of typing into the CLI
 * menus by hand.
 */
public class TestScenarios {
        public static void main(String[] args) {
                System.out.println("=========== NexusEnroll: Automated Test Scenarios ===========");

                AdminService adminService = new AdminService();
                EnrollmentService enrollmentService = new EnrollmentService();
                FacultyService facultyService = new FacultyService();

                // ---- Scenario 1 (Admin Module): create users and a course ----
                System.out.println("\n--- Scenario 1: Admin creates Student, Faculty and Course ---");
                Student alice = adminService.addStudent("S001", "Alice");
                Student bob = adminService.addStudent("S002", "Bob");
                Faculty profSilva = adminService.addFaculty("F001", "Prof. Silva");
                Course cs201 = adminService.addCourse("CS201", "Data Structures", 1, "Mon/Wed 09:00-10:30");
                cs201.addPrerequisite("CS101");
                adminService.assignCourseToFaculty("F001", "CS201");
                System.out.println("Created: " + alice.getName() + ", " + bob.getName()
                                + ", " + profSilva.getName() + ", course " + cs201.getName());

                // Advisor subscribes to be notified about this course (Observer Pattern)
                Advisor advisorMark = new Advisor("Mr. Mark");
                cs201.subscribe(advisorMark);

                // Both students completed the prerequisite
                alice.addCompleted("CS101");
                bob.addCompleted("CS101");
