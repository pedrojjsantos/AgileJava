package sis.ui;

import sis.studentinfo.Student;

import java.io.*;
import java.util.*;

public class StudentUI {
    static final String MENU = "(A)dd or (Q)uit?  ";
    static final String ADD_OPTION = "A";
    static final String QUIT_OPTION = "Q";
    static final String NAME_PROMPT = "Name: ";
    static final String ADDED_MESSAGE = "Added";

    private final BufferedReader reader;
    private final BufferedWriter writer;
    private List<Student> students = new ArrayList<Student>();

    public StudentUI() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public List<Student> getAddedStudents() {
        return students;
    }

    public void run() throws IOException {
        String line;
        do {
            write(MENU);
            line = reader.readLine().toUpperCase();
            if (line.equals(ADD_OPTION))
                addStudent();
        } while (!line.equals(QUIT_OPTION));
    }

    private void addStudent() throws IOException {
        write(NAME_PROMPT);
        String name = reader.readLine();
        students.add(new Student(name));
        writeln(ADDED_MESSAGE);
    }

    private void write(String line) throws IOException {
        writer.write(line, 0, line.length());
        writer.flush();
    }
    private void writeln (String line) throws IOException {
        write(line);
        writer.newLine();
        writer.flush();
    }

    public static final void main(String[] args) throws IOException {
        new StudentUI().run();
    }
}
