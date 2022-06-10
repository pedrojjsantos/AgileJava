package etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CounterHandler extends Handler {
    private final StringBuilder logs = new StringBuilder();
    private final HashMap<Level, Integer> counter =
            new HashMap<>();

    CounterHandler() {
        super();
        setFormatter(new CustomFormatter(this));
    }

    @Override
    public void flush() {}
    @Override
    public void close() {}

    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        int n = counter.getOrDefault(level, 0);
        counter.put(level, n+1);
        
        logs.append(getFormatter().format(record));
    }

    public int getCount(Level level) {
        return counter.getOrDefault(level, 0);
    }

    public String getLogMessages() {
        return logs.toString();
    }
}
