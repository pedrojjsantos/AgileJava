package sis.studentinfo;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";

    private final String fullName;
    private String firstName  = "";
    private String middleName = "";
    private String lastName   = "";

    private String state = "";
    private int credits;
    private List<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy =
            new BasicGradingStrategy();

    public Student(String fullName) {
        this.fullName = fullName;
        credits = 0;
        List<String> nameParts = split(fullName);
        setName(nameParts);
    }

    public enum Grade {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);

        private int points;

        Grade(int points) {
            this.points = points;
        }

        int getPoints() {
            return points;
        }
    }

    private List<String> split(String str) {
        List<String> results = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        int index = 0;

        while (index < str.length()) {
            char ch = str.charAt(index);
            if (!Character.isWhitespace(ch))
                word.append(ch);
            else if (!word.isEmpty()) {
                results.add(word.toString());
                word = new StringBuilder();
            }
            index++;
        }
        if (!word.isEmpty())
            results.add(word.toString());
        return results;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    public void setGradingStrategy(GradingStrategy strategy) {
        this.gradingStrategy = strategy;
    }

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    private void setName(List<String> nameParts) {
        this.lastName = pop(nameParts);

        String name = pop(nameParts);
        if (nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = pop(nameParts);
        }
    }
    private String pop(List<String> list) {
        if (list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public String getFullName() {
        return fullName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }


    public int getCredits() {
        return credits;
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
