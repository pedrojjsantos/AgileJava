package sis.studentinfo;

public class HonorsGradingStrategy implements GradingStrategy{
    public int getGradePointsFor(Student.Grade grade) {
        int points = gradeToPoints(grade);
        return (points > 0) ?
                points + 1 : 0;
    }

    private int gradeToPoints(Student.Grade grade) {
        return switch (grade) {
            case A  -> 4;
            case B  -> 3;
            case C  -> 2;
            case D  -> 1;
            default -> 0;
        };
    }
}
