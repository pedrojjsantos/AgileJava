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
                listener.ring(alarm);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    public void add(AlarmInfo alarm) throws Exception {
        Thread thr = new Thread(() -> {
            try {
                long start, current;
                start = current = System.currentTimeMillis();
                while (current - start < alarm.getTime()) {
                    sleep(5);
                    current = System.currentTimeMillis();
                }
                finishedAlarms.put(alarm);
            } catch (InterruptedException ignored) {}
        });
        thr.start();
        alarms.put(alarm.getName(), thr);
    }

    public void shutDown() {
        interrupt();
    }

    public void cancel(String alarmName) {
        Thread thr = alarms.get(alarmName);

        if (thr != null)
            thr.interrupt();
    }
}
