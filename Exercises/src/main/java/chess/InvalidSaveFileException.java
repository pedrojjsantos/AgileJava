package chess;

import java.io.IOException;

public class InvalidSaveFileException extends IOException {
    InvalidSaveFileException(Exception e) {
        super(e);
    }
}
