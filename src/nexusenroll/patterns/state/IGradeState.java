package nexusenroll.patterns.state;

import nexusenroll.models.Grade;

/**
 * STATE PATTERN -defines what a Grade can do depending on its current
 * lifecycle stage. Matches the assignment requirement: "a process for grade
 * approval (e.g., a 'Pending' state before a final 'Submitted' state)".
 */
public interface IGradeState {
    void submit(Grade grade);

    void correct(Grade grade, char newLetter);

    String getStatus();
}