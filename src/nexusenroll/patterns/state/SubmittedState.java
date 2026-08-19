package nexusenroll.patterns.state;

import nexusenroll.models.Grade;

public class SubmittedState implements IGradeState {
    @Override
    public void submit(Grade grade) {
        System.out.println("  Grade already submitted for " + grade.getStudentId() + "; no action taken.");
    }

    @Override
    public void correct(Grade grade, char newLetter) {
        
        System.out.println("  Correcting SUBMITTED grade for " + grade.getStudentId()
                + ": " + grade.getLetter() + " -> " + newLetter);
        grade.setLetter(newLetter);
    }

    @Override
    public String getStatus() {
        return "Submitted";
    }
}