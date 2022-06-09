package etc;

import java.util.logging.Logger;

public class Dyer {
    static final Logger logger = Logger.getLogger(Dyer.class.getName());

    private Dyer() {
        throw new SimpleException("oops.");
    }

    public static Dyer create() throws RuntimeException {
        try {
            return new Dyer();
        } catch (SimpleException e) {
            RuntimeException failedCreate = new RuntimeException("failed to create", e);
            logReverse(failedCreate);
            throw failedCreate;
        }
    }

    private static void logReverse(Throwable e) {
        Throwable exception = e;
        while (exception != null) {
            logger.info(exception.getMessage());
            exception = exception.getCause();
        }
    }
}
