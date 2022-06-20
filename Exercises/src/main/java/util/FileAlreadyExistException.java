package util;

import java.io.IOException;

public class FileAlreadyExistException extends IOException {
    FileAlreadyExistException(String msg) {
        super(msg);
    }
}
