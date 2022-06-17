package sis.studentinfo;

import java.io.*;
import java.util.*;

public class CourseCatalog {
    private List<Session> sessions = new ArrayList<>();
    public void add(Session session) {
        sessions.add(session);
    }
    public List<Session> getSessions() {
        return sessions;
    }
    public void clearAll() {
        sessions.clear();
    }

    public void store(String filename) throws IOException {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(sessions);
        }
        finally {
            output.close();
        }
    }

    public void load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            sessions = (List<Session>) input.readObject();
        }
        finally {
            input.close();
        }
    }
}
