package util;

import java.io.IOException;

public class FileAlreadyExistException extends RuntimeException {
    FileAlreadyExistException(String msg) {
        super(msg);
    }
}
