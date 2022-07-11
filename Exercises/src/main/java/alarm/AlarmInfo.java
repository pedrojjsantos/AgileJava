package alarm;

public class AlarmInfo {
    private final String name;
    private final long time;

    AlarmInfo(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "%s(%dms)".formatted(name, time);
    }
}
