package nexusenroll.patterns.observer;

/**
 * OBSERVER PATTERN - Observer interface.
 * Anything that wants to receive notifications (a Student, an Advisor, an
 * admin dashboard, etc.) implements this. This keeps the notification
 * system fully decoupled from the core enrolment logic, exactly as required
 * by the System-Wide Requirements: "This process should be automated and
 * decoupled from the core enrolment logic."
 */
public interface IObserver {
    void onNotify(String event, String message);
}