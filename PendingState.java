package nexusenroll.patterns.state;

import nexusenroll.models.Grade;

public class PendingState implements IGradeState {
    @Override
    public void submit(Grade grade) {
        System.out.println("  Submitting grade " + grade.getLetter() + " for " + grade.getStudentId());
        grade.setState(new SubmittedState());
    }

    @Override
    public void correct(Grade grade, char newLetter) {
        System.out.println("  Correcting PENDING grade for " + grade.getStudentId()
                + ": " + grade.getLetter() + " -> " + newLetter);
        grade.setLetter(newLetter);
    }

    @Override
    public String getStatus() {
        return "Pending";
    }
}