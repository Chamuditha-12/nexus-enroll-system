package nexusenroll.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER PATTERN -the Subject.
 * Any class that needs to broadcast events (e.g. Course, when a seat opens
 * up) extends this. Observers subscribe once and are notified automatically
 * whenever notifyAll() is called - the Subject never needs to know who its
 * observers actually are (Student, Advisor, Admin...), only that they
 * implement IObserver. This is Dependency Inversion in action: high-level
 * code depends on the IObserver abstraction, not on concrete classes.
 */
public class NotificationSubject {
    private final List<IObserver> observers = new ArrayList<>();

    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyAll(String event, String message) {
        for (IObserver obs : observers) {
            obs.onNotify(event, message);
        }
    }
}