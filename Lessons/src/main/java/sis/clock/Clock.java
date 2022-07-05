package sis.clock;

import java.util.Date;

public class Clock implements Runnable{
    private ClockListener listener;
    private long interval;
    private boolean run = true;

    public Clock(ClockListener listener, long interval) {
        this.listener = listener;
        this.interval = interval;
        new Thread(this).start();
    }

    public void stop() {
        run = false;
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        while (run) {
            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            long now = System.currentTimeMillis();
            if ((now) - (lastTime) >= interval) {
                listener.update(new Date(now));
                lastTime = now;
            }
        }
    }
}
