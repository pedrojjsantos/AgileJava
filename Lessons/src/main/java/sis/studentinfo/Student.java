package sis.studentinfo;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";
    private String name;
    private String state = "";
    private int credits;
    private List<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy =
            new RegularGradingStrategy();

    public void setGradingStrategy(GradingStrategy strategy) {
        this.gradingStrategy = strategy;
    }

    enum Grade {A, B, C, D, F}

    public Student(String name) {
        this.name = name;
        credits = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getGpa() {
        if (grades.isEmpty()) return 0.0;

        double total = 0;

        for (Grade grade : grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        return total / grades.size();
    }
}
