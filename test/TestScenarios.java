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
                // ---- Scenario 2 (Student Module): Alice enrols - Strategy Pattern validates
                // ----
                System.out.println("\n--- Scenario 2: Alice enrols in CS201 (capacity = 1) ---");
                String result1 = enrollmentService.enroll(alice, cs201);
                System.out.println(result1 == null ? "ENROLMENT SUCCESS for Alice." : "FAILED: " + result1);

                // ---- Scenario 3 (Student Module): Bob tries to enrol - course now full ----
                System.out.println("\n--- Scenario 3: Bob tries to enrol in CS201 (now full) ---");
                String result2 = enrollmentService.enroll(bob, cs201);
                System.out.println(result2 == null ? "ENROLMENT SUCCESS for Bob." : "FAILED: " + result2);

                // ---- Scenario 4 (System-wide requirement): Alice drops - Observer notifies
                // ----
                System.out.println("\n--- Scenario 4: Alice drops CS201 (Observer Pattern fires) ---");
                enrollmentService.drop(alice, cs201);
