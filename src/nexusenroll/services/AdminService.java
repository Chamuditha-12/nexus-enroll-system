package nexusenroll.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nexusenroll.models.Administrator;
import nexusenroll.models.Course;
import nexusenroll.models.CourseChangeRequest;
import nexusenroll.models.Faculty;
import nexusenroll.models.Program;
import nexusenroll.models.Student;
import nexusenroll.patterns.factory.UserFactory;

public class AdminService {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Faculty> faculties = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final List<Administrator> administrators = new ArrayList<>();
    private final Map<String, Program> programs = new HashMap<>();
    private final List<CourseChangeRequest> changeRequests = new ArrayList<>();

    // ---------- User creation (Factory Method) ----------

    public Student addStudent(String id, String name) {
        Student s = (Student) UserFactory.createUser("Student", id, name);
        students.put(id, s);
        return s;
    }

    public Faculty addFaculty(String id, String name) {
        Faculty f = (Faculty) UserFactory.createUser("Faculty", id, name);
        faculties.put(id, f);
        return f;
    }

    public Administrator addAdministrator(String id, String name) {
        Administrator a = (Administrator) UserFactory.createUser("Administrator", id, name);
        administrators.add(a);
        return a;
    }

    // ---------- Student & Faculty account management ----------

    /** NEW: "Administrators can edit account details." */
    public boolean editStudent(String id, String newName, String newEmail, String newPhone) {
        Student s = students.get(id);
        if (s == null)
            return false;
        if (newName != null && !newName.isEmpty())
            s.setName(newName);
        if (newEmail != null)
            s.setEmail(newEmail);
        if (newPhone != null)
            s.setPhone(newPhone);
        return true;
    }

    /** NEW: edit faculty details. */
    public boolean editFaculty(String id, String newName, String newDepartment) {
        Faculty f = faculties.get(id);
        if (f == null)
            return false;
        if (newName != null && !newName.isEmpty())
            f.setName(newName);
        if (newDepartment != null)
            f.setDepartment(newDepartment);
        return true;
    }

    public void deactivateStudent(String id) {
        students.remove(id);
    }

    public void deactivateFaculty(String id) {
        faculties.remove(id);
    }

    // ---------- Course management ----------

    public Course addCourse(String id, String name, int capacity, String schedule) {
        Course c = new Course(id, name, capacity, schedule);
        courses.put(id, c);
        return c;
    }

    /** NEW: full-detail course creation. */
    public Course addCourse(String id, String name, int capacity, String schedule,
            String description, String instructorName, String department) {
        Course c = new Course(id, name, capacity, schedule, description, instructorName, department);
        courses.put(id, c);
        return c;
    }

    /**
     * NEW: "Administrators can edit existing course details." Pass
     * null/empty for any field you don't want to change.
     */
    public boolean editCourse(String id, String newName, Integer newCapacity, String newSchedule,
            String newDescription, String newInstructor, String newDepartment) {
        Course c = courses.get(id);
        if (c == null)
            return false;
        if (newName != null && !newName.isEmpty())
            c.setName(newName);
        if (newCapacity != null)
            c.setCapacity(newCapacity);
        if (newSchedule != null && !newSchedule.isEmpty())
            c.setSchedule(newSchedule);
        if (newDescription != null && !newDescription.isEmpty())
            c.setDescription(newDescription);
        if (newInstructor != null && !newInstructor.isEmpty())
            c.setInstructorName(newInstructor);
        if (newDepartment != null && !newDepartment.isEmpty())
            c.setDepartment(newDepartment);
        return true;
    }

    /** NEW: "Administrators can delete courses that are no longer offered." */
    public boolean deleteCourse(String id) {
        return courses.remove(id) != null;
    }

    public void assignCourseToFaculty(String facultyId, String courseId) {
        Faculty f = faculties.get(facultyId);
        Course c = courses.get(courseId);
        if (f != null && c != null) {
            f.assignCourse(c);
        }
    }

    public void forceAddStudent(Student student, Course course) {
        course.commitEnrollment();
        student.enrollIn(course);
    }

    // ---------- Course search (Student Module requirement) ----------

