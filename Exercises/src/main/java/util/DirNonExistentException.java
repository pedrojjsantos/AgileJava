package util;

public class DirNonExistentException extends RuntimeException {
    DirNonExistentException(String msg) {
        super(msg);
    }
}
