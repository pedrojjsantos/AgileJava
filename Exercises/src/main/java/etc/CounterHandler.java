package etc;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CounterHandler extends Handler {
    private final HashMap<Level, Integer> counter =
            new HashMap<>();

    @Override
    public void flush() {}
    @Override
    public void close() {}

    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        int n = counter.getOrDefault(level, 0);
        counter.put(level, n+1);
    }

    public int getCount(Level level) {
        return counter.getOrDefault(level, 0);
    }
}