    /** NEW: search by department, instructor, and keyword. */
    public List<Course> searchCoursesByDepartment(String department) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses.values()) {
            if (c.getDepartment().equalsIgnoreCase(department))
                result.add(c);
        }
        return result;
    }

    public List<Course> searchCoursesByInstructor(String instructorName) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses.values()) {
            if (c.getInstructorName().equalsIgnoreCase(instructorName))
                result.add(c);
        }
        return result;
    }

    public List<Course> searchCoursesByKeyword(String keyword) {
        List<Course> result = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Course c : courses.values()) {
            if (c.getName().toLowerCase().contains(lower)
                    || c.getDescription().toLowerCase().contains(lower)
                    || c.getId().toLowerCase().contains(lower)) {
                result.add(c);
            }
        }
        return result;
    }

    // ---------- Degree Program management ----------

    /** NEW: "Administrators can define and manage degree programs." */
    public Program addProgram(String id, String name, int totalCredits, double minGpa) {
        Program p = new Program(id, name, totalCredits, minGpa);
        programs.put(id, p);
        return p;
    }

    public void addRequiredCourseToProgram(String programId, String courseId) {
        Program p = programs.get(programId);
        if (p != null)
            p.addRequiredCourse(courseId);
    }

    public Map<String, Program> getAllPrograms() {
        return programs;
    }

    // ---------- Course change request approval workflow ----------

    /** NEW: called by FacultyService.requestCourseChange(). */
    public void submitChangeRequest(CourseChangeRequest request) {
        changeRequests.add(request);
    }

    public List<CourseChangeRequest> getPendingChangeRequests() {
        List<CourseChangeRequest> pending = new ArrayList<>();
        for (CourseChangeRequest r : changeRequests) {
            if (r.getStatus() == CourseChangeRequest.Status.PENDING)
                pending.add(r);
        }
        return pending;
    }

    /** NEW: "these requests must be approved by an administrator." */
    public boolean approveChangeRequest(String requestId) {
        for (CourseChangeRequest r : changeRequests) {
            if (r.getRequestId().equals(requestId)) {
                r.setStatus(CourseChangeRequest.Status.APPROVED);
                return true;
            }
        }
        return false;
    }

    public boolean rejectChangeRequest(String requestId) {
        for (CourseChangeRequest r : changeRequests) {
            if (r.getRequestId().equals(requestId)) {
                r.setStatus(CourseChangeRequest.Status.REJECTED);
                return true;
            }
        }
        return false;
    }

    // ---------- Getters ----------

    public Student getStudent(String id) {
        return students.get(id);
    }

    public Faculty getFaculty(String id) {
        return faculties.get(id);
    }

    public Course getCourse(String id) {
        return courses.get(id);
    }

    public Map<String, Student> getAllStudents() {
        return students;
    }

    public Map<String, Faculty> getAllFaculties() {
        return faculties;
    }

    public Map<String, Course> getAllCourses() {
        return courses;
    }

    // ---------- Reporting & Analytics ----------

    public String generateEnrollmentReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Enrolment Report ===\n");
        for (Course c : courses.values()) {
            sb.append("- ").append(c.getName())
                    .append(": ").append(c.getEnrolledCount()).append("/").append(c.getCapacity())
                    .append(" seats filled\n");
        }
        return sb.toString();
    }

    public String generateFacultyWorkloadReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Faculty Workload Report ===\n");
        for (Faculty f : faculties.values()) {
            sb.append("- ").append(f.getName())
                    .append(": ").append(f.getAssignedCourses().size()).append(" course(s) assigned\n");
        }
        return sb.toString();
    }

    /** NEW: courses currently above 90% capacity. */
    public String generateCoursePopularityReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Course Popularity Trends (>90% capacity) ===\n");
        boolean any = false;
        for (Course c : courses.values()) {
            if (c.getOccupancyRate() > 0.9) {
                any = true;
                sb.append("- ").append(c.getName())
                        .append(" (").append(c.getDepartment()).append("): ")
                        .append(c.getEnrolledCount()).append("/").append(c.getCapacity())
                        .append(" (").append(Math.round(c.getOccupancyRate() * 100)).append("%)\n");
            }
        }
        if (!any)
            sb.append("No courses currently above 90% capacity.\n");
        return sb.toString();
    }

    /** NEW: enrolment statistics by department. */
    public String generateEnrollmentByDepartmentReport() {
        Map<String, Integer> byDept = new HashMap<>();
        for (Course c : courses.values()) {
            byDept.merge(c.getDepartment(), c.getEnrolledCount(), Integer::sum);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Enrolment Statistics by Department ===\n");
        for (Map.Entry<String, Integer> entry : byDept.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" students enrolled\n");
        }
        return sb.toString();
    }
}