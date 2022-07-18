package util.file;

import java.io.IOException;

public class FileDoesntExistException extends RuntimeException {
    FileDoesntExistException(String msg) {
        super(msg);
    }
}
