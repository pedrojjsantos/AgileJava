package sis.studentinfo;

public class HonorsGradingStrategy
        extends BasicGradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        int points = super.getGradePointsFor(grade);
        return (points > 0) ?
                points + 1 : 0;
    }
}
