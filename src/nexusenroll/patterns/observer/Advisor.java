package nexusenroll.patterns.observer;

/**
 * Concrete Observer - an academic advisor who wants to know when one of
 * their advisees drops a course (System-Wide Requirement: "Advisors should
 * be notified when one of their advisees drops a critical course.").
 */
public class Advisor implements IObserver {
    private final String name;

    public Advisor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onNotify(String event, String message) {
        System.out.println("  [NOTIFY -> Advisor " + name + "] (" + event + "): " + message);
    }
}