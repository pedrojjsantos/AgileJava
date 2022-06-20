package util;

import java.io.IOException;

public class FileDoesntExistException extends IOException {
    FileDoesntExistException(String msg) {
        super(msg);
    }
}
