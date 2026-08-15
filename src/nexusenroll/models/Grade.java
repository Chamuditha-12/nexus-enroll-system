package nexusenroll.models;

import nexusenroll.patterns.state.IGradeState;
import nexusenroll.patterns.state.PendingState;

/**
 * A Grade delegates its behaviour to whichever IGradeState it currently
 * holds (State Pattern). It starts life as Pending and becomes Submitted
 * once the faculty member submits it.
 */
public class Grade {
    private final String studentId;
    private char letter;
    private IGradeState state;

    public Grade(String studentId, char letter) {
        this.studentId = studentId;
        this.letter = letter;
        this.state = new PendingState();
    }

    public void setState(IGradeState state) {
        this.state = state;
    }

    public void submit() {
        state.submit(this);
    }

    public void correct(char newLetter) {
        state.correct(this, newLetter);
    }

    public String getStatus() {
        return state.getStatus();
    }

    public String getStudentId() {
        return studentId;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}