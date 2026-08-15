import nexusenroll.models.Course;
import nexusenroll.models.Faculty;
import nexusenroll.models.Program;
import nexusenroll.models.Student;
import nexusenroll.patterns.observer.Advisor;
import nexusenroll.services.AdminService;
import nexusenroll.services.EnrollmentService;
import nexusenroll.services.FacultyService;

/**
 * TestScenarios - automated, non-interactive demo of every module,
 * every design pattern, AND every newly-added feature (search, edit,
 * delete, programs, change-request approval, popularity/department
 * reports). No user input needed - just run it and read the output.
 */
public class TestScenarios {
        public static void main(String[] args) {
                System.out.println("=========== NexusEnroll: Automated Test Scenarios ===========");

                AdminService adminService = new AdminService();
                EnrollmentService enrollmentService = new EnrollmentService();
                FacultyService facultyService = new FacultyService();

                // ---- Scenario 1 (Admin Module): create users and a full-detail course ----
                System.out.println("\n--- Scenario 1: Admin creates Student, Faculty and Course ---");
                Student alice = adminService.addStudent("S001", "Alice");
                alice.setEmail("alice@nexus.edu");
                alice.setPhone("0771234567");
                Student bob = adminService.addStudent("S002", "Bob");
                Faculty profSilva = adminService.addFaculty("F001", "Prof. Silva");
                profSilva.setDepartment("Computer Science");

                Course cs201 = adminService.addCourse("CS201", "Data Structures", 1, "Mon/Wed 09:00-10:30",
                                "Core data structures and algorithms course.", "Prof. Silva", "Computer Science");
                cs201.addPrerequisite("CS101");
                adminService.assignCourseToFaculty("F001", "CS201");
                System.out.println("Created: " + alice.getName() + ", " + bob.getName()
                                + ", " + profSilva.getName() + ", course " + cs201.getName());

                Advisor advisorMark = new Advisor("Mr. Mark");
                cs201.subscribe(advisorMark);

                alice.addCompleted("CS101", "Spring 2025");
                bob.addCompleted("CS101", "Spring 2025");

                // ---- Scenario 2: search courses (NEW) ----
                System.out.println("\n--- Scenario 2: Search courses by department/instructor/keyword ---");
                System.out.println("By department 'Computer Science': "
                                + adminService.searchCoursesByDepartment("Computer Science").size() + " result(s)");
                System.out.println("By instructor 'Prof. Silva': "
                                + adminService.searchCoursesByInstructor("Prof. Silva").size() + " result(s)");
                System.out.println("By keyword 'data': "
                                + adminService.searchCoursesByKeyword("data").size() + " result(s)");

                // ---- Scenario 3 (Student Module): Alice enrols - Strategy Pattern validates
                // ----
                System.out.println("\n--- Scenario 3: Alice enrols in CS201 (capacity = 1) ---");
                String result1 = enrollmentService.enroll(alice, cs201);
                System.out.println(result1 == null ? "ENROLMENT SUCCESS for Alice." : "FAILED: " + result1);

                // ---- Scenario 4: Bob tries to enrol - course now full ----
                System.out.println("\n--- Scenario 4: Bob tries to enrol in CS201 (now full) ---");
                String result2 = enrollmentService.enroll(bob, cs201);
                System.out.println(result2 == null ? "ENROLMENT SUCCESS for Bob." : "FAILED: " + result2);

                // ---- Scenario 5: Alice drops - Observer notifies ----
                System.out.println("\n--- Scenario 5: Alice drops CS201 (Observer Pattern fires) ---");
                enrollmentService.drop(alice, cs201);

                // ---- Scenario 6 (Faculty Module): grade submission - State Pattern ----
                System.out.println("\n--- Scenario 6: Faculty submits and later corrects a grade ---");
                facultyService.submitGrade(alice, "CS201", 'B');
                System.out.println("Status after submit: " + alice.getGrade("CS201").getStatus());
                facultyService.correctGrade(alice, "CS201", 'A');
                System.out.println("Status after correction: " + alice.getGrade("CS201").getStatus()
                                + ", new letter: " + alice.getGrade("CS201").getLetter());

                // ---- Scenario 7: view roster with contact info (NEW) ----
                System.out.println("\n--- Scenario 7: Faculty views class roster with contact info ---");
                facultyService.viewRoster(cs201, adminService.getAllStudents())
                                .forEach(s -> System.out.println("- " + s.getId() + ": " + s.getName()
                                                + " | " + s.getEmail() + " | " + s.getPhone()));

                // ---- Scenario 8: past semester schedule (NEW) ----
                System.out.println("\n--- Scenario 8: Alice views her past semester schedule ---");
                alice.getCompletedCourseSemesters().forEach(
                                (courseId, semester) -> System.out.println("- " + courseId + " (" + semester + ")"));

                // ---- Scenario 9: Faculty requests a course change -> Admin approves (NEW)
                // ----
                System.out.println("\n--- Scenario 9: Faculty requests a course change; Admin approves ---");
                String reqMsg = facultyService.requestCourseChange(profSilva, cs201, "Increase capacity to 40",
                                adminService);
                System.out.println(reqMsg);
                String pendingId = adminService.getPendingChangeRequests().get(0).getRequestId();
                boolean approved = adminService.approveChangeRequest(pendingId);
                System.out.println("Request " + pendingId + " approved: " + approved);
                // Admin then actually applies the approved change:
                adminService.editCourse("CS201", null, 40, null, null, null, null);
                System.out.println("New capacity after edit: " + adminService.getCourse("CS201").getCapacity());

                // ---- Scenario 10: Admin edits and deletes a course (NEW) ----
                System.out.println("\n--- Scenario 10: Admin edits and deletes a course ---");
                Course temp = adminService.addCourse("TMP101", "Temporary Course", 10, "Fri 14:00-16:00");
                System.out.println("Before edit: " + temp.getName() + ", capacity " + temp.getCapacity());
                adminService.editCourse("TMP101", "Renamed Course", 20, null, null, null, null);
                System.out.println("After edit: " + adminService.getCourse("TMP101").getName()
                                + ", capacity " + adminService.getCourse("TMP101").getCapacity());
                boolean deleted = adminService.deleteCourse("TMP101");
                System.out.println("Deleted TMP101: " + deleted + " | Still exists: "
                                + (adminService.getCourse("TMP101") != null));

                // ---- Scenario 11: Admin manages a degree program (NEW) ----
                System.out.println("\n--- Scenario 11: Admin creates a degree program ---");
                Program bscCS = adminService.addProgram("PRG1", "BSc in Computer Science", 120, 2.0);
                adminService.addRequiredCourseToProgram("PRG1", "CS101");
                adminService.addRequiredCourseToProgram("PRG1", "CS201");
                System.out.println("Program: " + bscCS.getName() + " | Credits: " + bscCS.getTotalCredits()
                                + " | Required: " + bscCS.getRequiredCourseIds());

                // ---- Scenario 12: Admin edits a student and faculty record (NEW) ----
                System.out.println("\n--- Scenario 12: Admin edits student and faculty details ---");
                adminService.editStudent("S001", null, "alice.new@nexus.edu", null);
                System.out.println("Alice's updated email: " + adminService.getStudent("S001").getEmail());
                adminService.editFaculty("F001", null, "Software Engineering");
                System.out.println(
                                "Prof. Silva's updated department: " + adminService.getFaculty("F001").getDepartment());

                // ---- Scenario 13: Reports, including the two new ones ----
                System.out.println("\n--- Scenario 13: Admin generates all reports ---");
                System.out.print(adminService.generateEnrollmentReport());
                System.out.print(adminService.generateFacultyWorkloadReport());
                System.out.print(adminService.generateCoursePopularityReport());
                System.out.print(adminService.generateEnrollmentByDepartmentReport());

                System.out.println("\n=========== END OF TEST SCENARIOS ===========");
        }
}