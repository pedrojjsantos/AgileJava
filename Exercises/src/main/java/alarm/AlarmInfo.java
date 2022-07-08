package alarm;

public class AlarmInfo {
    private final String ALARM_MSG_FORMAT = "%s(%dms) %s";
    private final String name;
    private final long time;
    private String status;

    AlarmInfo(String name, long time) {
        this.name = name;
        this.time = time;
        status = "Ready!";
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void finish() {
        status = "Finished!";
    }

    @Override
    public String toString() {
        return ALARM_MSG_FORMAT.formatted(name, time, status);
    }
}
