package util.file;

public class DirNonExistentException extends RuntimeException {
    DirNonExistentException(String msg) {
        super(msg);
    }
}
