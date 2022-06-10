package etc;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    public final static String DEFAULT_FORMAT = "%s: %s%n";
    public final static String COUNTER_FORMAT = "%s: %s (%s total = %d)%n";

    private CounterHandler handler = null;

    CustomFormatter() {
        super();
    }

    CustomFormatter(CounterHandler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public String format(LogRecord record) {
        String formattedMsg;
        Level level = record.getLevel();
        String msg = record.getMessage();

        if (handler == null)
            formattedMsg = String.format(DEFAULT_FORMAT, level, msg);
        else {
            int count = handler.getCount(level);
            formattedMsg = String.format(COUNTER_FORMAT, level, msg, level, count);
        }

        return formattedMsg;
    }
}
