package util;

import java.io.IOException;

public class FileDoesntExistException extends RuntimeException {
    FileDoesntExistException(String msg) {
        super(msg);
    }
}
