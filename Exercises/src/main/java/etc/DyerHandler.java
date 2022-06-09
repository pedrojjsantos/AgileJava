package etc;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DyerHandler extends Handler {
    private final ArrayList<LogRecord> logs = new ArrayList<>();

    @Override
    public void flush() {}
    @Override
    public void close() {}

    @Override
    public void publish(LogRecord record) {
        logs.add(record);
    }

    public String getLog(int i) {
        if (i < logs.size())
            return logs.get(i).getMessage();
        return "";
    }
}
