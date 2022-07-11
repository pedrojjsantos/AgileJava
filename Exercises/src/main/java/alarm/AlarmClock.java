package alarm;

import java.util.*;
import java.util.concurrent.*;

public class AlarmClock extends Thread{
    private final AlarmListener listener;
    private final BlockingQueue<AlarmInfo> finishedAlarms = new LinkedBlockingQueue<>();
    private final Map<String, Thread> alarms = new HashMap<>();

    public AlarmClock(AlarmListener listener) {
        this.listener = listener;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                AlarmInfo alarm = finishedAlarms.take();
                listener.ring(alarm.toString());
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    public void add(String name, long time) throws Exception {
        AlarmInfo alarm = new AlarmInfo(name, time);
        Thread thr = new Thread(() -> {
            try {
                long start, current;
                start = current = System.currentTimeMillis();
                while (current - start < alarm.time) {
                    sleep(5);
                    current = System.currentTimeMillis();
                }
                finishedAlarms.put(alarm);
            } catch (InterruptedException ignored) {}
        });
        thr.start();
        alarms.put(alarm.name, thr);
    }

    public void shutDown() {
        interrupt();
    }

    public void cancel(String alarmName) {
        Thread thr = alarms.get(alarmName);

        if (thr != null)
            thr.interrupt();
    }

    class AlarmInfo {
        private final String name;
        private final long time;

        AlarmInfo(String name, long time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public String toString() {
            return "%s(%dms)".formatted(name, time);
        }
    }
}
