package sis.studentinfo;

public class RegularGradingStrategy implements GradingStrategy{
    public int getGradePointsFor(Student.Grade grade) {
        return switch (grade) {
            case A  -> 4;
            case B  -> 3;
            case C  -> 2;
            case D  -> 1;
            default -> 0;
        };
    }
}
