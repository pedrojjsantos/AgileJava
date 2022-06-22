package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dir {
    private final File directory;
    private final String path;

    Dir(String path) {
        this.path = path;
        this.directory = new File(path);

        if (directory.isFile())
            throw new DirPathNameException("Path given is an already existent file!");
    }

    public void ensureExists() {
        if (directory.exists() && directory.isDirectory())
            return;

        directory.mkdir();
    }

    public List<MyFile> files() {
        String[] filenames = directory.list();
        if (filenames == null) {
            throw new DirNonExistentException(
                    "Nonexistent directory, please ensure that it exists before calling!");
        }

        List<MyFile> files = new ArrayList<>();

        for (String filename : filenames)
            files.add(new MyFile(path + '/' + filename));

        return files;
    }


    public boolean delete() {
        for (MyFile file : files())
            file.delete();
        return directory.delete();
    }

    public Attributes getAttributes() {
        return new Attributes(this);
    }

    private boolean isReadOnly() {
        return !directory.canWrite() && directory.canRead();
    }
    private boolean isHidden() {
        return directory.isHidden();
    }

    static class Attributes {
        private boolean isReadOnly;
        private boolean isHidden;

        Attributes(Dir dir) {
            isReadOnly = dir.isReadOnly();
            isHidden = dir.isHidden();
        }

        public boolean isReadOnly() {
            return isReadOnly;
        }

        public boolean isHidden() {
            return isHidden;
        }
    }
}
